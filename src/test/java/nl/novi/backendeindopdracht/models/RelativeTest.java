package nl.novi.backendeindopdracht.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

class RelativeTest {
    @Test
    @DisplayName("should keep relativename")
    public void testRelative() {

        // parse the date string into a LocalDate object
        LocalDate dob = LocalDate.parse("1976-06-10");

        // arrange
        Relative testRelative = new Relative(1L, "Eva", "van Dongen", "Eef", dob, "Together", "Freek", true, 2, "Fynn en Luz", "Keep it simple", "Family", null);
        testRelative.setId(1L);

//        // Create a Group and add the relative to it
//        Group group = new Group();
//        Set<Relative> relatives = new HashSet<>();
//        relatives.add(testRelative);
//        group.setRelatives(relatives);
//
//        // Set the group to the relative
//        Set<Group> groups = new HashSet<>();
//        groups.add(group);
//        testRelative.setGroups(groups);

        // Act
        String result = testRelative.getFirstName();

        // Assert
        assertEquals("Eva", result);

    }
}
