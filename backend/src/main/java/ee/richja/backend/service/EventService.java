package ee.richja.backend.service;

import ee.richja.backend.api.request.EventCreateRequest;
import ee.richja.backend.domain.event.Event;
import ee.richja.backend.repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public UUID create(EventCreateRequest request) {
        log.info("Creating event");
        Event event = new Event();
        event.setName(request.getName());
        event.setTime(request.getTime());
        event.setLocation(request.getLocation());
        event.setAdditionalInfo(request.getAdditionalInfo());
        Event createdEvent = eventRepository.save(event);
        log.info("Event {} created", createdEvent.getUuid());
        return createdEvent.getUuid();
    }

    public Event getEventByUuid(UUID uuid) {
        return eventRepository.findById(uuid).orElse(null);
    }
}
