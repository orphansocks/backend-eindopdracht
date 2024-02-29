package nl.novi.backendeindopdracht.exceptions;

public class CardDataNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CardDataNotFoundException() {

        super();

    }

    public CardDataNotFoundException(String message) {

        super(message);

    }

}