package ee.richja.backend.domain.event;

import ee.richja.backend.domain.person.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(exclude = {"event"})
public class EventParticipant {
    @Id
    @GeneratedValue
    private UUID uuid;
    @NotEmpty
    private String paymentType;
    @Column(length = 5000)
    private String additionalInfo;

    @ManyToOne
    @JoinColumn(name = "person_uuid")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "event_uuid")
    private Event event;

    @PrePersist
    public void prePersist() {
        if (paymentType != null) {
            paymentType = paymentType.toUpperCase();
        }
    }
}
