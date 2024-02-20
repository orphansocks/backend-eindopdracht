package nl.novi.backendeindopdracht.repositories;

import nl.novi.backendeindopdracht.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    public List<Group> findAllByGroupNameEqualsIgnoreCase(String groupName);
    public List<Group> findAllByGroupPlaceEqualsIgnoreCase(String groupPlace);

}
