package dev.tugbaislyn.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateRequest {
    private  Long id;
    private  String name;
    private LocalDate birthDate;
    private  String Country;
}
