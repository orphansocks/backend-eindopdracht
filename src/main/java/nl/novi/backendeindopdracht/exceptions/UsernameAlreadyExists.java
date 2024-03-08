package nl.novi.backendeindopdracht.exceptions;

public class UsernameAlreadyExists extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UsernameAlreadyExists(String username) {
        super(username + " already exists");
    }

}