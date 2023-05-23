package com.practicing.springpractice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegistrationDto {
    private Long id;
    @NotEmpty(message = "You need to fill this box")
    private String username;
    @NotEmpty(message = "You need to fill this box")
    private String email;
    @NotEmpty(message = "You need to fill this box")
    private String password;
}
