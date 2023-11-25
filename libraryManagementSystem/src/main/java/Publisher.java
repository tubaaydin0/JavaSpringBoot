import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "publisher")
public class Publisher { // Yayınevi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="publisher_id", columnDefinition = "serial")
    private Long id;
    @Column(name="name")
    private String name;

    @Column(name = "establishment_year",nullable = false)
    private Integer establishmentYear; //Kuruluş Yılı

    @Column(name = "address",nullable = false)
    private String address;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Book> bookList;

    public Publisher() {
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

    public Integer getEstablishmentYear() {
        return establishmentYear;
    }

    public void setEstablishmentYear(Integer establishmentYear) {
        this.establishmentYear = establishmentYear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", establishmentYear=" + establishmentYear +
                ", address='" + address + '\'' +
                '}';
    }
}
