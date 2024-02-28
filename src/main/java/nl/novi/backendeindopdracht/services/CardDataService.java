package nl.novi.backendeindopdracht.services;

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
        Optional<User> user = userRepository.findById(username);

        User user1 = user.get();
        CardData cardData = new CardData();

        cardData.setCardName(multipartFile.getName());
        cardData.setType(multipartFile.getContentType());
        cardData.setCardData(ImageUtil.compressImage(multipartFile.getBytes()));
        cardData.setUser(user1);

        CardData savedCard = cardDataRepository.save(cardData);
        user1.setCard(savedCard);
        userRepository.save(user1);

        return savedCard.getCardName();

    }

    public byte[] downloadCard(String username) throws IOException {
        Optional<User> user = userRepository.findById(username);
        User user1 = user.get();
        CardData cardData = user1.getCardData();

        return ImageUtil.decompressImage(cardData.getCardData());
    }


}
