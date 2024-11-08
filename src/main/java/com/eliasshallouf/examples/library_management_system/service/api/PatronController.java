package com.eliasshallouf.examples.library_management_system.service.api;

import com.eliasshallouf.examples.library_management_system.AppCache;
import com.eliasshallouf.examples.library_management_system.domain.model.Book;
import com.eliasshallouf.examples.library_management_system.domain.model.Patron;
import com.eliasshallouf.examples.library_management_system.domain.model.exceptions.RecordNotFoundException;
import com.eliasshallouf.examples.library_management_system.service.db.BookService;
import com.eliasshallouf.examples.library_management_system.service.db.PatronService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    @Autowired
    private PatronService patronService;

    @GetMapping
    public List<Patron> getAllPatrons() {
        return patronService.getAll();
    }

    @GetMapping("/{patronId}")
    @Cacheable(AppCache.PATRON_CACHE)
    public Patron findPatron(@Valid @NotNull @Min(value = 1, message = "Id must be bigger than 0") @PathVariable Long patronId) throws RecordNotFoundException {
        return patronService.findById(patronId);
    }

    @PostMapping
    public void save(@Valid @RequestBody Patron patron) {
        patronService.insert(patron);
    }

    @PutMapping("/{patronId}")
    public void update(@Valid @NotNull @Min(value = 1, message = "Id must be bigger than 0") @PathVariable Long patronId, @Valid @RequestBody Patron patron) throws RecordNotFoundException {
        patronService.update(patronId, patron);
    }

    @DeleteMapping("/{patronId}")
    public void delete(@Valid @NotNull @Min(value = 1, message = "Id must be bigger than 0") @PathVariable Long patronId) throws RecordNotFoundException {
        patronService.delete(patronId);
    }
}
