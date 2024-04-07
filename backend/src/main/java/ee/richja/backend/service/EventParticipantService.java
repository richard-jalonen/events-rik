package ee.richja.backend.service;

import ee.richja.backend.api.request.EventParticipantCreateRequest;
import ee.richja.backend.api.request.EventParticipantUpdateRequest;
import ee.richja.backend.domain.event.EventParticipant;
import ee.richja.backend.properties.PaymentProperties;
import ee.richja.backend.repository.EventParticipantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventParticipantService {
    private final EventParticipantRepository eventParticipantRepository;
    private final PersonService personService;
    private final PaymentProperties paymentProperties;

    public EventParticipant getParticipant(UUID uuid) {
        return eventParticipantRepository.findById(uuid).orElse(null);
    }

    public void update(EventParticipantUpdateRequest request) {
        log.info("Updating participant-{}", request.getUuid());
        validateRequest(request);
        EventParticipant eventParticipant = eventParticipantRepository.findById(request.getUuid())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found"));

        personService.update(request, eventParticipant.getPerson());
        BeanUtils.copyProperties(request, eventParticipant);
        eventParticipantRepository.save(eventParticipant);
        log.info("Updated participant-{}", request.getUuid());
    }

    private void validateRequest(EventParticipantCreateRequest request) {
        if (request.getType() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Person type cannot be null");
        }
        if (!paymentProperties.getTypes().contains(request.getPaymentType())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid payment type");
        }
    }

    public EventParticipant createEventParticipant(EventParticipantCreateRequest request) {
        EventParticipant eventParticipant = new EventParticipant();
        eventParticipant.setPerson(personService.create(request));
        eventParticipant.setPaymentType(request.getPaymentType());
        eventParticipant.setAdditionalInfo(request.getAdditionalInfo());
        return eventParticipant;
    }
}
