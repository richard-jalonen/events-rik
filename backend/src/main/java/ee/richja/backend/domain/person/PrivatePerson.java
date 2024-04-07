package ee.richja.backend.domain.person;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("PRIVATE")
public class PrivatePerson extends Person {
    @NotEmpty
    private String lastName;
}
