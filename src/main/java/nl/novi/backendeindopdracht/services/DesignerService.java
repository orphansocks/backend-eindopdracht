package nl.novi.backendeindopdracht.services;

import nl.novi.backendeindopdracht.dtos.card.CardDto;
import nl.novi.backendeindopdracht.dtos.designer.DesignerDto;
import nl.novi.backendeindopdracht.dtos.designer.DesignerInputDto;
import nl.novi.backendeindopdracht.exceptions.RecordNotFoundException;
import nl.novi.backendeindopdracht.models.Card;
import nl.novi.backendeindopdracht.models.Designer;
import nl.novi.backendeindopdracht.repositories.CardRepository;
import nl.novi.backendeindopdracht.repositories.DesignerRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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

        if (designerRepository.findById(id).isPresent()) {

            Designer designer = designerRepository.findById(id).get();

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

        Set<Card> cardSet = new HashSet<>();

        for (Long id : designerInputDto.cardIds) {

            Optional<Card> cardOptional = cardRepository.findById(id);

            if (cardOptional.isPresent()) {
                Card card = cardOptional.get();
                cardSet.add(card);

            } else {
                throw new RecordNotFoundException("Card with Id " + id + " not found");
            }
        }

            designer.setCards(cardSet);

            designerRepository.save(designer);

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

            Set<Card> cardSet = new HashSet<>();

            for (Card card : designer.getCards()) {

                CardDto cardDto = assignCardToDto(card);
                cardSet.add(card);
            }

            dto.setCards(cardSet);

            return dto;

        }

        private CardDto assignCardToDto(Card card) {

        CardDto dto = new CardDto();

        dto.setId(card.getId());
        dto.setCardName(card.getCardName());
        dto.setAmountOfDownloads(card.getAmountOfDownloads());

        return dto;
        }











}
