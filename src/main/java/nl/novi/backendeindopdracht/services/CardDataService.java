package nl.novi.backendeindopdracht.services;

import nl.novi.backendeindopdracht.exceptions.CardDataNotFoundException;
import nl.novi.backendeindopdracht.exceptions.UsernameNotFoundException;
import nl.novi.backendeindopdracht.models.CardData;
import nl.novi.backendeindopdracht.models.User;
import nl.novi.backendeindopdracht.repositories.CardDataRepository;
import nl.novi.backendeindopdracht.repositories.UserRepository;
import nl.novi.backendeindopdracht.utils.ImageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class CardDataService {

    private final CardDataRepository cardDataRepository;
    private final UserRepository userRepository;

    public CardDataService(CardDataRepository cardDataRepository, UserRepository userRepository) {
        this.cardDataRepository = cardDataRepository;
        this.userRepository = userRepository;
    }

    public String uploadCard(MultipartFile multipartFile, String username) throws IOException {
        Optional<User> userOptional = userRepository.findById(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            CardData cardData = new CardData();

            cardData.setCardName(multipartFile.getName());
            cardData.setType(multipartFile.getContentType());
            cardData.setCardData(ImageUtil.compressImage(multipartFile.getBytes()));
            cardData.setUser(user);

            CardData savedCard = cardDataRepository.save(cardData);
            user.setCard(savedCard);
            userRepository.save(user);

            return savedCard.getCardName();
        } else {
            // Handle the case where the user is not found
            throw new UsernameNotFoundException("User with username " + username + " not found.");
        }
    }


    public byte[] downloadCard(String username) throws IOException {
        Optional<User> userOptional = userRepository.findById(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            CardData cardData = user.getCardData();

            if (cardData != null) {
                return ImageUtil.decompressImage(cardData.getCardData());
            } else {
                // Handle the case where the card data for the user is null
                throw new CardDataNotFoundException("Card data not found for user with username " + username);
            }
        } else {
            // Handle the case where the user is not found
            throw new UsernameNotFoundException("User with username " + username + " not found.");
        }

    }
}
