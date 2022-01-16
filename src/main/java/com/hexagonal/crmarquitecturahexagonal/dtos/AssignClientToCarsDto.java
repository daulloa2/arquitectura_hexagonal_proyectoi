package com.hexagonal.crmarquitecturahexagonal.dtos;



import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class AssignClientToCarsDto {
    
    @NotBlank
    private String uuidClient;

    @NotBlank
    private String uuidUserManager;

    @NonNull
    private Long[] idsCars;
}
