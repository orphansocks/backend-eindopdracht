package nl.novi.backendeindopdracht.controllers;

import nl.novi.backendeindopdracht.dtos.card.CardDto;
import nl.novi.backendeindopdracht.dtos.card.CardInputDto;
import nl.novi.backendeindopdracht.services.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cards")
class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @PostMapping("")
    public ResponseEntity<CardDto> createCard(@RequestBody CardInputDto cardInputDto) {
        CardDto cardDto = cardService.createCard(cardInputDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + cardDto.getId()).toUriString());

        return  ResponseEntity.created(uri).body(cardDto);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }





}
