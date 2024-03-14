package nl.novi.backendeindopdracht.services;

import nl.novi.backendeindopdracht.dtos.card.CardDto;
import nl.novi.backendeindopdracht.dtos.designer.DesignerDto;
import nl.novi.backendeindopdracht.dtos.designer.DesignerInputDto;
import nl.novi.backendeindopdracht.exceptions.RecordNotFoundException;
import nl.novi.backendeindopdracht.models.Card;
import nl.novi.backendeindopdracht.models.Designer;
import nl.novi.backendeindopdracht.models.ImageData;
import nl.novi.backendeindopdracht.repositories.CardRepository;
import nl.novi.backendeindopdracht.repositories.DesignerRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DesignerService {

    private final DesignerRepository designerRepository;
    private final CardRepository cardRepository;

    public DesignerService(DesignerRepository designerRepository, CardRepository cardRepository) {
        this.designerRepository = designerRepository;
        this.cardRepository = cardRepository;
    }

    public DesignerDto createDesignerProfile(DesignerInputDto designerInputDto) {

        Designer designer = transferToEntity(designerInputDto);
        designerRepository.save(designer);

        return transferToDto(designer);


    }

    public DesignerDto getDesignerById(Long id) {

        Optional<Designer> optionalDesigner = designerRepository.findById(id);

        if (optionalDesigner.isPresent()) {

            Designer designer = optionalDesigner.get();
            return transferToDto(designer);

        } else {
            throw new RecordNotFoundException("No designer found");
        }
    }



    // DE TRANSFERS

    public Designer transferToEntity(DesignerInputDto designerInputDto) {
        Designer designer = new Designer();
        designer.setCompany(designerInputDto.company);
        designer.setLastname(designerInputDto.lastname);
        designer.setFirstname(designerInputDto.firstname);
        designer.setAddress(designerInputDto.address);
        designer.setUrl(designerInputDto.url);
        designer.setPhone(designerInputDto.phone);
        designer.setBankAccount(designerInputDto.bankAccount);

        // Save the designer to get an ID
        designer = designerRepository.save(designer);

        // Fetch cards associated with the designer's ID from the card repository
        Set<Card> cardsByDesigner = cardRepository.findByDesignerId(designer.getId());

        // Add fetched cards to the designer's set of cards
        designer.getCards().addAll(cardsByDesigner);

        return designer;
    }



    public DesignerDto transferToDto(Designer designer) {

        DesignerDto dto = new DesignerDto();

        dto.setId(designer.getId());
        dto.setCompany(designer.getCompany());
        dto.setFirstname(designer.getFirstname());
        dto.setLastname(designer.getLastname());
        dto.setAddress(designer.getAddress());
        dto.setUrl(designer.getUrl());
        dto.setPhone(designer.getPhone());
        dto.setBankAccount(designer.getBankAccount());

        Set<Card> cardsByDesigner = new HashSet<>();

        for (Card card : designer.getCards()) {

            CardDto cardDto = assignCardToDto(card);
            cardsByDesigner.add(card);
        }

        dto.setCards(cardsByDesigner);
        return dto;
    }

    private CardDto assignCardToDto(Card card) {

        CardDto dto = new CardDto();

        dto.setId(card.getId());
        dto.setCardName(card.getCardName());
        dto.setDesignedBy(card.getDesignedBy());
        dto.setCardName(card.getCategory());
        dto.setAmountOfDownloads(card.getAmountOfDownloads());
        dto.setImageData(card.getImageData());


        return dto;
    }







}
