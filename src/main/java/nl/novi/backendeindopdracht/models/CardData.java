package nl.novi.backendeindopdracht.models;

import jakarta.persistence.*;

@Entity
@Table(name = "card_data")
public class CardData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardName;
    private String type;
    @Lob
    private byte[] cardData;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public CardData(){
    }

    public CardData(String cardName, String type, byte[] cardData, User user) {
        this.cardName = cardName;
        this.type = type;
        this.cardData = cardData;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getCardData() {
        return cardData;
    }

    public void setCardData(byte[] cardData) {
        this.cardData = cardData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
