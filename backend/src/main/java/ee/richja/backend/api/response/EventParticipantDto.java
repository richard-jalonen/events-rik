package ee.richja.backend.api.response;

import ee.richja.backend.domain.event.EventParticipant;
import ee.richja.backend.domain.person.LegalPerson;
import ee.richja.backend.domain.person.PrivatePerson;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class EventParticipantDto {
    private UUID uuid;
    private String type;
    private String paymentType;
    private String firstName;
    private String lastName;
    private String personCode;
    private Long participantCount;
    private String additionalInfo;

    public static EventParticipantDto createDto(EventParticipant eventParticipant) {
        EventParticipantDto eventParticipantDto = new EventParticipantDto();
        eventParticipantDto.setUuid(eventParticipant.getUuid());
        eventParticipantDto.setFirstName(eventParticipant.getPerson().getFirstName());
        eventParticipantDto.setPersonCode(eventParticipant.getPerson().getPersonCode());
        eventParticipantDto.setPaymentType(eventParticipant.getPaymentType());
        eventParticipantDto.setAdditionalInfo(eventParticipant.getAdditionalInfo());
        if (eventParticipant.getPerson() instanceof PrivatePerson privatePerson) {
            eventParticipantDto.setType("PRIVATE");
            eventParticipantDto.setLastName(privatePerson.getLastName());
        } else {
            LegalPerson legalPerson = (LegalPerson) eventParticipant.getPerson();
            eventParticipantDto.setType("LEGAL");
            eventParticipantDto.setParticipantCount(legalPerson.getParticipantCount());
        }
        return eventParticipantDto;
    }

    public static Set<EventParticipantDto> createDtoSet(Set<EventParticipant> participants) {
        Set<EventParticipantDto> eventParticipantDtos = new HashSet<>();
        participants.forEach(person -> eventParticipantDtos.add(createDto(person)));
        return eventParticipantDtos;
    }
}
