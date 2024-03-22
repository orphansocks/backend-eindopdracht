package nl.novi.backendeindopdracht.services;

import nl.novi.backendeindopdracht.exceptions.DuplicateException;
import nl.novi.backendeindopdracht.exceptions.RecordNotFoundException;
import nl.novi.backendeindopdracht.models.Card;
import nl.novi.backendeindopdracht.dtos.card.CardInputDto;
import nl.novi.backendeindopdracht.dtos.card.CardDto;
import nl.novi.backendeindopdracht.models.DesignerProfile;
import nl.novi.backendeindopdracht.models.ImageData;
import nl.novi.backendeindopdracht.repositories.CardRepository;
import nl.novi.backendeindopdracht.repositories.DesignerProfileRepository;
import nl.novi.backendeindopdracht.repositories.ImageDataRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final ImageDataRepository imageDataRepository;
    private final DesignerProfileRepository designerProfileRepository;


    public CardService(CardRepository cardRepository, ImageDataRepository imageDataRepository, DesignerProfileRepository designerProfileRepository) {
        this.cardRepository = cardRepository;
        this.imageDataRepository = imageDataRepository;
        this.designerProfileRepository = designerProfileRepository;
    }

    public CardDto createCard(CardInputDto cardInputDto) {
        Card card = transferToEntity(cardInputDto);

        String cardName = card.getCardName();
        List<Card> cardsWithMatchingNames = cardRepository.findAllByCardNameEqualsIgnoreCase(cardName);

        if (cardsWithMatchingNames.isEmpty()) {
            // Save the card
            cardRepository.save(card);

            // Fetch the designer using designerId from cardInputDto
            Optional<DesignerProfile> designerOptional = designerProfileRepository.findById(cardInputDto.designerId);
            if (designerOptional.isPresent()) {
                DesignerProfile designerProfile = designerOptional.get();
                // Add the card to the designerProfile's card set
                designerProfile.getCards().add(card);
                designerProfileRepository.save(designerProfile);
                card.setDesignerProfile(designerProfile);
                cardRepository.save(card);
            } else {
                throw new RecordNotFoundException("DesignerProfile with id " + cardInputDto.designerId + " not found");
            }

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

//        if (dto.designerId != null) {
//            Optional<DesignerProfile> designerOptional = designerProfileRepository.findById(dto.designerId);
//            if (designerOptional.isPresent()) {
//
//                DesignerProfile designer = designerOptional.get();
//
//                String designedBy = designer.getCompany(); // Get the company of the designer
//                card.setDesigner(designer); // Set the retrieved DesignerProfile to the card
//                card.setDesignedBy(designedBy);
//            } else {
//                throw new RecordNotFoundException("DesignerProfile with id " + dto.designerId + " not found");
//            }
//        }

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
        dto.setCategory(card.getCategory());
        dto.setAmountOfDownloads(card.getAmountOfDownloads());
        dto.setDesignedBy(card.getDesignedBy());

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

    public CardDto assignDesignerToCard(Long id, Long designerId) {

        Optional<Card> cardOptional = cardRepository.findById(id);
        Optional<DesignerProfile> designerOptional = designerProfileRepository.findById(designerId);

        if(cardOptional.isPresent() && designerOptional.isPresent()) {
            Card card = cardOptional.get();
            DesignerProfile designerProfile = designerOptional.get();

            card.setDesignerProfile(designerProfile);
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
