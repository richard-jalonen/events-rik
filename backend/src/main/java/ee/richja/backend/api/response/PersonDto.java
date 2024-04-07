package ee.richja.backend.api.response;

import ee.richja.backend.domain.person.LegalPerson;
import ee.richja.backend.domain.person.Person;
import ee.richja.backend.domain.person.PrivatePerson;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class PersonDto {
    private UUID uuid;
    private String type;
    private String paymentType;
    private String firstName;
    private String lastName;
    private String personCode;
    private Long participantCount;
    private String additionalInfo;

    public static PersonDto createDto(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setUuid(person.getUuid());
        personDto.setFirstName(person.getFirstName());
        personDto.setPersonCode(person.getPersonCode());
        personDto.setPaymentType(person.getPaymentType());
        personDto.setAdditionalInfo(person.getAdditionalInfo());
        if (person instanceof PrivatePerson privatePerson) {
            personDto.setType("PRIVATE");
            personDto.setLastName(privatePerson.getLastName());
        } else {
            LegalPerson legalPerson = (LegalPerson) person;
            personDto.setType("LEGAL");
            personDto.setParticipantCount(legalPerson.getParticipantCount());
        }
        return personDto;
    }

    public static Set<PersonDto> createDtoSet(Set<Person> persons) {
        Set<PersonDto> personDtos = new HashSet<>();
        persons.forEach(person -> personDtos.add(createDto(person)));
        return personDtos;
    }
}
