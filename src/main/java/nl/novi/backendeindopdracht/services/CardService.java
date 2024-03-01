package nl.novi.backendeindopdracht.services;

import nl.novi.backendeindopdracht.exceptions.DuplicateException;
import nl.novi.backendeindopdracht.exceptions.RecordNotFoundException;
import nl.novi.backendeindopdracht.models.Card;
import nl.novi.backendeindopdracht.dtos.card.CardInputDto;
import nl.novi.backendeindopdracht.dtos.card.CardDto;
import nl.novi.backendeindopdracht.repositories.CardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;


    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
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
        List<Card> cardList = cardRepository.findAll();
        return transferEntityListToDtoList(cardList);

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
        card.setDesigner(dto.designer);
        card.setCategory(dto.category);

        return card;

    }

    public CardDto transferToDto(Card card) {

        CardDto dto = new CardDto();

        dto.setId(card.getId());
        dto.setCardName(card.getCardName());
        dto.setDesigner(card.getDesigner());
        dto.setCategory(card.getCategory());
        dto.setAmountOfDownloads(card.getAmountOfDownloads());


        return dto;

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
