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
        personDto.setPaymentType(person.getPaymentType());
        if (person instanceof PrivatePerson privatePerson) {
            personDto.setType("PRIVATE");
            personDto.setUuid(person.getUuid());
            personDto.setPaymentType(person.getPaymentType());
            personDto.setFirstName(privatePerson.getFirstName());
            personDto.setLastName(privatePerson.getLastName());
            personDto.setPersonCode(privatePerson.getPersonCode());
            personDto.setAdditionalInfo(privatePerson.getAdditionalInfo());
        } else {
            LegalPerson legalPerson = (LegalPerson) person;
            personDto.setType("LEGAL");
            personDto.setFirstName(legalPerson.getFirstName());
            personDto.setPersonCode(legalPerson.getPersonCode());
            personDto.setParticipantCount(legalPerson.getParticipantCount());
            personDto.setAdditionalInfo(legalPerson.getAdditionalInfo());
        }
        return personDto;
    }

    public static Set<PersonDto> createDtoSet(Set<Person> persons) {
        Set<PersonDto> personDtos = new HashSet<>();
        persons.forEach(person -> personDtos.add(createDto(person)));
        return personDtos;
    }
}
