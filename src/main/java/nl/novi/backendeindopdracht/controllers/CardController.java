package nl.novi.backendeindopdracht.controllers;

import nl.novi.backendeindopdracht.models.CardData;
import nl.novi.backendeindopdracht.models.User;
import nl.novi.backendeindopdracht.repositories.CardDataRepository;
import nl.novi.backendeindopdracht.repositories.UserRepository;
import nl.novi.backendeindopdracht.services.CardDataService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardDataService cardDataService;
    private final CardDataRepository cardDataRepository;
    private final UserRepository userRepository;

    public CardController(CardDataService cardDataService, CardDataRepository cardDataRepository, UserRepository userRepository) {
        this.cardDataService = cardDataService;
        this.cardDataRepository = cardDataRepository;
        this.userRepository = userRepository;
    }

    @PostMapping ("")
    public ResponseEntity<String> uploadCard(@RequestParam("file") MultipartFile multipartFile, @RequestParam String username) throws IOException {

        String card = cardDataService.uploadCard(multipartFile, username);

        return ResponseEntity.ok("file has been uploaded, " + card);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Object> downloadCard(@PathVariable("username") String username) throws IOException {

        byte[] card = cardDataService.downloadCard(username);
        Optional<User> user = userRepository.findById(username);
        Optional<CardData> cardDataOptional = cardDataRepository.findById(user.get().getCardData().getId());
        MediaType mediaType = MediaType.valueOf(cardDataOptional.get().getType());

        return ResponseEntity.ok().contentType(mediaType).body(card);

    }

}
