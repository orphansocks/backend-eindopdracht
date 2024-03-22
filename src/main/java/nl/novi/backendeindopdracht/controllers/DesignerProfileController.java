package nl.novi.backendeindopdracht.controllers;

import nl.novi.backendeindopdracht.dtos.designerProfile.DesignerProfileDto;
import nl.novi.backendeindopdracht.dtos.designerProfile.DesignerProfileInputDto;
import nl.novi.backendeindopdracht.services.DesignerProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/designers")
public class DesignerProfileController {

    private final DesignerProfileService designerProfileService;

    public DesignerProfileController(DesignerProfileService designerProfileService) {
        this.designerProfileService = designerProfileService;
    }

@PostMapping("")
    public ResponseEntity<DesignerProfileDto> createDesignerProfile(@RequestBody DesignerProfileInputDto designerProfileInputDto) {

        DesignerProfileDto designerProfileDto = designerProfileService.createDesignerProfile(designerProfileInputDto);

    URI uri = URI.create (
            ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/" + designerProfileDto.getCompany()).toUriString());

    return ResponseEntity.created(uri).body(designerProfileDto);

}

@GetMapping("/{id}")
    public ResponseEntity<DesignerProfileDto> getDesignerById(@PathVariable("id") Long id) {
        DesignerProfileDto designerProfileDto = designerProfileService.getDesignerById(id);

        return ResponseEntity.ok().body(designerProfileDto);
}




}
