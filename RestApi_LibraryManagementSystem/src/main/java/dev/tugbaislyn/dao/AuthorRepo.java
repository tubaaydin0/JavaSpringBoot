package dev.tugbaislyn.dao;

import dev.tugbaislyn.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AuthorRepo extends JpaRepository<Author,Long> {

    //Yazar entitiysinde aynı bilgilere sahip yazarın eklenmemesi için. Verilen bilgilere sahip yazar var mı yok mu
    // onu kontrol eden bir Optional nesnesi oluşturuyoruz.
    Optional<Author> findByNameAndBirthDateAndCountry(String name, LocalDate birthDate, String Country);
}
