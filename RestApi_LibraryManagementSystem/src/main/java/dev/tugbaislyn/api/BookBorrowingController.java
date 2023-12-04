package dev.tugbaislyn.api;

import dev.tugbaislyn.business.abstracts.IBookBorrowingService;
import dev.tugbaislyn.business.abstracts.IBookService;
import dev.tugbaislyn.dto.request.BookBorrowingSaveRequest;
import dev.tugbaislyn.dto.request.BookBorrowingUpdateRequest;
import dev.tugbaislyn.entities.Book;
import dev.tugbaislyn.entities.BookBorrowing;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/lms")
@RequiredArgsConstructor
public class BookBorrowingController {
    private final IBookBorrowingService bookBorrowingService;
    private final ModelMapper modelMapper;
    private final IBookService bookService;

    /*
    BookBorrowing tablosuna yeni kayıt yapılırken (bir kitabın ödünç verilme senaryosu) ödünç verilen kitabın
    stok kontrolünü yapmalısın
     */
    //CREATE
    @PostMapping("/bookborrowing")
    @ResponseStatus(HttpStatus.CREATED)
    public BookBorrowing save(@RequestBody BookBorrowingSaveRequest bookBorrowingSaveRequest){

       // return this.bookBorrowingService.save(bookBorrowingSaveRequest);
        return this.bookBorrowingService.save(this.modelMapper.map(bookBorrowingSaveRequest,BookBorrowing.class));

    }

    //READ
    @GetMapping("/bookborrowing/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookBorrowing findById(@PathVariable("id") Long id){
        return this.bookBorrowingService.getById(id);
    }

    @GetMapping("/bookborrowing")
    @ResponseStatus(HttpStatus.OK)
    public List<BookBorrowing> findAll(){
        return this.bookBorrowingService.findAll();
    }

    //UPDATE
    @PutMapping("/bookborrowing")
    @ResponseStatus(HttpStatus.OK)
    public BookBorrowing update( @RequestBody BookBorrowingUpdateRequest bookBorrowingUpdateRequest){
        return this.bookBorrowingService.update(this.modelMapper.map(bookBorrowingUpdateRequest,BookBorrowing.class));
    }

    //DELETE
    @DeleteMapping("/bookborrowing/{id}")
    public void delete(@PathVariable("id") Long id){
        this.bookBorrowingService.delete(id);
    }
}
