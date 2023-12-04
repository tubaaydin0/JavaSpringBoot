package dev.tugbaislyn.business.abstracts;

import dev.tugbaislyn.entities.Book;


import java.util.List;

public interface IBookService {
    //CRUD
    Book save(Book book); // Kaydetme
    Book getById(Long id); //idye göre publisher
    Book update(Book book); //Güncelleme
    void delete(Long id); // idye göre silme
    List<Book> findAll(); // Tümünü listeleme
    public List<Book> findByCategoryId(Long id);
}
