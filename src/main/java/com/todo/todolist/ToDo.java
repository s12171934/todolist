package com.todo.todolist;

public class ToDo {
    int id;
    String todo;
    String stat;

    public ToDo(int id, String todo, String stat) {
        this.id = id;
        this.todo = todo;
        this.stat = stat;
    }

    public int getId() {
        return id;
    }

    public String getTodo() {
        return todo;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
