package nl.novi.backendeindopdracht.models;

import jakarta.persistence.*;

@Entity
@Table( name = "cards")
public class Card {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designer_profile_id")
    private DesignerProfile designerProfile;
    private String category;
    private Integer amountOfDownloads;
    private String designedBy;

    @OneToOne(mappedBy = "card")
    private ImageData imageData;


    // CONSTRUCTOR

    public Card() {
    }

    public Card(Long id, String cardName, DesignerProfile designerProfile, String category, Integer amountOfDownloads, String designedBy, ImageData imageData) {
        this.id = id;
        this.cardName = cardName;
        this.designerProfile = designerProfile;
        this.category = category;
        this.amountOfDownloads = amountOfDownloads;
        this.designedBy = designedBy;
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

    public DesignerProfile getDesignerProfile() {
        return designerProfile;
    }

    public void setDesignerProfile(DesignerProfile designerProfile) {
        this.designerProfile = designerProfile;
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

    public String getDesignedBy() {
        return designedBy;
    }

    public void setDesignedBy(String designedBy) {
        this.designedBy = designedBy;
    }

    public ImageData getImageData() {
        return imageData;
    }

    public void setImageData(ImageData imageData) {
        this.imageData = imageData;
    }
}
