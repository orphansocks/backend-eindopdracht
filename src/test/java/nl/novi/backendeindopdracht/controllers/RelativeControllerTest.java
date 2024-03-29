package nl.novi.backendeindopdracht.controllers;

import nl.novi.backendeindopdracht.dtos.relative.RelativeDto;
import nl.novi.backendeindopdracht.services.CustomUserDetailsService;
import nl.novi.backendeindopdracht.services.RelativeService;
import nl.novi.backendeindopdracht.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;


@WebMvcTest(RelativeController.class)
@AutoConfigureMockMvc(addFilters = true)
class RelativeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtUtil jwtUtil;

    @MockBean
    CustomUserDetailsService customUserDetailsService;

    @MockBean
    RelativeService relativeService;

    @Test
    @WithMockUser(username="user", roles="USER")
    void shouldRetrieveCorrectRelative() throws Exception {

        RelativeDto relativeTestDto = new RelativeDto();

        relativeTestDto.id = 2L;
        relativeTestDto.amountOfKids = 2;
        relativeTestDto.dob = LocalDate.ofEpochDay(1980-10-10);
        relativeTestDto.firstName = "Jan";
        relativeTestDto.lastName = "Janssen";
        relativeTestDto.nameOfPartner = "Marie";
        relativeTestDto.nickName = "";
        relativeTestDto.hasKids = true;
        relativeTestDto.namesOfKids = "";
        relativeTestDto.misc = " type ier iets ";
        relativeTestDto.relation = "family";
        relativeTestDto.socialStatus = "married";

        Mockito.when(relativeService.getRelativeById(anyLong())).thenReturn(relativeTestDto);

        this.mockMvc
        .perform(MockMvcRequestBuilders.get("/relatives/2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is("Jan")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameOfPartner", is("Marie")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hasKids", is(true)));
    }


}