package com.eliasshallouf.examples.library_management_system.domain.model.repository;

import com.eliasshallouf.examples.library_management_system.domain.model.Patron;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends CrudRepository<Patron, Long> {
}
