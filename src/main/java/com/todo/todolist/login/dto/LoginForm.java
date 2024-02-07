package com.todo.todolist.login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginForm {
    @NotEmpty
    @Email
    String email;
    @NotEmpty
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z]).{8,}")
    String password;
    boolean saveEmail = false;
}
