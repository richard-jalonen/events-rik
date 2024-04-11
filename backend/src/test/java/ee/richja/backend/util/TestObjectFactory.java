package ee.richja.backend.util;

import ee.richja.backend.api.request.EventCreateRequest;
import ee.richja.backend.api.request.EventParticipantCreateRequest;
import ee.richja.backend.api.request.EventParticipantUpdateRequest;
import ee.richja.backend.domain.event.Event;
import ee.richja.backend.domain.event.EventParticipant;
import ee.richja.backend.domain.person.LegalPerson;
import ee.richja.backend.domain.person.PrivatePerson;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.UUID;

public class TestObjectFactory {
    public static Event createEvent() {
        Event event = new Event();
        event.setTime(LocalDateTime.now());
        return event;
    }

    public static EventParticipant createEventParticipant() {
        EventParticipant eventParticipant = new EventParticipant();
        eventParticipant.setPerson(createPrivatePerson());
        return eventParticipant;
    }

    public static PrivatePerson createPrivatePerson() {
        PrivatePerson privatePerson = new PrivatePerson();
        privatePerson.setFirstName("First");
        privatePerson.setLastName("Last");
        privatePerson.setPersonCode("39209070000");
        return privatePerson;
    }

    public static LegalPerson createLegalPerson() {
        LegalPerson legalPerson = new LegalPerson();
        legalPerson.setFirstName("Company");
        legalPerson.setPersonCode("12345678");
        return legalPerson;
    }

    public static EventParticipantCreateRequest createEventParticipantCreateRequestPrivate() {
        EventParticipantCreateRequest request = new EventParticipantCreateRequest();
        request.setType("PRIVATE");
        request.setPaymentType("Credit Card");
        request.setAdditionalInfo("Some additional info");
        request.setFirstName("First");
        request.setLastName("Last");
        request.setPersonCode("39209070000");
        return request;
    }

    public static EventParticipantCreateRequest createEventParticipantCreateRequestLegal() {
        EventParticipantCreateRequest request = new EventParticipantCreateRequest();
        request.setType("LEGAL");
        request.setPaymentType("Credit Card");
        request.setAdditionalInfo("Some additional info");
        request.setFirstName("Company");
        request.setPersonCode("12345678");
        request.setParticipantCount(55L);
        return request;
    }

    public static EventParticipantUpdateRequest createEventParticipantUpdateRequestPrivate(UUID uuid) {
        EventParticipantUpdateRequest request = new EventParticipantUpdateRequest();
        request.setUuid(uuid);
        BeanUtils.copyProperties(createEventParticipantCreateRequestPrivate(), request);
        return request;
    }

    public static EventParticipantUpdateRequest createEventParticipantUpdateRequestLegal(UUID uuid) {
        EventParticipantUpdateRequest request = new EventParticipantUpdateRequest();
        request.setUuid(uuid);
        BeanUtils.copyProperties(createEventParticipantCreateRequestLegal(), request);
        return request;
    }

    public static EventCreateRequest createEventCreateRequest() {
        EventCreateRequest request = new EventCreateRequest();
        request.setName("Test Event");
        request.setTime(LocalDateTime.now());
        request.setLocation("Location");
        request.setAdditionalInfo("Additional info");
        return request;
    }
}
