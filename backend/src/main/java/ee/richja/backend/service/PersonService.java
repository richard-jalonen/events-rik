package ee.richja.backend.service;

import ee.richja.backend.api.request.PersonCreateRequest;
import ee.richja.backend.domain.person.LegalPerson;
import ee.richja.backend.domain.person.Person;
import ee.richja.backend.domain.person.PrivatePerson;
import ee.richja.backend.properties.PaymentProperties;
import ee.richja.backend.repository.person.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            LegalPerson legalPerson = new LegalPerson();
            legalPerson.setFirstName(request.getFirstName());
            legalPerson.setPersonCode(request.getPersonCode());
            legalPerson.setParticipantCount(request.getParticipantCount());
            legalPerson.setPaymentType(request.getPaymentType());
            legalPerson.setAdditionalInfo(request.getAdditionalInfo());
            person = legalPerson;
        } else if (request.getType().equalsIgnoreCase("PRIVATE")) {
            PrivatePerson privatePerson = new PrivatePerson();
            privatePerson.setFirstName(request.getFirstName());
            privatePerson.setLastName(request.getLastName());
            privatePerson.setPersonCode(request.getPersonCode());
            privatePerson.setPaymentType(request.getPaymentType());
            privatePerson.setAdditionalInfo(request.getAdditionalInfo());
            person = privatePerson;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown person type: " + request.getType());
        }
        log.info("Person created");
        return personRepository.save(person);
    }
}
