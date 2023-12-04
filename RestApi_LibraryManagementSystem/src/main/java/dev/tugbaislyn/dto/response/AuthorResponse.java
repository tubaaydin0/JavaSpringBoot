package dev.tugbaislyn.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse { // veritabanından booklist dışındaki veriler çekilsin.
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    private String country;

}
