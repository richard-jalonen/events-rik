package ee.richja.backend.api.request;

import lombok.Data;

@Data
public class PersonCreateRequest {
    private String type;
    private String firstName;
    private String lastName;
    private String personCode;
    private Long participantCount;
    private String paymentType;
    private String additionalInfo;
}
