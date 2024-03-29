package nl.novi.backendeindopdracht.services;

import nl.novi.backendeindopdracht.exceptions.CardDataNotFoundException;
import nl.novi.backendeindopdracht.exceptions.RecordNotFoundException;
import nl.novi.backendeindopdracht.models.Card;
import nl.novi.backendeindopdracht.models.ImageData;
import nl.novi.backendeindopdracht.repositories.ImageDataRepository;
import nl.novi.backendeindopdracht.repositories.CardRepository;
import nl.novi.backendeindopdracht.utils.ImageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageDataService {

    private final ImageDataRepository imageDataRepository;
    private final CardRepository cardRepository;

    public ImageDataService(ImageDataRepository imageDataRepository, CardRepository cardRepository) {
        this.imageDataRepository = imageDataRepository;
        this.cardRepository = cardRepository;
    }

//    public String uploadImage(MultipartFile multipartFile, Long id) throws IOException {
//
//        Optional<Card> cardOptional = cardRepository.findById(id);
//
//        if (cardOptional.isPresent()) {
//            Card card = cardOptional.get();
//
//            ImageData imageData = new ImageData();
//            imageData.setImageName(multipartFile.getName());
//            imageData.setType(multipartFile.getContentType());
//            imageData.setImageData(ImageUtil.compressImage(multipartFile.getBytes()));
//            imageData.setCard(card);
//
//            ImageData savedImage = imageDataRepository.save(imageData);
//            card.setImageData(savedImage);
//            cardRepository.save(card);
//
//            return savedImage.getImageName();
//        } else {
//            // Handle the case when the card is not found
//            throw new CardDataNotFoundException("Card is not found.");
//        }
//    }

    public String uploadImage(MultipartFile multipartFile) throws IOException {
        ImageData imageData = new ImageData();
        imageData.setImageName(multipartFile.getOriginalFilename());
        imageData.setType(multipartFile.getContentType());
        imageData.setImageData(ImageUtil.compressImage(multipartFile.getBytes()));

        ImageData savedImage = imageDataRepository.save(imageData);

        return savedImage.getImageName();
    }

    public Long uploadImageGetId(MultipartFile multipartFile) throws IOException {
        ImageData imageData = new ImageData();
        imageData.setImageName(multipartFile.getOriginalFilename());
        imageData.setType(multipartFile.getContentType());
        imageData.setImageData(ImageUtil.compressImage(multipartFile.getBytes()));

        ImageData savedImage = imageDataRepository.save(imageData);

        return savedImage.getId();
    }



    public byte[] downloadImage(Long id) throws IOException {



        if (imageDataRepository.findById(id).isPresent()) {

            ImageData imageData = imageDataRepository.findById(id).get();

                return ImageUtil.decompressImage(imageData.getImageData());
            } else {
                // Handle the case where the card data for the card is null
                throw new RecordNotFoundException("Image is not found" + id);
            }


    }
}
