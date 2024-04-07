package ee.richja.backend.repository;

import ee.richja.backend.domain.event.EventParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventParticipantRepository extends JpaRepository<EventParticipant, UUID> {
}
