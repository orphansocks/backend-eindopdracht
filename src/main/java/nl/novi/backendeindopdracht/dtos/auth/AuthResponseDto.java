package nl.novi.backendeindopdracht.dtos.auth;

public class AuthResponseDto {

    private final String jwt;


    public AuthResponseDto(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
