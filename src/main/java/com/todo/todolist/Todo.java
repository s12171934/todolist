package com.todo.todolist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Todo {
    int id;
    String content;
    boolean done = false;

    public Todo(){}
    public Todo(String content) {
        this.content = content;
    }

    public Todo(int id, String content) {
        this.id = id;
        this.content = content;
    }
}
