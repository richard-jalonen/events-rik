package ee.richja.backend.domain.person;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("LEGAL")
public class LegalPerson extends Person {
    @NotEmpty
    private String firstName;
    private Long participantCount;
    @AttributeOverrides({
            @AttributeOverride(name = "additionalInfo", column = @Column(length = 1500))
    })
    private String additionalInfo;

    @Override
    public String getName() {
        return firstName;
    }
}
