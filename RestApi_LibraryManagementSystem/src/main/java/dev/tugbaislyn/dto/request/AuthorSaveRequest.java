package dev.tugbaislyn.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorSaveRequest {
    private  String name;
    private LocalDate birthDate;
    private  String Country;
}
