package nl.novi.backendeindopdracht.controller;

import jakarta.validation.Valid;
import nl.novi.backendeindopdracht.dtos.relative.RelativeDto;
import nl.novi.backendeindopdracht.dtos.relative.RelativeInputDto;

import nl.novi.backendeindopdracht.services.RelativeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/relatives")
public class RelativeController {

private final RelativeService relativeService;

   public RelativeController (RelativeService relativeService) {
       this.relativeService = relativeService;
   }


    @GetMapping("")
    public ResponseEntity<List<RelativeDto>> getAllRelatives() {

       List<RelativeDto> relativeDtos = relativeService.getAllRelatives();

        return ResponseEntity.ok().body(relativeDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelativeDto> getRelative(@PathVariable("id") Long id) {

       RelativeDto relativeDto = relativeService.getRelativeById(id);

        return ResponseEntity.ok().body(relativeDto);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<RelativeDto>> getRelativesByName(@PathVariable String name ) {
        List<RelativeDto> relativeDtos;

        if (name.isEmpty()) {
            relativeDtos = relativeService.getAllRelatives();
        } else {
            relativeDtos = relativeService.getAllRelativesByName(name);
        }
        return ResponseEntity.ok().body(relativeDtos);
    }


    @PostMapping("")
    public ResponseEntity<RelativeDto> createRelative(@RequestBody RelativeInputDto relativeInputDto) {

            RelativeDto relativeDto = relativeService.createRelative(relativeInputDto);

        URI uri = URI.create (
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + relativeDto.getId()).toUriString());

        return ResponseEntity.created(uri).body(relativeDto);

//            return ResponseEntity.created(null).body(relativeDto);
        }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRelative(@PathVariable Long id, @RequestBody RelativeInputDto newRelativeInput) {

       RelativeDto relativeDto = relativeService.updateRelative(id, newRelativeInput);

            return ResponseEntity.ok().body(relativeDto);
        }


@DeleteMapping("/{id}")
    public ResponseEntity<Object> DeleteRelative(@PathVariable Long id) {

       relativeService.deleteRelative(id);

       return ResponseEntity.noContent().build();
    }


}
