package ee.richja.backend.api.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventCreateRequest {
    private String name;
    private LocalDateTime time;
    private String location;
    private String additionalInfo;
}
