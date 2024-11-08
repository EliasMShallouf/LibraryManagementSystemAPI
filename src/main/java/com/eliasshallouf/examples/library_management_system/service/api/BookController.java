package com.eliasshallouf.examples.library_management_system.service.api;

import com.eliasshallouf.examples.library_management_system.AppCache;
import com.eliasshallouf.examples.library_management_system.domain.model.Book;
import com.eliasshallouf.examples.library_management_system.domain.model.exceptions.RecordNotFoundException;
import com.eliasshallouf.examples.library_management_system.service.db.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/{bookId}")
    @Cacheable(AppCache.BOOK_CACHE)
    public Book findBook(@Valid @NotNull @Min(value = 1, message = "Id must be bigger than 0") @PathVariable Long bookId) throws RecordNotFoundException {
        return bookService.findById(bookId);
    }

    @PostMapping
    public void save(@Valid @RequestBody Book book) {
        bookService.insert(book);
    }

    @PutMapping("/{bookId}")
    public void update(@Valid @NotNull @Min(value = 1, message = "Id must be bigger than 0") @PathVariable Long bookId, @Valid @RequestBody Book book) throws RecordNotFoundException {
        bookService.update(bookId, book);
    }

    @DeleteMapping("/{bookId}")
    public void delete(@Valid @NotNull @Min(value = 1, message = "Id must be bigger than 0") @PathVariable Long bookId) throws RecordNotFoundException {
        bookService.delete(bookId);
    }
}
