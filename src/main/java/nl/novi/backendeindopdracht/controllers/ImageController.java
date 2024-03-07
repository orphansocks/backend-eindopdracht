package nl.novi.backendeindopdracht.controllers;

import nl.novi.backendeindopdracht.dtos.card.CardDto;
import nl.novi.backendeindopdracht.dtos.card.CardInputDto;
import nl.novi.backendeindopdracht.models.Card;
import nl.novi.backendeindopdracht.models.ImageData;
import nl.novi.backendeindopdracht.repositories.ImageDataRepository;
import nl.novi.backendeindopdracht.repositories.CardRepository;
import nl.novi.backendeindopdracht.services.ImageDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageDataService imageDataService;
    private final ImageDataRepository imageDataRepository;
    private final CardRepository cardRepository;

    public ImageController(ImageDataService imageDataService, ImageDataRepository imageDataRepository, CardRepository cardRepository) {
        this.imageDataService = imageDataService;
        this.imageDataRepository = imageDataRepository;
        this.cardRepository = cardRepository;
    }

//    @PostMapping ("")
//    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile multipartFile, @RequestParam Long id) throws IOException {
//        Optional<Card> cardOptional = cardRepository.findById(id);
//
//        if (cardOptional.isPresent()) {
//            String image = imageDataService.uploadImage(multipartFile, id);
//            return ResponseEntity.ok("File has been uploaded for card with ID " + id + ": " + image);
//        } else {
//            // Handle the case where the card with the provided ID is not found
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PostMapping ("")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile multipartFile) throws IOException {

            String image = imageDataService.uploadImage(multipartFile);
            return ResponseEntity.ok("File has been uploaded as " + image);

    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> downloadImage(@PathVariable("id") Long id) throws IOException {

        byte[] image = imageDataService.downloadImage(id);

        Optional<ImageData> imageDataOptional = imageDataRepository.findById(id);
        MediaType mediaType = MediaType.valueOf(imageDataOptional.get().getType());

        return ResponseEntity.ok().contentType(mediaType).body(image);

    }


}
