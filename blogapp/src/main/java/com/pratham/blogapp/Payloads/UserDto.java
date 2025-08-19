package com.pratham.blogapp.Payloads;

import com.pratham.blogapp.Entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {

    private int id;

    @NotBlank(message = "Name is required")
    @Size(min = 4, max = 15, message = "Name must be 4 to 15 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,15}$",
            message = "Password must be 8–15 chars, include 1 uppercase, 1 lowercase, 1 digit, and 1 special character"
    )
    private String password;

    private String about;

    private List<RolesDto> roles = new ArrayList<>();
}
