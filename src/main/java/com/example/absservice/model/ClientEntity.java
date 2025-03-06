package com.example.absservice.model;

import java.time.*;
import java.util.*;

import jakarta.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "clients")
@NamedEntityGraph(
    name = "client_entity-graph",
    attributeNodes = {
        @NamedAttributeNode("categories")
    }
)
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Size(max = 255)
    @NotNull
    @Column(name = "passport", nullable = false)
    private String passport;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "client_categories",
        joinColumns = @JoinColumn(name = "client_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<CategoryEntity> categories;

}
