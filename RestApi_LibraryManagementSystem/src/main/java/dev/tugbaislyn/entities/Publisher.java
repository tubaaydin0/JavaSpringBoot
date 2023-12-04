package dev.tugbaislyn.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "publisher")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publisher { // Yayınevi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="publisher_id", columnDefinition = "serial")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name = "establishment_year",nullable = false)
    @NotNull
    private Integer establishmentYear; //Kuruluş Yılı

    @Column(name = "address",nullable = false)
    @NotNull
    private String address;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Book> bookList;




}