package com.eliasshallouf.examples.library_management_system.controllers_test;

import com.eliasshallouf.examples.library_management_system.App;
import com.eliasshallouf.examples.library_management_system.domain.model.Book;
import com.eliasshallouf.examples.library_management_system.service.db.BookService;
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
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookService bookService;

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> expectedBooks = bookService.getAll();

        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(expectedBooks.size()));
    }

    @Test
    public void testFindBookById() throws Exception {
        Long bookId = 2L;
        Book book = bookService.findById(bookId);

        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/api/books/{bookId}", bookId)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(book.getAuthor()));;
    }

    @Test
    public void testSaveBook() throws Exception {
        Book newBook = Book.builder()
                .title("Test 1")
                .author("Elias")
                .publicationYear(2023)
                .isbn("1234567890")
                .build();

        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newBook))
        )
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateBook() throws Exception {
        Long bookId = 2L;
        Book updatedBook = Book.builder()
                .title("Test 2")
                .author("Elias")
                .publicationYear(2023)
                .isbn("1234567890")
                .build();

        mockMvc.perform(
            MockMvcRequestBuilders
                .put("/api/books/{bookId}", bookId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updatedBook))
        )
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteBook() throws Exception {
        Long bookId = 3L;

        mockMvc.perform(
            MockMvcRequestBuilders
                .delete("/api/books/{bookId}", bookId)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
