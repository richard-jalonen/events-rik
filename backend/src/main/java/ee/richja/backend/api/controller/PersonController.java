package ee.richja.backend.api.controller;

import ee.richja.backend.api.request.PersonCreateRequest;
import ee.richja.backend.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/persons")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PostMapping
    public ResponseEntity<Void> createPerson(@RequestBody PersonCreateRequest request) {
        personService.create(request);
        return ResponseEntity.ok().build();
    }
}
