package ee.richja.backend.repository;

import ee.richja.backend.domain.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
    Person findByPersonCode(String personCode);
}
