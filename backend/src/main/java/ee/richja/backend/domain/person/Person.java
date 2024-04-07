package ee.richja.backend.domain.person;

import ee.richja.backend.domain.AggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorColumn(name = "type")
public abstract class Person extends AggregateRoot {
    @Id
    @GeneratedValue
    private UUID uuid;
    @NotEmpty
    private String paymentType;
    @NotEmpty
    @Size(min = 8, max = 11)
    private String personCode;

    public abstract String getName();

    @Override
    @PrePersist
    public void prePersist() {
        if (paymentType != null) {
            paymentType = paymentType.toUpperCase();
        }
        super.prePersist();
    }
}
