package ee.richja.backend.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class PersonUpdateRequest extends PersonCreateRequest {
    private UUID uuid;
}
