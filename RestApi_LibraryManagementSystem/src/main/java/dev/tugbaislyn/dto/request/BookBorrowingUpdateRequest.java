package dev.tugbaislyn.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingUpdateRequest {
    @NotNull
    private Long id;

    private String name;

    private LocalDate borrowingDate;

    private LocalDate returnDate;

}
