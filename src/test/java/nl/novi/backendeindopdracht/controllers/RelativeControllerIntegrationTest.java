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
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = true)
@ActiveProfiles("test")
class RelativeControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser(username="user", roles="USER")
    void shouldCreateCorrectRelative() throws Exception {

        String requestJson = """
             
                {
                "id":1,
                "firstName":"Jan",
                "lastName":"Klaassen",
                "nickName":"",
                "dob":"1982-06-10",
                "socialStatus": "married",
                "nameOfPartner": "Katrijn",
                "hasKids":false,
                "amountOfKids":2,
                "namesOfKids":"",
                "misc":"Getrouwd met Marie",
                "relation":"friend",
                "groups":null
          
                }
                  
                """;

        MvcResult testResult = this.mockMvc

                .perform(MockMvcRequestBuilders.post("/relatives")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String jsonResponse = testResult.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
        String createdId = jsonNode.get("id").asText();

        // check location field in response header (using Hamcrest regex matcher)
        assertThat(testResult.getResponse().getHeader("Location"), matchesPattern("^.*/relatives/" + createdId));
    }

    @Test
    @WithMockUser(username="user", roles="USER")
    void shouldGetCorrectRelative() throws Exception {

        String requestJson = """
                {
                    "id":1,
                "firstName":"Jan",
                "lastName":"Klaassen",
                "nickName":"",
                "dob":"1982-06-10",
                "socialStatus": "married",
                "nameOfPartner": "Katrijn",
                "hasKids":false,
                "amountOfKids":2,
                "namesOfKids":"",
                "misc":"Getrouwd met Marie",
                "relation":"friend",
                "groups":null
                }
                """;

        MvcResult testResult = this.mockMvc

                .perform(MockMvcRequestBuilders.post("/relatives")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String jsonResponse = testResult.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
        String createdId = jsonNode.get("id").asText();

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/relatives/" + createdId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is("Jan")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.socialStatus", is("married")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amountOfKids", is(2)));
    }


}