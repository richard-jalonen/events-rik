package ee.richja.backend.domain.person;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("PRIVATE")
public class PrivatePerson extends Person {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @AttributeOverrides({
            @AttributeOverride(name = "additionalInfo", column = @Column(length = 1500))
    })
    private String additionalInfo;

    @Override
    public String getName() {
        return String.format("%s %s", firstName, lastName);
    }
}
