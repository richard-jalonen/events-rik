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
    private String firstName;
    @NotEmpty
    @Size(min = 8, max = 11)
    @Column(unique = true)
    private String personCode;
}
