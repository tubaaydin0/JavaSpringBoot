package dev.tugbaislyn.dao;

import dev.tugbaislyn.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    //Ada göre aramayapıldığında  aynısı varsa tekrar eklenemez. yani ad unic olmalı
    Optional<Category> findByName(String name);
}
