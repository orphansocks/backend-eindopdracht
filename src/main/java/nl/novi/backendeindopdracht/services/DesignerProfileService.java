package nl.novi.backendeindopdracht.services;

import nl.novi.backendeindopdracht.dtos.card.CardDto;
import nl.novi.backendeindopdracht.dtos.designerProfile.DesignerProfileDto;
import nl.novi.backendeindopdracht.dtos.designerProfile.DesignerProfileInputDto;
import nl.novi.backendeindopdracht.exceptions.RecordNotFoundException;
import nl.novi.backendeindopdracht.models.Card;
import nl.novi.backendeindopdracht.models.DesignerProfile;
import nl.novi.backendeindopdracht.repositories.CardRepository;
import nl.novi.backendeindopdracht.repositories.DesignerProfileRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class DesignerProfileService {

    private final DesignerProfileRepository designerProfileRepository;
    private final CardRepository cardRepository;

    public DesignerProfileService(DesignerProfileRepository designerProfileRepository, CardRepository cardRepository) {
        this.designerProfileRepository = designerProfileRepository;
        this.cardRepository = cardRepository;
    }

//    public DesignerProfileDto createDesignerProfile(DesignerProfileInputDto designerProfileInputDto) {
//
//        DesignerProfile designerProfile = transferToEntity(designerProfileInputDto);
//        designerProfileRepository.save(designerProfile);
//
//        return transferToDto(designerProfile);
//
//    }

    public DesignerProfileDto createDesignerProfile(DesignerProfileInputDto designerProfileInputDto) {
        DesignerProfile designerProfile = transferToEntity(designerProfileInputDto);

        // Fetch cards associated with the designerProfile's ID from the card repository
        Set<Card> cardsByDesigner = cardRepository.findByDesignerProfileId(designerProfile.getId());

        // Add fetched cards to the designerProfile's set of cards
        designerProfile.getCards().addAll(cardsByDesigner);

        // Save the designerProfile with populated cards to the repository
        designerProfile = designerProfileRepository.save(designerProfile);

        // Return the DTO
        return transferToDto(designerProfile);
    }


    public DesignerProfileDto getDesignerById(Long id) {

        Optional<DesignerProfile> optionalDesigner = designerProfileRepository.findById(id);

        if (optionalDesigner.isPresent()) {

            DesignerProfile designerProfile = optionalDesigner.get();
            return transferToDto(designerProfile);

        } else {
            throw new RecordNotFoundException("No designer found");
        }
    }



    // DE TRANSFERS

    public DesignerProfile transferToEntity(DesignerProfileInputDto designerProfileInputDto) {
        DesignerProfile designerProfile = new DesignerProfile();
        designerProfile.setCompany(designerProfileInputDto.company);
        designerProfile.setLastname(designerProfileInputDto.lastname);
        designerProfile.setFirstname(designerProfileInputDto.firstname);
        designerProfile.setAddress(designerProfileInputDto.address);
        designerProfile.setUrl(designerProfileInputDto.url);
        designerProfile.setPhone(designerProfileInputDto.phone);
        designerProfile.setBankAccount(designerProfileInputDto.bankAccount);

        // Save the designerProfile to get an ID
        designerProfile = designerProfileRepository.save(designerProfile);

        // Fetch cards associated with the designerProfile's ID from the card repository
        Set<Card> cardsByDesigner = cardRepository.findByDesignerProfileId(designerProfile.getId());

        // Add fetched cards to the designerProfile's set of cards
        designerProfile.getCards().addAll(cardsByDesigner);

        return designerProfile;
    }



    public DesignerProfileDto transferToDto(DesignerProfile designerProfile) {

        DesignerProfileDto dto = new DesignerProfileDto();

        dto.setId(designerProfile.getId());
        dto.setCompany(designerProfile.getCompany());
        dto.setFirstname(designerProfile.getFirstname());
        dto.setLastname(designerProfile.getLastname());
        dto.setAddress(designerProfile.getAddress());
        dto.setUrl(designerProfile.getUrl());
        dto.setPhone(designerProfile.getPhone());
        dto.setBankAccount(designerProfile.getBankAccount());

        Set<CardDto> cardsByDesigner = new HashSet<>();

        for (Card card : designerProfile.getCards()) {

            CardDto cardDto = assignCardToDto(card);
            cardsByDesigner.add(cardDto);
        }

        dto.setCardDto(cardsByDesigner);
        return dto;
    }

    private CardDto assignCardToDto(Card card) {

        CardDto dto = new CardDto();

        dto.setId(card.getId());
        dto.setCardName(card.getCardName());
        dto.setDesignedBy(card.getDesignedBy());
        dto.setCategory(card.getCategory());
        dto.setAmountOfDownloads(card.getAmountOfDownloads());
        dto.setImageData(card.getImageData());


        return dto;
    }







}
