package com.todo.todolist;

public class ToDo {
    int id;
    String todo;
    String stat = "";

    public ToDo(){}
    public ToDo(String todo) {
        this.todo = todo;
    }

    public ToDo(int id, String todo) {
        this.id = id;
        this.todo = todo;
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

    public void setId(int id) {
        this.id = id;
    }
    public void setStat(String stat) {
        this.stat = stat;
    }
}
