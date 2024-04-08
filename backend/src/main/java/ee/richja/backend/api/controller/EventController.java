package ee.richja.backend.api.controller;

import ee.richja.backend.api.request.EventCreateRequest;
import ee.richja.backend.api.request.EventParticipantCreateRequest;
import ee.richja.backend.api.response.EventDto;
import ee.richja.backend.domain.event.Event;
import ee.richja.backend.service.EventParticipantService;
import ee.richja.backend.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static ee.richja.backend.api.response.EventDto.createDto;
import static ee.richja.backend.api.response.EventDto.createDtoList;

@Slf4j
@Controller
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final EventParticipantService eventParticipantService;

    @GetMapping
    public ResponseEntity<List<EventDto>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        if (!events.isEmpty()) {
            log.info("Returning {} events", events.size());
            return ResponseEntity.ok(createDtoList(events));
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<EventDto> getEvent(@PathVariable UUID uuid) {
        Event event = eventService.getEventByUuid(uuid);
        log.info("Found event {}", event.getUuid());
        return ResponseEntity.ok(createDto(event));
    }

    @PostMapping
    public ResponseEntity<Void> createEvent(@RequestBody EventCreateRequest request) {
        UUID eventUuid = eventService.create(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(eventUuid)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/{uuid}/participants")
    public ResponseEntity<Void> addParticipant(@PathVariable UUID uuid, @RequestBody EventParticipantCreateRequest request) {
        if (!eventService.existsEventByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found with UUID: " + uuid);
        }

        eventService.addParticipantToEvent(uuid, request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID uuid) {
        eventService.delete(uuid);
        log.info("Deleted event with UUID: {}", uuid);
        return ResponseEntity.noContent().build();
    }
}
