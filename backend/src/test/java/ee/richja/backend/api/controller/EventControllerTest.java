package ee.richja.backend.api.controller;

import ee.richja.backend.api.request.EventCreateRequest;
import ee.richja.backend.api.request.EventParticipantCreateRequest;
import ee.richja.backend.api.response.EventDto;
import ee.richja.backend.domain.event.Event;
import ee.richja.backend.service.EventParticipantService;
import ee.richja.backend.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventControllerTest {

    @Mock
    private EventService eventService;

    @Mock
    private EventParticipantService eventParticipantService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    void setUp() {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEvents() {
        List<Event> events = Collections.singletonList(createEventMock());
        when(eventService.getAllEvents()).thenReturn(events);

        ResponseEntity<List<EventDto>> response = eventController.getAllEvents();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(events.size(), Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    void getAllEvents_noContent() {
        when(eventService.getAllEvents()).thenReturn(Collections.emptyList());

        ResponseEntity<List<EventDto>> response = eventController.getAllEvents();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testGetEvent() {
        UUID uuid = UUID.randomUUID();
        Event event = createEventMock();
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
    void testAddParticipant_throwsResponseStatusException() {
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

    private Event createEventMock() {
        return Event.builder().time(LocalDateTime.now()).build();
    }
}