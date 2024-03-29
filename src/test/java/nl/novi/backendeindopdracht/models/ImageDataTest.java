package nl.novi.backendeindopdracht.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageDataTest {

    @Test
    @DisplayName("should keep imagename")
    public void testImageName() {

        // create a card instance
        Card card = new Card();

        // new byte array
        byte[] imageData = new byte[]{1, 2, 3};

        // Arrange
        ImageData testImageData = new ImageData(2L, "Mooi plaatje","png", imageData, card );

        // Act
        String result = testImageData.getImageName();

        // Assert
        assertEquals("Mooi plaatje", result);


    }






}