package ee.richja.backend.service;

import ee.richja.backend.api.request.EventCreateRequest;
import ee.richja.backend.domain.event.Event;
import ee.richja.backend.domain.person.Person;
import ee.richja.backend.repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        event.setName(request.getName());
        event.setTime(request.getTime());
        event.setLocation(request.getLocation());
        event.setAdditionalInfo(request.getAdditionalInfo());
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

    public void addPersonToEvent(UUID eventUuid, Person person) {
        log.info("Adding person-{} to event-{}", person.getUuid(), eventUuid);
        Event event = eventRepository.findById(eventUuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
        event.addParticipant(person);
        eventRepository.save(event);
        log.info("Person-{} added", person.getUuid());
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
