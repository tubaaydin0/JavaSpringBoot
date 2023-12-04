package dev.tugbaislyn.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookBorrowingSaveRequest {
    @NotNull
    private  String name;
    @NotNull
    private LocalDate borrowingDate;
    @NotNull
    private BookWhichBorrowedRequest book; // BookBorrowing Entitysinde book şeklinde tanımladığımız için book adını verdik.
    // Farklı isim verirsek maplerkan hata yaşayabiliriz.Bu ÖNEMLİ!!!


}
