package com.eliasshallouf.examples.library_management_system.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class BorrowingRecord {
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    @Embeddable
    public static class BorrowingRecordId implements Serializable {
        @ManyToOne
        private Book book;

        @ManyToOne
        private Patron patron;

        @Column(nullable = false)
        private Date borrowedDate;
    }

    @EmbeddedId
    private BorrowingRecordId id;

    @Column
    private Date returnedDate;
}
