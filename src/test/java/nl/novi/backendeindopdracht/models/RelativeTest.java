package nl.novi.backendeindopdracht.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

class RelativeTest {
    @Test
    @DisplayName("should keep relativeName")
    public void testRelative() {

        // Parse the date string into a LocalDate object
        LocalDate dob = LocalDate.parse("1976-06-10");

        // Arrange
        Relative testRelative = new Relative();

        testRelative.setId(3L);
        testRelative.setFirstName("Eva");
        testRelative.setLastName("van Dongen");
        testRelative.setNickName("Eef");
        testRelative.setDob(dob);
        testRelative.setSocialStatus("together");
        testRelative.setNameOfPartner("Freek");
        testRelative.setHasKids(true);
        testRelative.setAmountOfKids(2);
        testRelative.setNamesOfKids("Fynn en Luz");
        testRelative.setMisc("keep it simple");
        testRelative.setRelation("family");

        // Create a Group object and add the relative to it
        Group group = new Group();
        Set<Relative> relatives = new HashSet<>();
        relatives.add(testRelative);
        group.setRelatives(relatives);

        // Set the group to the relative
        Set<Group> groups = new HashSet<>();
        groups.add(group);
        testRelative.setGroups(groups);

        // Act
        String result = testRelative.getFirstName();

        // Assert
        assertEquals("Eva", result);

    }
}
