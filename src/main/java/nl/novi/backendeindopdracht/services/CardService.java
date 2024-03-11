package nl.novi.backendeindopdracht.services;

import nl.novi.backendeindopdracht.exceptions.DuplicateException;
import nl.novi.backendeindopdracht.exceptions.RecordNotFoundException;
import nl.novi.backendeindopdracht.models.Card;
import nl.novi.backendeindopdracht.dtos.card.CardInputDto;
import nl.novi.backendeindopdracht.dtos.card.CardDto;
import nl.novi.backendeindopdracht.models.ImageData;
import nl.novi.backendeindopdracht.repositories.CardRepository;
import nl.novi.backendeindopdracht.repositories.ImageDataRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final ImageDataRepository imageDataRepository;


    public CardService(CardRepository cardRepository, ImageDataRepository imageDataRepository) {
        this.cardRepository = cardRepository;
        this.imageDataRepository = imageDataRepository;
    }

    public CardDto createCard(CardInputDto cardInputDto) {

        Card card = transferToEntity(cardInputDto);

        String cardName = card.getCardName();

        List<Card> cardsWithMatchingNames = cardRepository.findAllByCardNameEqualsIgnoreCase(cardName);

        if(cardsWithMatchingNames.isEmpty()) {
            cardRepository.save(card);
            return transferToDto(card);
        } else {
            throw new DuplicateException("A card with this name already exists");
        }
    }


    public List<CardDto> getAllCards() {

        List<CardDto> collection = new ArrayList<>();
        List<Card> cardList = cardRepository.findAll();

        for(Card card : cardList) {
            collection.add(transferToDto(card));
        }

        return collection;

}

    public CardDto getCardById(Long id) {

        if (cardRepository.findById(id).isPresent()) {

           Card card = cardRepository.findById(id).get();

            return transferToDto(card);
        } else {
            throw new RecordNotFoundException("No card found");
        }
    }

    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }

    public CardDto updateCard(Long id, CardInputDto cardNewInputDto) {
        if(cardRepository.findById(id).isPresent()) {

            Card card = cardRepository.findById(id).get();

            Card newInputCard = transferToEntity(cardNewInputDto);
            newInputCard.setId(card.getId());

            cardRepository.save(newInputCard);
            return transferToDto(newInputCard);

        } else {
            throw new RecordNotFoundException("No card found");
        }
    }

// DE TRANSFERS

    public Card transferToEntity(CardInputDto dto) {
        Card card = new Card();

        card.setCardName(dto.cardName);
        card.setCategory(dto.category);

        if(dto.imageId != null && dto.imageId != 0) {
            Optional<ImageData> optionalImageData = imageDataRepository.findById(dto.imageId);

            if(optionalImageData.isPresent()) {
                ImageData imageData = optionalImageData.get();
                card.setImageData(imageData); // Set the retrieved ImageData to the card
            } else {
                throw new RecordNotFoundException("Image for card is not found");
            }
        }

        return card;
    }


    public CardDto transferToDto(Card card) {

        CardDto dto = new CardDto();

        dto.setId(card.getId());
        dto.setCardName(card.getCardName());
//         dto.setDesigner(designerProfile.getDesigner());
        dto.setCategory(card.getCategory());
        dto.setAmountOfDownloads(card.getAmountOfDownloads());


           if(card.getImageData() != null) {
               dto.setImageData(card.getImageData());
            }

        return dto;
    }



    public CardDto assignImageToCard(Long id, Long imageId) {

        Optional<Card> cardOptional = cardRepository.findById(id);
        Optional<ImageData> imageOptional = imageDataRepository.findById(imageId);

        if (cardOptional.isPresent() && imageOptional.isPresent()) {
            Card card = cardOptional.get();
            ImageData imageData = imageOptional.get();

            card.setImageData(imageData);
            cardRepository.save(card);

            return transferToDto(card);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public List<CardDto> transferEntityListToDtoList(List<Card> cards) {

        List<CardDto> cardDtoList = new ArrayList<>();

        for(Card card : cards) {

          CardDto cardDto = transferToDto(card);
            cardDtoList.add(cardDto);
        }


        return cardDtoList;
    }


}
