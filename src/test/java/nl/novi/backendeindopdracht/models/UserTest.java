package nl.novi.backendeindopdracht.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

   @Test
   @DisplayName("should keep username")
   public void testUsername() {

       // Create a Role instance
       Role role = new Role();
       Set<Role> roles = new HashSet<>();
       roles.add(role);

        // Arrange
        User testUser = new User("Jan", "Pass1234", "jan@email.nl", "USER", true);

        // Act
        String result = testUser.getUsername();

        // Assert
        assertEquals("Jan", result);
    }
}
