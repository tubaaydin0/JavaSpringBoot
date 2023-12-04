package dev.tugbaislyn.dto.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublisherResponse {
    //Veritabanından publisher verileri çekilirken hangileri gösterilsin onları belirliyoruz. Address in görülmesi istenmiyor.
    private  Long id;

    @NotNull
    private String name;

    @Past
    @NotNull
    private Integer establishmentYear;
}
