package nl.novi.backendeindopdracht.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    @DisplayName("should keep username")
    public void testUserName() {

        // Arrange
        Role testRole = new Role("Peters", "ROLE_DESIGNER");

        // Act
        String result = testRole.getUsername();

        // Assert
        assertEquals("Peters", result);


    }



}