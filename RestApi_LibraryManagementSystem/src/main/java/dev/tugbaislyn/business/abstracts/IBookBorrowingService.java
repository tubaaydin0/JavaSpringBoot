package dev.tugbaislyn.business.abstracts;

import dev.tugbaislyn.dto.request.BookBorrowingSaveRequest;
import dev.tugbaislyn.entities.BookBorrowing;


import java.util.List;

public interface IBookBorrowingService {
    //CRUD
    //BookBorrowing save(BookBorrowingSaveRequest borrowingSaveRequest); //mapleme işleri serviste olsa daha iyi olur ama bu projede cnotrollerda yaptık
    BookBorrowing save(BookBorrowing bookBorrowing);


    BookBorrowing getById(Long id);

    List<BookBorrowing> findAll();

    BookBorrowing update(BookBorrowing bookBorrowing);

    void delete(Long id);
}
