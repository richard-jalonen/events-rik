package ee.richja.backend.domain.event;


import ee.richja.backend.domain.AggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(length = 1000)
    private String additionalInfo;

    @Builder.Default
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private transient Set<EventParticipant> eventParticipants = new HashSet<>();

    public void addParticipant(EventParticipant eventParticipant) {
        eventParticipants.add(eventParticipant);
        eventParticipant.setEvent(this);
    }
}
