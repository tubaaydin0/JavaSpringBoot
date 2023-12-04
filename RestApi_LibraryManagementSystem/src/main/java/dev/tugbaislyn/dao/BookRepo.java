package dev.tugbaislyn.dao;

import dev.tugbaislyn.entities.Author;
import dev.tugbaislyn.entities.Book;
import jakarta.validation.constraints.Min;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> {

    //b.categoryList  c : Book entitysindeki  private List<Category> categoryList; değişkenine denk geliyor.
    // Yani categoryListesini tutan listeye c ismini verdik.
    //c.id : listedeki kategori idsini belirtiyor.


    @Query("SELECT b FROM Book b JOIN b.categoryList c WHERE c.id= :categoryId")
    List<Book> findByCategoryId (Long categoryId);

    //Kitap adı ve yazarın olup olmadığını kontrol eden metot. Save ve update etme işlemlerinde uygulanacak.Bu değerler unic olmalı.
    Optional<Book> findByNameAndAuthor(String name, Author author);
}
