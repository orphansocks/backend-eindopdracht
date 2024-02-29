package nl.novi.backendeindopdracht.controllers;

import nl.novi.backendeindopdracht.models.Card;
import nl.novi.backendeindopdracht.models.ImageData;
import nl.novi.backendeindopdracht.repositories.ImageDataRepository;
import nl.novi.backendeindopdracht.repositories.CardRepository;
import nl.novi.backendeindopdracht.services.ImageDataService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping ("")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile multipartFile, @RequestParam String imageName) throws IOException {

        String image = imageDataService.uploadImage(multipartFile, imageName);

        return ResponseEntity.ok("file has been uploaded, " + image);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> downloadImage(@PathVariable("id") Long id) throws IOException {

        byte[] image = imageDataService.downloadImage(id);

        Optional<Card> cardOptional = cardRepository.findById(id);
        Optional<ImageData> imageDataOptional = imageDataRepository.findById(cardOptional.get().getImageData().getId());

        MediaType mediaType = MediaType.valueOf(imageDataOptional.get().getType());

        return ResponseEntity.ok().contentType(mediaType).body(image);

    }

}
