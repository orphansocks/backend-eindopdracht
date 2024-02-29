package nl.novi.backendeindopdracht.repositories;

import nl.novi.backendeindopdracht.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}

