package dev.tugbaislyn.business.abstracts;

import dev.tugbaislyn.entities.Author;

import java.util.List;

public interface IAuthorService {
    //CRUD işlemler
    Author getById(Long id);// Gelen idye göre author getiren
    Author save(Author author);// Gelen authoru kaydeden
    Author update(Author author); // Gelen author güncelleyen
    void delete(Long id); // idye göre silme işlemi yapan
    List<Author > findAll(); // Tüm authoru listeleyen
}
