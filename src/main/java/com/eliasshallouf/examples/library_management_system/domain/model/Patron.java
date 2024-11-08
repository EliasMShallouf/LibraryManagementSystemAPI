package com.eliasshallouf.examples.library_management_system.domain.model;

import com.eliasshallouf.examples.library_management_system.domain.model.helpers.IndexedEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class Patron implements IndexedEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 5, message = "Patron title must be 5 chars length long")
    private String name;

    @Column(nullable = false)
    @Email
    private String email;

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
