package ee.richja.backend.api.controller;

import ee.richja.backend.api.request.PersonUpdateRequest;
import ee.richja.backend.api.response.PersonDto;
import ee.richja.backend.domain.person.Person;
import ee.richja.backend.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static ee.richja.backend.api.response.PersonDto.createDto;


@Slf4j
@Controller
@RequestMapping("/api/v1/persons")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping("/{uuid}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable UUID uuid) {
        Person person = personService.getPersonByUuid(uuid);
        if (person != null) {
            log.info("Found person {}", person.getUuid());
            return ResponseEntity.ok(createDto(person));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> updatePerson(@RequestBody PersonUpdateRequest request) {
        personService.update(request);
        return ResponseEntity.noContent().build();
    }
}
