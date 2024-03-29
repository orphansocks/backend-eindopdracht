package nl.novi.backendeindopdracht.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = true)
@ActiveProfiles("test")
class DesignerProfileControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser(username="user", roles="USER")
    void shouldCreateCorrectDesignerProfile() throws Exception {

        String requestJson = """
             
                {
                     "company":"Company Name",
                     "firstname" :"Designer",
                     "lastname":"van Dongen",
                     "address":"Haarlem",
                     "url":"www.designcompany.nl",
                     "phone":"0612345678",
                     "bankAccount":"IBAN90BANK12345678"
                 }
                  
                """;

        MvcResult testResult = this.mockMvc

                .perform(MockMvcRequestBuilders.post("/designers")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String jsonResponse = testResult.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
        String createdCompany = jsonNode.get("company").asText().replace(" ", "%20");

        assertThat(testResult.getResponse().getHeader("Location"), matchesPattern("^.*/designers/" + createdCompany));

    }











}