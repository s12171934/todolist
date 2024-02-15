package com.todo.todolist.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Todo {
    Long id;
    Long memberId;
    String content;
    boolean done;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
}
