package ee.richja.backend.service;

import ee.richja.backend.api.request.PersonCreateRequest;
import ee.richja.backend.api.request.PersonUpdateRequest;
import ee.richja.backend.domain.person.LegalPerson;
import ee.richja.backend.domain.person.Person;
import ee.richja.backend.domain.person.PrivatePerson;
import ee.richja.backend.properties.PaymentProperties;
import ee.richja.backend.repository.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final PaymentProperties paymentProperties;

    public Person create(PersonCreateRequest request) {
        log.info("Creating person");
        if (request.getType() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Person type cannot be null");
        }
        if (paymentProperties.getTypes().stream()
                .noneMatch(type -> type.equalsIgnoreCase(request.getPaymentType()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid payment type");
        }

        Person person;
        if (request.getType().equalsIgnoreCase("LEGAL")) {
            person = getLegalPerson(request);
        } else if (request.getType().equalsIgnoreCase("PRIVATE")) {
            person = getPrivatePerson(request);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown person type: " + request.getType());
        }
        Person createdPerson = personRepository.save(person);
        log.info("Person {} created", createdPerson.getUuid());
        return createdPerson;
    }

    private static PrivatePerson getPrivatePerson(PersonCreateRequest request) {
        PrivatePerson privatePerson = new PrivatePerson();
        privatePerson.setFirstName(request.getFirstName());
        privatePerson.setLastName(request.getLastName());
        if (request.getPersonCode().length() != 11) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Code must be 11 digits");
        }
        privatePerson.setPersonCode(request.getPersonCode());
        privatePerson.setPaymentType(request.getPaymentType());
        privatePerson.setAdditionalInfo(request.getAdditionalInfo());
        return privatePerson;
    }

    private static LegalPerson getLegalPerson(PersonCreateRequest request) {
        LegalPerson legalPerson = new LegalPerson();
        legalPerson.setFirstName(request.getFirstName());
        if (request.getPersonCode().length() != 8) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Code must be 8 digits");
        }
        legalPerson.setPersonCode(request.getPersonCode());
        legalPerson.setParticipantCount(request.getParticipantCount());
        legalPerson.setPaymentType(request.getPaymentType());
        legalPerson.setAdditionalInfo(request.getAdditionalInfo());
        return legalPerson;
    }

    public Person getPersonByUuid(UUID uuid) {
        log.info("Asking for person {}", uuid);
        return personRepository.findById(uuid).orElse(null);
    }

    public void update(PersonUpdateRequest request) {
        log.info("Updating person-{}", request.getUuid());
        Person person = personRepository.findById(request.getUuid()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found"));
        BeanUtils.copyProperties(request, person);
        personRepository.save(person);
        log.info("Person-{} updated", person.getUuid());
    }
}
