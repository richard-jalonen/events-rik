package ee.richja.backend.api.controller;

import ee.richja.backend.api.request.EventParticipantUpdateRequest;
import ee.richja.backend.api.response.EventParticipantDto;
import ee.richja.backend.domain.event.EventParticipant;
import ee.richja.backend.domain.person.PrivatePerson;
import ee.richja.backend.service.EventParticipantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.UUID;

import static ee.richja.backend.util.TestObjectFactory.createPrivatePerson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ParticipantControllerTest {
    private EventParticipantService eventParticipantService;
    private ParticipantController participantController;

    @BeforeEach
    void setUp() {
        eventParticipantService = mock(EventParticipantService.class);
        participantController = new ParticipantController(eventParticipantService);
    }

    @Test
    void testGetParticipant() {
        UUID uuid = UUID.randomUUID();
        PrivatePerson person = createPrivatePerson();
        EventParticipant participant = new EventParticipant();
        participant.setUuid(uuid);
        participant.setPerson(person);
        when(eventParticipantService.getParticipant(uuid)).thenReturn(participant);

        ResponseEntity<EventParticipantDto> response = participantController.getParticipant(uuid);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(uuid, Objects.requireNonNull(response.getBody()).getUuid());
        assertEquals(person.getFirstName(), response.getBody().getFirstName());
        assertEquals(person.getLastName(), response.getBody().getLastName());
        assertEquals(person.getPersonCode(), response.getBody().getPersonCode());
    }

    @Test
    void testUpdateParticipant() {
        EventParticipantUpdateRequest request = new EventParticipantUpdateRequest();

        ResponseEntity<Void> response = participantController.updateParticipant(request);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(eventParticipantService, times(1)).update(request);
    }

    @Test
    void testDeleteParticipant() {
        UUID uuid = UUID.randomUUID();

        ResponseEntity<Void> response = participantController.deleteParticipant(uuid);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(eventParticipantService, times(1)).delete(uuid);
    }
}