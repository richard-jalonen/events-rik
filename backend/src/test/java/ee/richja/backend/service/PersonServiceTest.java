package ee.richja.backend.service;

import ee.richja.backend.api.request.EventParticipantCreateRequest;
import ee.richja.backend.api.request.EventParticipantUpdateRequest;
import ee.richja.backend.domain.person.LegalPerson;
import ee.richja.backend.domain.person.Person;
import ee.richja.backend.domain.person.PrivatePerson;
import ee.richja.backend.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static ee.richja.backend.util.TestObjectFactory.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonServiceTest {
    private PersonRepository personRepository;
    private PersonService personService;


    @BeforeEach
    void setUp() {
        personRepository = mock(PersonRepository.class);
        personService = new PersonService(personRepository);
    }

    @Test
    void testCreate_PrivatePerson() {
        EventParticipantCreateRequest request = createEventParticipantCreateRequestPrivate();
        when(personRepository.save(any())).thenReturn(createPrivatePerson());

        PrivatePerson result = (PrivatePerson) personService.create(request);

        assertInstanceOf(PrivatePerson.class, result);
        assertEquals("First", result.getFirstName());
        assertEquals("Last", result.getLastName());
        assertEquals("39209070000", result.getPersonCode());
        verify(personRepository, times(1)).save(any(PrivatePerson.class));
    }

    @Test
    void testCreate_LegalPerson() {
        EventParticipantCreateRequest request = createEventParticipantCreateRequestLegal();
        when(personRepository.save(any())).thenReturn(createLegalPerson());

        LegalPerson result = (LegalPerson) personService.create(request);

        assertInstanceOf(LegalPerson.class, result);
        assertEquals("Company", result.getFirstName());
        assertEquals("12345678", result.getPersonCode());
        verify(personRepository, times(1)).save(any(LegalPerson.class));
    }

    @Test
    void testCreate_PrivatePerson_InvalidPersonCode() {
        EventParticipantCreateRequest request = createEventParticipantCreateRequestPrivate();
        request.setPersonCode("123");

        assertThrows(ResponseStatusException.class, () -> personService.create(request));
    }

    @Test
    void testCreate_LegalPerson_InvalidPersonCode() {
        EventParticipantCreateRequest request = createEventParticipantCreateRequestLegal();
        request.setPersonCode("123");

        assertThrows(ResponseStatusException.class, () -> personService.create(request));
    }

    @Test
    void testCreate_ExistingPerson() {
        EventParticipantCreateRequest request = createEventParticipantCreateRequestPrivate();
        PrivatePerson existingPerson = createPrivatePerson();
        when(personRepository.findByPersonCode(request.getPersonCode())).thenReturn(existingPerson);

        Person result = personService.create(request);

        assertEquals(existingPerson, result);
    }

    @Test
    void testCreate_UnknownPersonType() {
        EventParticipantCreateRequest request = createEventParticipantCreateRequestPrivate();
        request.setType("UNKNOWN");

        assertThrows(ResponseStatusException.class, () -> personService.create(request));
        verify(personRepository, never()).save(any());
    }

    @Test
    void testUpdate_Success() {
        UUID uuid = UUID.randomUUID();
        EventParticipantUpdateRequest request = createEventParticipantUpdateRequestPrivate(uuid);
        PrivatePerson person = createPrivatePerson();
        when(personRepository.save(person)).thenReturn(person);

        PrivatePerson result = (PrivatePerson) personService.update(request, person);

        assertEquals("First", result.getFirstName());
        assertEquals("Last", result.getLastName());
        assertEquals("39209070000", result.getPersonCode());
        verify(personRepository, times(1)).save(person);
    }
}