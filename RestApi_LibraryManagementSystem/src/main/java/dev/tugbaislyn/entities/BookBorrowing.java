package dev.tugbaislyn.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "book_borrowing")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
public class BookBorrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="borrowing_id", columnDefinition = "serial")
    private Long id;

    @NotNull
    @Column(name="borrower_name",nullable = false)
    private String name;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "borrowing_date",nullable = false)
    private LocalDate borrowingDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "return_date")
    private LocalDate returnDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "b_book_id", referencedColumnName="book_id")
    private Book book;

}