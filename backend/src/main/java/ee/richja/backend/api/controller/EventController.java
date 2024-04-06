package ee.richja.backend.api.controller;

import ee.richja.backend.api.request.EventCreateRequest;
import ee.richja.backend.domain.event.Event;
import ee.richja.backend.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

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

    @GetMapping("/{uuid}")
    public ResponseEntity<Event> getEvent(@PathVariable UUID uuid) {
        Event event = eventService.getEventByUuid(uuid);
        if (event != null) {
            log.info("Found event {}", event.getUuid());
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        if (!events.isEmpty()) {
            log.info("Returning {} events", events.size());
            return ResponseEntity.ok(events);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
