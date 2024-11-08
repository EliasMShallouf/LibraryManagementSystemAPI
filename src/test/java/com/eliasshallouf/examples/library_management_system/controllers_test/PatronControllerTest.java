package com.eliasshallouf.examples.library_management_system.controllers_test;

import com.eliasshallouf.examples.library_management_system.App;
import com.eliasshallouf.examples.library_management_system.domain.model.Patron;
import com.eliasshallouf.examples.library_management_system.service.db.PatronService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;
import static com.eliasshallouf.examples.library_management_system.service.utils.JsonHelper.asJsonString;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = App.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class PatronControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatronService patronService;

    @Test
    public void testGetAllPatrons() throws Exception {
        List<Patron> expectedPatrons = patronService.getAll();

        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/api/patrons")
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(expectedPatrons.size()));
    }

    @Test
    public void testFindPatronById() throws Exception {
        Long patronId = 2L;
        Patron patron = patronService.findById(patronId);

        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/api/patrons/{patronId}", patronId)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(patron.getName()));;
    }

    @Test
    public void testSavePatron() throws Exception {
        Patron newPatron = Patron.builder()
                .name("Mr.Java")
                .email("mr.java@gmail.com")
                .build();

        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/api/patrons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newPatron))
        )
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdatePatron() throws Exception {
        Long patronId = 3L;
        Patron updatedPatron = Patron.builder()
                .name("Mr.Java")
                .email("mr.j@gmail.com")
                .build();

        mockMvc.perform(
            MockMvcRequestBuilders
                .put("/api/patrons/{patronId}", patronId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updatedPatron))
        )
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeletePatron() throws Exception {
        Long patronId = 7L;

        mockMvc.perform(
            MockMvcRequestBuilders
                .delete("/api/patrons/{patronId}", patronId)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
