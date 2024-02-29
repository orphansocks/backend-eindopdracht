package nl.novi.backendeindopdracht.services;

import nl.novi.backendeindopdracht.repositories.CardRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository cardRepository;


    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }





}
