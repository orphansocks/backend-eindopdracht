package nl.novi.backendeindopdracht.dtos.user;

import jakarta.validation.constraints.NotNull;

public class UserInputDto {

    @NotNull(message = "Username is required")
    public String username;

    public String password;
    public String email;
}
