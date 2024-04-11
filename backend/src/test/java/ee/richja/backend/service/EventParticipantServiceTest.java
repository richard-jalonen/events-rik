package ee.richja.backend.service;

import ee.richja.backend.api.request.EventParticipantCreateRequest;
import ee.richja.backend.api.request.EventParticipantUpdateRequest;
import ee.richja.backend.domain.event.EventParticipant;
import ee.richja.backend.domain.person.LegalPerson;
import ee.richja.backend.domain.person.PrivatePerson;
import ee.richja.backend.properties.PaymentProperties;
import ee.richja.backend.repository.EventParticipantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ee.richja.backend.util.TestObjectFactory.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventParticipantServiceTest {
    private PersonService personService;
    private EventParticipantRepository eventParticipantRepository;
    private PaymentProperties paymentProperties;
    private EventParticipantService eventParticipantService;

    @BeforeEach
    void setUp() {
        personService = mock(PersonService.class);
        eventParticipantRepository = mock(EventParticipantRepository.class);
        paymentProperties = mock(PaymentProperties.class);
        eventParticipantService = new EventParticipantService(eventParticipantRepository, personService, paymentProperties);
    }

    @Test
    void testGetParticipant() {
        UUID uuid = UUID.randomUUID();
        EventParticipant eventParticipant = new EventParticipant();
        when(eventParticipantRepository.findById(uuid)).thenReturn(Optional.of(eventParticipant));

        EventParticipant result = eventParticipantService.getParticipant(uuid);

        assertNotNull(result);
        assertEquals(eventParticipant, result);
    }

    @Test
    void testGetParticipant_NotFound() {
        UUID uuid = UUID.randomUUID();
        when(eventParticipantRepository.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> eventParticipantService.getParticipant(uuid));
    }

    @Test
    void testUpdate() {
        UUID uuid = UUID.randomUUID();
        EventParticipantUpdateRequest request = new EventParticipantUpdateRequest();
        request.setUuid(uuid);
        request.setType("PRIVATE");
        request.setPaymentType("Cash");
        EventParticipant eventParticipant = createEventParticipant();
        when(paymentProperties.getTypes()).thenReturn(List.of("Cash", "Credit card"));
        when(eventParticipantRepository.findById(uuid)).thenReturn(Optional.of(eventParticipant));
        when(personService.update(request, eventParticipant.getPerson())).thenReturn(eventParticipant.getPerson());

        eventParticipantService.update(request);

        verify(eventParticipantRepository, times(1)).save(eventParticipant);
    }

    @Test
    void testUpdate_PersonTypeIsNull() {
        EventParticipantUpdateRequest request = new EventParticipantUpdateRequest();
        request.setUuid(UUID.randomUUID());

        assertThrows(ResponseStatusException.class, () -> eventParticipantService.update(request));
    }

    @Test
    void testUpdate_PaymentPropertyNotFound() {
        EventParticipantUpdateRequest request = new EventParticipantUpdateRequest();
        request.setUuid(UUID.randomUUID());
        request.setType("PRIVATE");
        request.setPaymentType("Non-existent payment type");
        when(paymentProperties.getTypes()).thenReturn(List.of("Cash", "Credit card"));

        assertThrows(ResponseStatusException.class, () -> eventParticipantService.update(request));
    }

    @Test
    void testUpdate_ParticipantNotFound() {
        EventParticipantUpdateRequest request = new EventParticipantUpdateRequest();
        request.setUuid(UUID.randomUUID());
        request.setType("PRIVATE");
        request.setPaymentType("Cash");
        when(paymentProperties.getTypes()).thenReturn(List.of("Cash", "Credit card"));

        assertThrows(ResponseStatusException.class, () -> eventParticipantService.update(request));
    }

    @Test
    void testDelete() {
        UUID uuid = UUID.randomUUID();

        eventParticipantService.delete(uuid);

        verify(eventParticipantRepository, times(1)).deleteById(uuid);
    }

    @Test
    void testDelete_ParticipantNotFound() {
        UUID uuid = UUID.randomUUID();
        doThrow(EmptyResultDataAccessException.class).when(eventParticipantRepository).deleteById(uuid);

        assertThrows(ResponseStatusException.class, () -> eventParticipantService.delete(uuid));
    }

    @Test
    void testCreateEventParticipant_PrivatePerson() {
        EventParticipantCreateRequest request = createEventParticipantCreateRequestPrivate();
        PrivatePerson person = createPrivatePerson();
        when(personService.create(request)).thenReturn(person);

        EventParticipant createdParticipant = eventParticipantService.createEventParticipant(request);

        assertNotNull(createdParticipant);
        assertEquals(1L, createdParticipant.getParticipantCount());
        assertEquals(person, createdParticipant.getPerson());
    }

    @Test
    void testCreateEventParticipant_LegalPerson() {
        EventParticipantCreateRequest request = createEventParticipantCreateRequestLegal();
        LegalPerson person = createLegalPerson();
        when(personService.create(request)).thenReturn(person);

        EventParticipant createdParticipant = eventParticipantService.createEventParticipant(request);

        assertNotNull(createdParticipant);
        assertEquals(55L, createdParticipant.getParticipantCount());
        assertEquals(person, createdParticipant.getPerson());
    }
}