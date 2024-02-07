package com.todo.todolist.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Member {
    Long id;
    @NotEmpty
    @Email
    String email;
    @NotEmpty
    String name;
    @NotEmpty
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z]).{8,}")
    String password;
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z]).{8,}")
    String passwordCheck;
}
