package ee.richja.backend.service;

import ee.richja.backend.api.request.EventCreateRequest;
import ee.richja.backend.domain.event.Event;
import ee.richja.backend.domain.event.EventParticipant;
import ee.richja.backend.repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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
        BeanUtils.copyProperties(request, event);
        Event createdEvent = eventRepository.save(event);
        log.info("Event {} created", createdEvent.getUuid());
        return createdEvent.getUuid();
    }

    public Event getEventByUuid(UUID uuid) {
        log.info("Asking for event {}", uuid);
        return eventRepository.findById(uuid).orElse(null);
    }

    public List<Event> getAllEvents() {
        log.info("Asking for all events");
        return eventRepository.findAll();
    }

    public void addParticipantToEvent(UUID eventUuid, EventParticipant eventParticipant) {
        log.info("Adding participant to event-{}", eventUuid);
        Event event = eventRepository.findById(eventUuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
        event.addParticipant(eventParticipant);
        eventRepository.save(event);
        log.info("Participant-{}", eventParticipant.getPerson().getUuid());
    }

    public boolean delete(UUID uuid) {
        log.info("Deleting event-{} ", uuid);
        try {
            eventRepository.deleteById(uuid);
            return true;
        } catch (EmptyResultDataAccessException ex) {
            log.warn("Event with UUID {} not found", uuid);
            return false;
        }
    }

    public boolean existsEventByUuid(UUID uuid) {
        return eventRepository.existsById(uuid);
    }
}
