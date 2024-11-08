package com.eliasshallouf.examples.library_management_system.service.db;

import com.eliasshallouf.examples.library_management_system.domain.model.Patron;
import com.eliasshallouf.examples.library_management_system.domain.model.repository.PatronRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PatronService extends AbstractEntityService<Long, Patron, PatronRepository> {
    @Override
    public Class<Patron> getEntityClass() {
        return Patron.class;
    }
}