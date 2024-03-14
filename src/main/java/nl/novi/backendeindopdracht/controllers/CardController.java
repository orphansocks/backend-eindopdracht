package nl.novi.backendeindopdracht.controllers;

import jakarta.validation.Valid;
import nl.novi.backendeindopdracht.dtos.card.CardDto;
import nl.novi.backendeindopdracht.dtos.card.CardInputDto;
import nl.novi.backendeindopdracht.models.Card;
import nl.novi.backendeindopdracht.services.CardService;
import nl.novi.backendeindopdracht.services.ImageDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cards")
class CardController {

    private final CardService cardService;
    private final ImageDataService imageDataService;

    public CardController(CardService cardService, ImageDataService imageDataService) {
        this.cardService = cardService;
        this.imageDataService = imageDataService;
    }

    //    @PostMapping("")
//    public ResponseEntity<CardDto> createCard(@RequestBody CardInputDto cardInputDto) {
//        CardDto cardDto = cardService.createCard(cardInputDto);
//
//        URI uri = URI.create(
//                ServletUriComponentsBuilder
//                        .fromCurrentRequest()
//                        .path("/" + cardDto.getId()).toUriString());
//
//        return  ResponseEntity.created(uri).body(cardDto);
//    }

    @PostMapping("")
    public ResponseEntity<CardDto> createCardWithImage(@RequestParam("file") MultipartFile file, @RequestParam("cardInputDto") CardInputDto cardInputDto) throws IOException {

        Long imageId = imageDataService.uploadImageGetId(file);

        CardDto cardDto = cardService.createCard(cardInputDto);

        // Assign the uploaded image to the newly created card
        cardService.assignImageToCard(cardDto.getId(), imageId);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + cardDto.getId()).toUriString());

        return ResponseEntity.created(uri).body(cardDto);
    }


    @PutMapping("/{id}/{imageId}")
        public ResponseEntity<CardDto> assignImageToCard(@PathVariable("id") Long id, @PathVariable("imageId") Long imageId) {
            cardService.assignImageToCard(id, imageId);
            return ResponseEntity.noContent().build();
        }



    @GetMapping("")
    public ResponseEntity<List<CardDto>> getAllCards() {
        List<CardDto> cardDtos = cardService.getAllCards();
        return ResponseEntity.ok().body(cardDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDto> getCard(@PathVariable("id") Long id) {
        CardDto cardDto = cardService.getCardById(id);
        return ResponseEntity.ok().body(cardDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCard(@PathVariable Long id, @RequestBody CardInputDto newCardInputDto) {
        CardDto cardDto = cardService.updateCard(id, newCardInputDto);
        return ResponseEntity.ok().body(cardDto);
    }



    @PutMapping("/{id}/{designerId}")
    public ResponseEntity<CardDto> assignDesignerToCard(@PathVariable("id") Long id, @PathVariable("designerId") Long designerId) {
        cardService.assignDesignerToCard(id, designerId);
        return ResponseEntity.noContent().build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }





}
