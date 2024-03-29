package nl.novi.backendeindopdracht.services;

import nl.novi.backendeindopdracht.dtos.group.GroupDto;
import nl.novi.backendeindopdracht.models.Group;
import nl.novi.backendeindopdracht.models.Relative;
import nl.novi.backendeindopdracht.repositories.GroupRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

    @Mock
    GroupRepository groupRepository;

    @InjectMocks
    GroupService groupService;

    @Test
    @DisplayName("return group")
    public void shouldReturnGroup() {

        // parse the date string into a LocalDate object
        LocalDate dob1 = LocalDate.parse("1977-05-10");
        LocalDate dob2 = LocalDate.parse("1988-04-05");

        // arrange
        Set<Relative> relatives = new HashSet<>();
        relatives.add(new Relative(null, "Karel", "Appel", "Peer", dob1, "Single", null, false, 0, null, null, "family", null));
        relatives.add(new Relative(null, "Ans", "Appel", "Ansje", dob2, "Single", null, false, 0, null, null, "neighbour", null));

        Group testGroup = new Group(1L, "Novi", "Utrecht", relatives);

        when(groupRepository.findById(anyLong())).thenReturn(Optional.of(testGroup));

        // act
        GroupDto dto = groupService.getGroupById(1L);

        // assert
        assertEquals(1l, dto.id);
        assertEquals("Novi", dto.groupName);
        assertEquals("Utrecht", dto.groupPlace);
        assertEquals(relatives, dto.relatives);


    }

}