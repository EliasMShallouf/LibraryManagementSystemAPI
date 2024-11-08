package com.eliasshallouf.examples.library_management_system.domain.model.repository;

import com.eliasshallouf.examples.library_management_system.domain.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
