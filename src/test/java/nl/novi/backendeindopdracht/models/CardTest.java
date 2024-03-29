package nl.novi.backendeindopdracht.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    @DisplayName("should keep cardName")
    public void testCardName() {

        // Create a DesignerProfile instance
        DesignerProfile designerProfile = new DesignerProfile();
        // Create an ImageData instance
        ImageData imageData = new ImageData();

        // Arrange
        Card testCard = new Card(
                3L, "HelloCard", designerProfile, "birthday",
                21, "designer", imageData
        );

        // Act
        String result = testCard.getCardName();

        // Assert
        assertEquals("HelloCard", result);
    }
}