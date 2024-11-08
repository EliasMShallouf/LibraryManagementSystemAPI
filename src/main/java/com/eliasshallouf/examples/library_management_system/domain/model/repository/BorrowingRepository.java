package com.eliasshallouf.examples.library_management_system.domain.model.repository;

import com.eliasshallouf.examples.library_management_system.domain.model.BorrowingRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface BorrowingRepository extends CrudRepository<BorrowingRecord, BorrowingRecord.BorrowingRecordId> {
    @Query("SELECT COUNT(*) > 0 FROM BorrowingRecord br WHERE br.id.book.id = :bookId AND br.returnedDate IS NULL AND br.id.borrowedDate <= :date")
    Boolean isBorrowed(Long bookId, Date date);

    @Query("SELECT br FROM BorrowingRecord br WHERE br.id.book.id = :bookId and br.id.patron.id = :patronId and br.returnedDate IS NULL")
    BorrowingRecord findBorrowed(Long bookId, Long patronId);
}
