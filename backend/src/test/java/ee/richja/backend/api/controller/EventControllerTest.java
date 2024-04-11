package ee.richja.backend.api.controller;

import ee.richja.backend.api.request.EventCreateRequest;
import ee.richja.backend.api.request.EventParticipantCreateRequest;
import ee.richja.backend.api.response.EventDto;
import ee.richja.backend.domain.event.Event;
import ee.richja.backend.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static ee.richja.backend.util.TestObjectFactory.createEvent;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventControllerTest {
    private EventService eventService;
    private EventController eventController;

    @BeforeEach
    void setUp() {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));
        eventService = mock(EventService.class);
        eventController = new EventController(eventService);
    }

    @Test
    void testGetAllEvents() {
        List<Event> events = Collections.singletonList(createEvent());
        when(eventService.getAllEvents()).thenReturn(events);

        ResponseEntity<List<EventDto>> response = eventController.getAllEvents();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(events.size(), Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    void testGetAllEvents_NoContent() {
        when(eventService.getAllEvents()).thenReturn(Collections.emptyList());

        ResponseEntity<List<EventDto>> response = eventController.getAllEvents();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testGetEvent() {
        UUID uuid = UUID.randomUUID();
        Event event = createEvent();
        when(eventService.getEventByUuid(uuid)).thenReturn(event);

        ResponseEntity<EventDto> response = eventController.getEvent(uuid);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testCreateEvent() {
        EventCreateRequest request = new EventCreateRequest();
        UUID eventUuid = UUID.randomUUID();
        when(eventService.create(request)).thenReturn(eventUuid);

        ResponseEntity<Void> response = eventController.createEvent(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testAddParticipant() {
        UUID uuid = UUID.randomUUID();
        EventParticipantCreateRequest request = new EventParticipantCreateRequest();
        when(eventService.existsEventByUuid(uuid)).thenReturn(true);

        ResponseEntity<Void> response = eventController.addParticipant(uuid, request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testAddParticipant_ThrowsResponseStatusException() {
        UUID uuid = UUID.randomUUID();
        EventParticipantCreateRequest request = new EventParticipantCreateRequest();

        assertThrows(ResponseStatusException.class, () -> eventController.addParticipant(uuid, request));
    }

    @Test
    void testDeleteEvent() {
        UUID uuid = UUID.randomUUID();

        ResponseEntity<Void> response = eventController.deleteEvent(uuid);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(eventService, times(1)).delete(uuid);
    }
}