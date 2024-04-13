package ee.richja.backend.api.controller;

import ee.richja.backend.api.request.EventParticipantUpdateRequest;
import ee.richja.backend.api.response.EventParticipantDto;
import ee.richja.backend.domain.event.EventParticipant;
import ee.richja.backend.service.EventParticipantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static ee.richja.backend.api.response.EventParticipantDto.createDto;


@Slf4j
@RestController
@RequestMapping("/api/v1/participants")
@RequiredArgsConstructor
public class ParticipantController {
    private final EventParticipantService eventParticipantService;

    @GetMapping("/{uuid}")
    public ResponseEntity<EventParticipantDto> getParticipant(@PathVariable UUID uuid) {
        EventParticipant eventParticipant = eventParticipantService.getParticipant(uuid);
        log.info("Found participant-{}", eventParticipant.getUuid());
        return ResponseEntity.ok(createDto(eventParticipant));
    }

    @PutMapping
    public ResponseEntity<Void> updateParticipant(@RequestBody EventParticipantUpdateRequest request) {
        eventParticipantService.update(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable UUID uuid) {
        eventParticipantService.delete(uuid);
        log.info("Deleted participant-{}", uuid);
        return ResponseEntity.noContent().build();
    }
}
