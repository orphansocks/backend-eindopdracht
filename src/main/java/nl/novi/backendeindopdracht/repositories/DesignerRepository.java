package nl.novi.backendeindopdracht.repositories;

import nl.novi.backendeindopdracht.models.DesignerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignerRepository extends JpaRepository<DesignerProfile, String> {
}
