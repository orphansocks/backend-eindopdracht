package nl.novi.backendeindopdracht.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DesignerProfileTest {

    @Test
    @DisplayName("should keep companyname")
    public void testCompanyName() {

        // Arrange
        DesignerProfile testDesignerProfile = new DesignerProfile(3L, "This Company", "Janssen", "DesignerJan", "Straat 10, Amsterdam", "thiscompany.nl", "0201234567", "IBAN00ABCD1234567", null );

        // Act
        String result = testDesignerProfile.getCompany();

        // Assert
        assertEquals("This Company", result);


    }

}