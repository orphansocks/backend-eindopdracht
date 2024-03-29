package nl.novi.backendeindopdracht.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    @Test
    @DisplayName("should keep groupname")
    public void testGroupName() {

        // Arrange
        Group testGroup = new Group(2L, "Tennisteam", "Haarlem", null);

        // Act
        String result = testGroup.getGroupName();


        // Assert
        assertEquals("Tennisteam", result);

    }

}