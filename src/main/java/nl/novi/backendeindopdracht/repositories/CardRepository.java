package nl.novi.backendeindopdracht.repositories;

import nl.novi.backendeindopdracht.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByCardNameEqualsIgnoreCase(String cardName);
}
