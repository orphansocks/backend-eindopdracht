package nl.novi.backendeindopdracht.repositories;

import nl.novi.backendeindopdracht.models.Relative;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelativeRepository extends JpaRepository<Relative, Long> {
    public List<Relative> findAllByFirstNameEqualsIgnoreCase(String firstName);
    public List<Relative> findAllByLastNameEqualsIgnoreCase(String firstName);


}
