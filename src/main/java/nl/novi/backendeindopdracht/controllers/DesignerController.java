package nl.novi.backendeindopdracht.controllers;

import nl.novi.backendeindopdracht.dtos.designer.DesignerDto;
import nl.novi.backendeindopdracht.dtos.designer.DesignerInputDto;
import nl.novi.backendeindopdracht.services.DesignerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/designers")
public class DesignerController {

    private final DesignerService designerService;

    public DesignerController(DesignerService designerService) {
        this.designerService = designerService;
    }

@PostMapping("")
    public ResponseEntity<DesignerDto> createDesignerProfile(@RequestBody DesignerInputDto designerInputDto) {

        DesignerDto designerDto = designerService.createDesignerProfile(designerInputDto);

    URI uri = URI.create (
            ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/" + designerDto.getCompany()).toUriString());

    return ResponseEntity.created(uri).body(designerDto);

}

@GetMapping("/{id}")
    public ResponseEntity<DesignerDto> getDesignerById(@PathVariable("id") Long id) {
        DesignerDto designerDto = designerService.getDesignerById(id);

        return ResponseEntity.ok().body(designerDto);
}



}
