package com.eliasshallouf.examples.library_management_system.domain.model;

import com.eliasshallouf.examples.library_management_system.domain.model.helpers.IndexedEntity;
import com.eliasshallouf.examples.library_management_system.service.utils.validators.MaxYear;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class Book implements IndexedEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Book title mustn't be blank")
    @Size(min = 5, message = "Book title must be 5 chars length long")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Book author name mustn't be blank")
    private String author;

    @MaxYear
    private Integer publicationYear;

    @Column(unique = true)
    @Pattern(
        regexp = "^(ISBN(?:-10|-13))?\\s*[:-]?\\s*(\\d{1,13})\\s*([-]?\\d{1,10})?$",
        message = "ISBN must be ISBN-10 or ISBN-13"
    )
    private String isbn;

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
