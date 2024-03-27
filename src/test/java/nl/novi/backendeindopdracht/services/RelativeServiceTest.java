package nl.novi.backendeindopdracht.services;

import nl.novi.backendeindopdracht.models.Relative;
import nl.novi.backendeindopdracht.dtos.relative.RelativeDto;
import nl.novi.backendeindopdracht.repositories.RelativeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RelativeServiceTest {

    @Mock
    RelativeRepository relativeRepository;

    @InjectMocks
    RelativeService relativeService;

    @Test
    @DisplayName("return relative")
    public void shouldReturnRelative() {

        // parse the date string into a LocalDate object
        LocalDate dob = LocalDate.parse("1976-06-10");

        // arrange
        Relative testRelative = new Relative(1L, "Eva", "van Dongen", "Eef", dob, "Together", "Freek", true, 2, "Fynn en Luz", "Keep it simple", "Family", null);
        testRelative.setId(1L);

        when(relativeRepository.findById(anyLong())).thenReturn(Optional.of(testRelative));

        // act
        RelativeDto dto = relativeService.getRelativeById(1L);

        // assert
        assertEquals(1L, dto.id );
        assertEquals("Eva", dto.firstName );
        assertEquals("Together", dto.socialStatus );
        assertEquals("Keep it simple", dto.misc );

    }



}