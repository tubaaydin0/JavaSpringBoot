package dev.tugbaislyn.business.abstracts;

import dev.tugbaislyn.entities.Category;

import java.util.List;

public interface ICategoryService {
    //CRUD İşlemler
    Category save(Category category);
    Category getById(Long id);
    List<Category> findAll();

    Category update(Category category);
    String delete(Long id);
}
