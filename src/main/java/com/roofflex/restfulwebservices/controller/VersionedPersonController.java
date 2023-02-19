package com.roofflex.restfulwebservices.controller;

import com.roofflex.restfulwebservices.model.PersonV1;
import com.roofflex.restfulwebservices.model.PersonV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.roofflex.restfulwebservices.model.Name.name;
import static com.roofflex.restfulwebservices.model.PersonV1.personV1;
import static com.roofflex.restfulwebservices.model.PersonV2.personV2;

@RestController()
public class VersionedPersonController {

    private static final List<PersonV1> personV1List = List.of(
            personV1("Bob Charlie"),
            personV1("Robert Corney"),
            personV1("Jane Hopkins")
    );

    private static final List<PersonV2> personV2List = List.of(
            personV2(name("Bob", "Charlie")),
            personV2(name("Robert", "Corney")),
            personV2(name("Jane", "Hopkins"))
    );

    @GetMapping("/v1/persons")
    public ResponseEntity<List<PersonV1>> getFirstVersionPersons() {
        return ResponseEntity.ok()
                .body(personV1List);
    }

    @GetMapping("/v2/persons")
    public ResponseEntity<List<PersonV2>> getSecondVersionPersons() {
        return ResponseEntity.ok()
                .body(personV2List);
    }

    @GetMapping(path = "/param/persons", params = "version=1")
    public ResponseEntity<List<PersonV1>> getFirstVersionPersonsVersionedByParam() {
        return ResponseEntity.ok()
                .body(personV1List);
    }

    @GetMapping(path = "/param/persons", params = "version=2")
    public ResponseEntity<List<PersonV2>> getSecondVersionPersonsVersionedByParam() {
        return ResponseEntity.ok()
                .body(personV2List);
    }

    @GetMapping(path = "/header/persons", headers = "X-API-VERSION=1")
    public ResponseEntity<List<PersonV1>> getFirstVersionPersonsVersionedByHeaders() {
        return ResponseEntity.ok()
                .body(personV1List);
    }

    @GetMapping(path = "/header/persons", headers = "X-API-VERSION=2")
    public ResponseEntity<List<PersonV2>> getSecondVersionPersonsVersionedByHeaders() {
        return ResponseEntity.ok()
                .body(personV2List);
    }
}
