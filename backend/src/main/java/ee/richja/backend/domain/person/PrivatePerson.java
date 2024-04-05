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
@DiscriminatorValue("PRIVATE")
public class PrivatePerson extends Person {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @Column(unique = true)
    @Size(min = 11, max = 11, message = "Private person code must have exactly 11 characters")
    @Pattern(regexp = "\\d{11}", message = "Person code must contain only numerical digits")
    private String personCode;
    @AttributeOverrides({
            @AttributeOverride(name = "additionalInfo", column = @Column(length = 1500))
    })
    private String additionalInfo;

    @Override
    public String getName() {
        return String.format("%s %s", firstName, lastName);
    }
}
