package com.eliasshallouf.examples.library_management_system.service.api;

import com.eliasshallouf.examples.library_management_system.domain.model.exceptions.RecordNotFoundException;
import com.eliasshallouf.examples.library_management_system.service.db.BorrowingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class BorrowingController {
    @Autowired
    private BorrowingService borrowingService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void borrow(
        @PathVariable @Valid @NotNull @Min(value = 1, message = "Id must be bigger than 0") Long bookId,
        @PathVariable @Valid @NotNull @Min(value = 1, message = "Id must be bigger than 0") Long patronId
    ) throws RecordNotFoundException, RuntimeException {
        borrowingService.borrow(bookId, patronId, new Date());
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void returnBorrow(
        @PathVariable @Valid @NotNull @Min(value = 1, message = "Id must be bigger than 0") Long bookId,
        @PathVariable @Valid @NotNull @Min(value = 1, message = "Id must be bigger than 0") Long patronId
    ) throws RuntimeException {
        borrowingService.returnBorrowed(bookId, patronId, new Date());
    }
}
