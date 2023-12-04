package dev.tugbaislyn.api;

import dev.tugbaislyn.business.abstracts.ICategoryService;
import dev.tugbaislyn.entities.Category;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/lms")
public class CategoryController {
    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }
    //CREATE İŞLEMİ
    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public Category save(@RequestBody Category category){// Json formatındaki veriler Category formatına çevrildi.
        return this.categoryService.save(category);

    }

    //READ İŞLEMLERİ
    @GetMapping("/category/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category FindById(@PathVariable ("id") Long id){
        return this.categoryService.getById(id);
    }

    @GetMapping("/category")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> findAll(){
        return this.categoryService.findAll();
    }

    //UPDATE İŞLEMİ
    @PutMapping("/category")
    @ResponseStatus(HttpStatus.OK)
    public Category update(@RequestBody Category category){
        return this.categoryService.update(category);

    }

    //DELETE İŞLEMİ

    @DeleteMapping("/category/{id}")
    public String delete(@PathVariable ("id") Long id){
        return this.categoryService.delete(id);
    }
}
