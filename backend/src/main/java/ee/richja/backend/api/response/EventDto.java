package ee.richja.backend.api.response;

import ee.richja.backend.domain.event.Event;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class EventDto {
    private UUID uuid;
    private String name;
    private String time;
    private String location;
    private Set<PersonDto> participants;

    public static EventDto createDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setUuid(event.getUuid());
        eventDto.setName(event.getName());
        eventDto.setTime(event.getTime().toString());
        eventDto.setLocation(event.getLocation());
        eventDto.setParticipants(PersonDto.createDtoSet(event.getParticipants()));
        return eventDto;
    }

    public static List<EventDto> createDtoList(List<Event> events) {
        List<EventDto> eventDtos = new ArrayList<>();
        events.forEach(event -> eventDtos.add(createDto(event)));
        return eventDtos;
    }
}