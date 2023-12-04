package dev.tugbaislyn.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublisherSaveRequest {
    //Kaydetme işleminde adı ve kuruluş yılı ve adres bilgileri girilecek.
    @NotNull
    private String name;
    @NotNull
    private Integer establishmentYear;
    @NotNull
    private String address;
}
