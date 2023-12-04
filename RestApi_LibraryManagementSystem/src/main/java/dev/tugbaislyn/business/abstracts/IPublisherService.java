package dev.tugbaislyn.business.abstracts;

import dev.tugbaislyn.entities.Publisher;

import java.util.List;

public interface IPublisherService {
    //CRUD

    Publisher save(Publisher publisher); // Kaydetme
    Publisher getById(Long id); //idye göre publisher
    Publisher update(Publisher publisher); //Güncelleme
    void delete(Long id); // idye göre silme
    List<Publisher> findAll(); // Tümünü listeleme

}
