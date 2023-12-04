package dev.tugbaislyn.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookWhichBorrowedRequest {//BookBorrowing save işlemi yaparken hangi kitabın ödünç alınacaksa onun bilgilerinin girilmesi gerekiyor.
    //Hangi bilgiler girilsin bunu belirliyoruz.Book entitydeki bilgilere göre işe yaraynları yazabiliriz.
    private  Long id;
    private String name;
    private int publicationYear;
    private int stock;

}
