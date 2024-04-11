package ee.richja.backend.service;

import ee.richja.backend.api.request.EventCreateRequest;
import ee.richja.backend.api.request.EventParticipantCreateRequest;
import ee.richja.backend.domain.event.Event;
import ee.richja.backend.domain.event.EventParticipant;
import ee.richja.backend.repository.EventRepository;
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

class EventServiceTest {
    private EventParticipantService eventParticipantService;
    private EventRepository eventRepository;
    private EventService eventService;

    @BeforeEach
    void setUp() {
        eventParticipantService = mock(EventParticipantService.class);
        eventRepository = mock(EventRepository.class);
        eventService = new EventService(eventParticipantService, eventRepository);
    }

    @Test
    void testCreate() {
        EventCreateRequest request = createEventCreateRequest();
        UUID uuid = UUID.randomUUID();
        Event event = new Event();
        event.setUuid(uuid);
        when(eventRepository.save(any())).thenReturn(event);

        UUID result = eventService.create(request);

        assertEquals(uuid, result);
        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    void testGetEventByUuid() {
        UUID uuid = UUID.randomUUID();
        Event event = new Event();
        when(eventRepository.findById(uuid)).thenReturn(Optional.of(event));

        Event result = eventService.getEventByUuid(uuid);

        assertNotNull(result);
        assertEquals(event, result);
    }

    @Test
    void testGetEventByUuid_NotFound() {
        UUID uuid = UUID.randomUUID();
        when(eventRepository.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> eventService.getEventByUuid(uuid));
    }

    @Test
    void testGetAllEvents() {
        List<Event> events = List.of(new Event(), new Event());
        when(eventRepository.findAll()).thenReturn(events);

        List<Event> result = eventService.getAllEvents();

        assertNotNull(result);
        assertEquals(events.size(), result.size());
    }

    @Test
    void testAddParticipantToEvent() {
        UUID eventUuid = UUID.randomUUID();
        EventParticipantCreateRequest request = createEventParticipantCreateRequestPrivate();
        EventParticipant eventParticipant = createEventParticipant();
        when(eventParticipantService.createEventParticipant(request)).thenReturn(eventParticipant);
        Event event = createEvent();
        when(eventRepository.findById(eventUuid)).thenReturn(Optional.of(event));

        eventService.addParticipantToEvent(eventUuid, request);

        assertTrue(event.getEventParticipants().contains(eventParticipant));
        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void testAddParticipantToEvent_ParticipantAlreadyExists() {
        UUID eventUuid = UUID.randomUUID();
        EventParticipantCreateRequest request = createEventParticipantCreateRequestPrivate();
        EventParticipant eventParticipant = createEventParticipant();
        when(eventParticipantService.createEventParticipant(request)).thenReturn(eventParticipant);
        Event event = createEvent();
        event.addParticipant(eventParticipant);
        when(eventRepository.findById(eventUuid)).thenReturn(Optional.of(event));

        assertThrows(ResponseStatusException.class, () -> eventService.addParticipantToEvent(eventUuid, request));
        verify(eventRepository, never()).save(event);
    }

    @Test
    void testAddParticipantToEvent_EventNotFound() {
        UUID eventUuid = UUID.randomUUID();
        EventParticipantCreateRequest request = createEventParticipantCreateRequestPrivate();
        EventParticipant eventParticipant = createEventParticipant();
        when(eventParticipantService.createEventParticipant(request)).thenReturn(eventParticipant);
        when(eventRepository.findById(eventUuid)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> eventService.addParticipantToEvent(eventUuid, request));
    }

    @Test
    void testDelete() {
        UUID uuid = UUID.randomUUID();

        eventService.delete(uuid);

        verify(eventRepository, times(1)).deleteById(uuid);
    }

    @Test
    void testDelete_EventNotFound() {
        UUID uuid = UUID.randomUUID();
        doThrow(EmptyResultDataAccessException.class).when(eventRepository).deleteById(uuid);

        assertThrows(ResponseStatusException.class, () -> eventService.delete(uuid));
    }

    @Test
    void testExistsEventByUuid_True() {
        UUID uuid = UUID.randomUUID();
        when(eventRepository.existsById(uuid)).thenReturn(true);

        boolean result = eventService.existsEventByUuid(uuid);

        assertTrue(result);
    }

    @Test
    void testExistsEventByUuid_False() {
        UUID uuid = UUID.randomUUID();
        when(eventRepository.existsById(uuid)).thenReturn(false);

        boolean result = eventService.existsEventByUuid(uuid);

        assertFalse(result);
    }
}