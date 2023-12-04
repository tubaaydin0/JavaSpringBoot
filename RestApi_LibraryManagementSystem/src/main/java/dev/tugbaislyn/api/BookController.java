package dev.tugbaislyn.api;

import dev.tugbaislyn.business.abstracts.IBookService;
import dev.tugbaislyn.entities.Book;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/lms")
public class BookController {
    private final IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    //CREATE
    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    public Book save(@RequestBody Book book){
        return this.bookService.save(book);
    }

    //READ
    @GetMapping("/book/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book findById(@PathVariable("id") Long id){
        return this.bookService.getById(id);
    }

    @GetMapping("/book")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> findAll(){
        return this.bookService.findAll();
    }

    //UPDATE
    @PutMapping("/book")
    @ResponseStatus(HttpStatus.OK)
    public Book update(@RequestBody Book book){
        return this.bookService.update(book);
    }

    //DELETE
    @DeleteMapping("/book/{id}")
    public void delete(@PathVariable("id") Long id){
        this.bookService.delete(id);
    }
}
