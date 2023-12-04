package dev.tugbaislyn.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "author")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", columnDefinition = "serial")
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    @NotNull
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    @Past // Geçmiş tarih yazılsın
    private LocalDate birthDate;

    @Column(name = "country")
    @NotNull
    private String country;


    // Bir yazar silindiğinde ona ait kitaplar da silinsin.
    //Gerektiğinde join işlemi yapsın
    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Book> bookList;
}