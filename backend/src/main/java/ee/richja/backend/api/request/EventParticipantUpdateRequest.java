package ee.richja.backend.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class EventParticipantUpdateRequest extends EventParticipantCreateRequest {
    private UUID uuid;
}
