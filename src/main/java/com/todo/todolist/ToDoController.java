package com.todo.todolist;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {
    static HashMap<Integer,ToDo> todolist = new HashMap<>();
    static {
        todolist.put(1,new ToDo(1,"123","complete"));
        todolist.put(2,new ToDo(2,"123","complete"));
        todolist.put(3,new ToDo(3,"123","complete"));
        todolist.put(4,new ToDo(4,"123","complete"));
    }
    @GetMapping
    public ArrayList<ToDo> showToDoList(){
        return new ArrayList<>(todolist.values());
    }

    @PostMapping
    public void addToDoList(String list){
        int id = Collections.max(todolist.keySet()) + 1;
        todolist.put(id,new ToDo(id,list,""));
    }

    @PutMapping
    public void changeStat(int id){
        ToDo todo = todolist.get(id);
        if(todo.getStat().isEmpty()){
            todo.setStat("complete");
        } else{
            todo.setStat("");
        }
    }

    @DeleteMapping
    public void deleteToDoList(int id){
        todolist.remove(id);
    }
}
