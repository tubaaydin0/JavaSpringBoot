package dev.tugbaislyn.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
Bu varlıklar arasında şu ilişkileri tanımlamalısın :
Bir kitabın bir yazarı olabilir, bir yazarın birden fazla kitabı olabilir. (One-to-Many ilişkisi).
Bir kategori birden fazla kitaba sahip olabilirken, bir kitap birden fazla kategoriye ait olabilir. (Many-to-Many ilişkisi).
Bir kitabın bir yayınevi olabilir, bir yayınevinin birden fazla kitabı olabilir. (One-to-Many ilişkisi).
Bir kitap birden fazla ödünç alma işlemine sahip olabilir, ancak her ödünç alma işlemi yalnızca bir kitaba ait olabilir. (One-to-Many ilişkisi).
 */
@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", columnDefinition = "serial")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "publication_year", nullable = false)
    private int publicationYear;

    @NotNull
    @Column(name = "stock", nullable = false)
    private int stock;

    @ManyToOne(fetch = FetchType.EAGER) // Join işlemi hep yapsın.
    @JoinColumn(name = "book_author id", referencedColumnName = "author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_publisher_id", referencedColumnName = "publisher_id")
    private Publisher publisher;


    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<BookBorrowing> bookBorrowingList;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_category",
            joinColumns = {
                    @JoinColumn(name = "book_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "category_id")
            }

    )

    private List<Category> categoryList;
}