package ee.richja.backend.repository.person;

import ee.richja.backend.domain.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}
