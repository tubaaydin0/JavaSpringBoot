package dev.tugbaislyn.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id", columnDefinition = "serial")
    private Long id;

    @Column(name="name",nullable = false)
    @NotNull
    private String name;

    @Column(name="description")
    private String description;


    @ManyToMany(mappedBy = "categoryList", cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Book> bookList;




}