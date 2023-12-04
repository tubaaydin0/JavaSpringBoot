package dev.tugbaislyn.dao;

import dev.tugbaislyn.entities.Book;
import dev.tugbaislyn.entities.BookBorrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookBorrowingRepo extends JpaRepository<BookBorrowing, Long> {

}
