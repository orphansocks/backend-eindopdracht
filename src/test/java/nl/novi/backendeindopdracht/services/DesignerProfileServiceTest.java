package nl.novi.backendeindopdracht.services;

import nl.novi.backendeindopdracht.dtos.designerProfile.DesignerProfileDto;
import nl.novi.backendeindopdracht.models.DesignerProfile;
import nl.novi.backendeindopdracht.repositories.DesignerProfileRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DesignerProfileServiceTest {

    @Mock
    DesignerProfileRepository designerProfileRepository;

    @InjectMocks
    DesignerProfileService designerProfileService;

    @Test
    @DisplayName("return designerProfile")
    public void shouldReturnDesignerProfile() {

        // arrange
        DesignerProfile testDesignerProfile = new DesignerProfile(2L, "CompanyName", "Ans", "Maas", "Appelstraat 23", "companyname.url", "0612345678", "IBAN00ABCB12345679");
        testDesignerProfile.setId(2L);

        when(designerProfileRepository.findById(anyLong())).thenReturn(Optional.of(testDesignerProfile));

        // act
        DesignerProfileDto dto = designerProfileService.getDesignerById(2L);

        // assert
        assertEquals(2L, dto.id );
        assertEquals("CompanyName", dto.company );
        assertEquals("Ans", dto.firstname );
        assertEquals("0612345678", dto.phone );


    }




}