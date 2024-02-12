package nl.novi.backendeindopdracht.controller;

import jakarta.servlet.Servlet;
import nl.novi.backendeindopdracht.exceptions.RecordNotFoundException;
import nl.novi.backendeindopdracht.model.Relative;

import nl.novi.backendeindopdracht.repositories.RelativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/relatives")
public class RelativeController {

    // spring: jij moet het juiste object injecteren = autowired

    @Autowired
    RelativeRepository relativeRepository;

    private ArrayList<Relative> relatives = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Relative>> getAllRelatives() {
        return ResponseEntity.ok(relativeRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Relative> getRelative(@PathVariable int id) {
        return new ResponseEntity<>(this.relatives.get(id), HttpStatus.OK);
    }

    @GetMapping("/{relativename}")
    public ResponseEntity<List<Relative>> getRelativeByName(@RequestParam String firstName) {
        return ResponseEntity.ok(relativeRepository.findAllByFirstNameEqualsIgnoreCase(firstName));
    }

    @PostMapping
    public ResponseEntity<Relative> createRelative(@RequestBody Relative relative) {
        relativeRepository.save(relative);
        URI uri = URI.create (
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + relative.getId()).toUriString());
        return ResponseEntity.created(uri).body(relative);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Relative> updateRelative(@PathVariable int id, @RequestBody Relative relative) {
        if (id >= 0 && id < this.relatives.size()) {
        this.relatives.set(id, relative);
        return new ResponseEntity<>(relative, HttpStatus.OK);
    }
        else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
}

@DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteRelative(@PathVariable int id) {
        if (id >= 0 && this.relatives.get(id) != null) {
            relatives.remove(id);
            return new ResponseEntity<>("relative successfully deleted", HttpStatus.OK);
        } else {
            throw new RecordNotFoundException("ID cannot be found");
        }
}


}
