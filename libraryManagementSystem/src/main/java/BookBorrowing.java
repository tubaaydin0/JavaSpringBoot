import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "book_borrowing")
public class BookBorrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="borrowing_id", columnDefinition = "serial")
    private Long id;
    @Column(name="borrower_name",nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "borrowing_date",nullable = false)
    private LocalDate borrowingDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "return_date")
    private LocalDate returnDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "b_book_id", referencedColumnName="book_id")
    private Book book;
    public BookBorrowing() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(LocalDate borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BookBorrowing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", borrowingDate=" + borrowingDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
