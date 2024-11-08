package com.eliasshallouf.examples.library_management_system.service.db;

import com.eliasshallouf.examples.library_management_system.domain.model.Book;
import com.eliasshallouf.examples.library_management_system.domain.model.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BookService extends AbstractEntityService<Long, Book, BookRepository> {
    @Override
    public Class<Book> getEntityClass() {
        return Book.class;
    }
}