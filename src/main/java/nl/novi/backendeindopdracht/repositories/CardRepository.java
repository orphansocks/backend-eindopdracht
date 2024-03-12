package nl.novi.backendeindopdracht.repositories;

import nl.novi.backendeindopdracht.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByCardNameEqualsIgnoreCase(String cardName);
    List<Card> findAllByCardNameEqualsIgnoreCase(String cardName);
//    List<Card> findAllByDesignerEqualsIgnoreCase(String designer);
    List<Card> findAllByCategoryEqualsIgnoreCase(String category);

    Set<Card> findByDesignerId(Long designerId);

}
