package ee.richja.backend.domain.person;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("LEGAL")
public class LegalPerson extends Person {
    private Long participantCount;
}
