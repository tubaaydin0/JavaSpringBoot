import jakarta.persistence.*;

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
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id", columnDefinition = "serial")
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "publication_year",nullable = false)
    private int publicationYear;

    @Column(name = "stock",nullable = false)
    private int stock;

    @ManyToOne(fetch = FetchType.EAGER) // Join işlemi hep yapsın.
    @JoinColumn(name = "book_author id", referencedColumnName = "author_id")
    private  Author author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="book_publisher_id", referencedColumnName = "publisher_id")
    private Publisher publisher;


    @OneToMany(mappedBy = "book",fetch = FetchType.LAZY,cascade =CascadeType.REMOVE)
    private List<BookBorrowing> bookBorrowingList;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="book_category",
            joinColumns = {
                    @JoinColumn(name = "book_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "category_id")
            }

    )


    private List<Category> categoryList;
    public Book() {
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

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<BookBorrowing> getBookBorrowingList() {
        return bookBorrowingList;
    }

    public void setBookBorrowingList(List<BookBorrowing> bookBorrowingList) {
        this.bookBorrowingList = bookBorrowingList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publicationYear=" + publicationYear +
                ", stock=" + stock +
                ", author=" + author +
                ", publisher=" + publisher +
                ", bookBorrowingList=" + bookBorrowingList +
                ", categoryList=" + categoryList +
                '}';
    }
}
