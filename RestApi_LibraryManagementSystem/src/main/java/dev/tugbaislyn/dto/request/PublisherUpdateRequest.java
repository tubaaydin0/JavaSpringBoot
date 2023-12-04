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
public class PublisherUpdateRequest {
    //Güncelleme işlemi yapılırken entitydeki tüm bilgiler kullanılacak
    @NotNull
    private Long id;

    private String name;

    private Integer establishmentYear;

    private String address;
}
