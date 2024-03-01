package nl.novi.backendeindopdracht.models;

import jakarta.persistence.*;

@Entity
@Table( name = "cards")
public class Card {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardName;
    private String designer;
    private String category;
    private Integer amountOfDownloads;

    @OneToOne(mappedBy = "card")
    private ImageData imageData;


    // CONSTRUCTOR

    public Card() {
    }

    public Card(Long id, String cardName, String designer, String category, Integer amountOfDownloads, ImageData imageData) {
        this.id = id;
        this.cardName = cardName;
        this.designer = designer;
        this.category = category;
        this.amountOfDownloads = amountOfDownloads;
        this.imageData = imageData;
    }


    // GETTERS AND SETTERS


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

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getAmountOfDownloads() {
        return amountOfDownloads;
    }

    public void setAmountOfDownloads(Integer amountOfDownloads) {
        this.amountOfDownloads = amountOfDownloads;
    }

    public ImageData getImageData() {
        return imageData;
    }

    public void setImageData(ImageData imageData) {
        this.imageData = imageData;
    }
}
