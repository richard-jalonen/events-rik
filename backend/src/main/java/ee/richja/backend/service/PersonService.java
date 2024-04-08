package ee.richja.backend.service;

import ee.richja.backend.api.request.EventParticipantCreateRequest;
import ee.richja.backend.api.request.EventParticipantUpdateRequest;
import ee.richja.backend.domain.person.LegalPerson;
import ee.richja.backend.domain.person.Person;
import ee.richja.backend.domain.person.PrivatePerson;
import ee.richja.backend.repository.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public Person create(EventParticipantCreateRequest request) {
        log.info("Creating person");
        Person existingPerson = personRepository.findByPersonCode(request.getPersonCode());
        if (existingPerson != null) {
            return updateExistingPerson(request, existingPerson);
        }
        Person createdPerson = createPerson(request);
        log.info("Person {} created", createdPerson.getUuid());
        return createdPerson;
    }

    private Person updateExistingPerson(EventParticipantCreateRequest request, Person existingPerson) {
        EventParticipantUpdateRequest eventParticipantUpdateRequest = new EventParticipantUpdateRequest();
        BeanUtils.copyProperties(request, eventParticipantUpdateRequest);
        return update(eventParticipantUpdateRequest, existingPerson);
    }

    private Person createPerson(EventParticipantCreateRequest request) {
        Person person;
        if (request.getType().equalsIgnoreCase("LEGAL")) {
            person = getLegalPerson(request);
        } else if (request.getType().equalsIgnoreCase("PRIVATE")) {
            person = getPrivatePerson(request);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown person type: " + request.getType());
        }
        return personRepository.save(person);
    }

    private static PrivatePerson getPrivatePerson(EventParticipantCreateRequest request) {
        PrivatePerson privatePerson = new PrivatePerson();
        privatePerson.setFirstName(request.getFirstName());
        privatePerson.setLastName(request.getLastName());
        if (request.getPersonCode().length() != 11) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Code must be 11 digits");
        }
        privatePerson.setPersonCode(request.getPersonCode());
        return privatePerson;
    }

    private static LegalPerson getLegalPerson(EventParticipantCreateRequest request) {
        LegalPerson legalPerson = new LegalPerson();
        legalPerson.setFirstName(request.getFirstName());
        if (request.getPersonCode().length() != 8) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Code must be 8 digits");
        }
        legalPerson.setPersonCode(request.getPersonCode());
        return legalPerson;
    }

    public Person update(EventParticipantUpdateRequest request, Person person) {
        log.info("Updating person-{}", person.getUuid());
        BeanUtils.copyProperties(request, person, "uuid");
        personRepository.save(person);
        log.info("Person-{} updated", person.getUuid());
        return person;
    }
}
