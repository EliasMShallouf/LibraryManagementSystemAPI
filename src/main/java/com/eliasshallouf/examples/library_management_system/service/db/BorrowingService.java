package com.eliasshallouf.examples.library_management_system.service.db;

import com.eliasshallouf.examples.library_management_system.domain.model.Book;
import com.eliasshallouf.examples.library_management_system.domain.model.BorrowingRecord;
import com.eliasshallouf.examples.library_management_system.domain.model.Patron;
import com.eliasshallouf.examples.library_management_system.domain.model.exceptions.RecordNotFoundException;
import com.eliasshallouf.examples.library_management_system.domain.model.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

@Service
public class BorrowingService {
    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private PatronService patronService;

    @Transactional
    public void borrow(Long bookId, Long patronId, Date borrowedDate) throws RecordNotFoundException, RuntimeException {
        BorrowingRecord borrowingRecord = new BorrowingRecord();

        Book book = bookService.findById(bookId);
        Patron patron = patronService.findById(patronId);
        if(borrowingRepository.isBorrowed(bookId, borrowedDate))
            throw new RuntimeException("Book already borrowed");

        BorrowingRecord.BorrowingRecordId id = new BorrowingRecord.BorrowingRecordId();
        id.setBook(book);
        id.setPatron(patron);
        id.setBorrowedDate(borrowedDate);

        borrowingRecord.setId(id);
        borrowingRecord.setReturnedDate(null);

        borrowingRepository.save(borrowingRecord);
    }

    @Transactional
    public void returnBorrowed(Long bookId, Long patronId, Date returnedDate) throws RuntimeException {
        BorrowingRecord borrowingRecord = borrowingRepository.findBorrowed(bookId, patronId);

        if(borrowingRecord == null)
            throw new RuntimeException("Book hadn't borrowed previously by this patron");

        borrowingRecord.setReturnedDate(returnedDate);
        borrowingRepository.save(borrowingRecord);
    }

    @Transactional
    public boolean isBorrowed(Long bookId, Date date) {
        return borrowingRepository.isBorrowed(bookId, date);
    }

    @Transactional
    public BorrowingRecord findBorrowRecord(Long bookId, Long patronId) {
        return borrowingRepository.findBorrowed(bookId, patronId);
    }
}
