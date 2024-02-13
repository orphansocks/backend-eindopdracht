package nl.novi.backendeindopdracht.repositories;

import nl.novi.backendeindopdracht.model.Relative;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelativeRepository extends JpaRepository<Relative, Long> {
    public List<Relative> findAllByFirstNameEqualsIgnoreCase(String firstName);


}
