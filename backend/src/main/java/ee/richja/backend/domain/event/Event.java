package ee.richja.backend.domain.event;


import ee.richja.backend.domain.AggregateRoot;
import ee.richja.backend.domain.person.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Event extends AggregateRoot {
    @Id
    @GeneratedValue
    private UUID uuid;
    @NotEmpty
    private String name;
    @NotNull
    @Future
    private LocalDateTime time;
    @NotEmpty
    private String location;
    private Long participantCount;
    @Column(length = 1000)
    private String additionalInfo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "event_participant",
            joinColumns = @JoinColumn(name = "event_uuid"),
            inverseJoinColumns = @JoinColumn(name = "person_uuid")
    )
    private Set<Person> participants;

    public void addParticipant(Person person) {
        participants.add(person);
    }
}
