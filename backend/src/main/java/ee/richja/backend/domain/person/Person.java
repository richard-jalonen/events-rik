package ee.richja.backend.domain.person;

import ee.richja.backend.domain.AggregateRoot;
import ee.richja.backend.properties.PaymentProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorColumn(name = "type")
public abstract class Person extends AggregateRoot {
    @Transient
    @Autowired
    PaymentProperties paymentProperties;

    @Id
    @GeneratedValue
    private UUID uuid;
    @NotEmpty
    private String personCode;
    @NotEmpty
    private String paymentType;

    public abstract String getName();

    public void setPaymentType(String paymentType) {
        if (!paymentProperties.getTypes().contains(paymentType)) {
            throw new IllegalArgumentException("Invalid payment type: " + paymentType);
        }
        this.paymentType = paymentType;
    }
}
