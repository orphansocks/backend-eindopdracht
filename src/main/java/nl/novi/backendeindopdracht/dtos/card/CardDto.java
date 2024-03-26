package nl.novi.backendeindopdracht.dtos.card;

import nl.novi.backendeindopdracht.models.ImageData;

public class CardDto {

    public Long id;
    public String cardName;
    public String designedBy;
    public String category;
    public Integer amountOfDownloads;
    public ImageData imageData;

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

    public String getDesignedBy() {
        return designedBy;
    }

    public void setDesignedBy(String designedBy) {
        this.designedBy = designedBy;
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
