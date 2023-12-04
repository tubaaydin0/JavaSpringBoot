package dev.tugbaislyn.dao;

import dev.tugbaislyn.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepo extends JpaRepository<Publisher,Long> {

   // isim ve kuruluş yılına göre getiren optinonal nesnesi oluşturuldu. Bu verilere göre aynıları varsa tekrar kaydedilmesi engellenecek.
    Optional<Publisher> findByNameAndEstablishmentYear(String name, Integer establishmentYear);
}
