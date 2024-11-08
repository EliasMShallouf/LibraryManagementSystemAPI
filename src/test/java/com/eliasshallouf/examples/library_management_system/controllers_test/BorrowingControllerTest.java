package com.eliasshallouf.examples.library_management_system.controllers_test;

import com.eliasshallouf.examples.library_management_system.App;
import com.eliasshallouf.examples.library_management_system.service.db.BorrowingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = App.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
class BorrowingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BorrowingService borrowingService;

    @Test
    @WithMockUser(roles = "ADMIN")
    void testBorrowBook() throws Exception {
        Long bookId = 2L;
        Long patronId = 1L;

        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/api/borrow/{bookId}/patron/{patronId}", bookId, patronId)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(
            borrowingService.isBorrowed(bookId, new Date())
                ? status().isMethodNotAllowed()
                : status().isOk()
        );
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testReturnBook() throws Exception {
        Long bookId = 2L;
        Long patronId = 1L;

        mockMvc.perform(
            MockMvcRequestBuilders
                .put("/api/return/{bookId}/patron/{patronId}", bookId, patronId)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(
            borrowingService.findBorrowRecord(bookId, patronId) == null
                ? status().isMethodNotAllowed()
                : status().isOk()
        );
    }
}
