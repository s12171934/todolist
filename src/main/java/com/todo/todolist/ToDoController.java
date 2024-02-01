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
        todolist.put(1,new ToDo(1,"1번할일"));
        todolist.put(2,new ToDo(2,"2번할일"));
        todolist.put(3,new ToDo(3,"3번할일"));
        todolist.put(4,new ToDo(4,"4번할일"));
    }
    @GetMapping
    public ArrayList<ToDo> showToDoList(){
        return new ArrayList<>(todolist.values());
    }

    @PostMapping("/add")
    public void addToDoList(@RequestBody ToDo todo){
        int id = Collections.max(todolist.keySet()) + 1;
        todo.setId(id);
        todolist.put(id,todo);
    }

    @PutMapping("/{id}")
    public void changeStat(@PathVariable int id){
        ToDo todo = todolist.get(id);
        if(todo.getStat().isEmpty()){
            todo.setStat("complete");
        } else{
            todo.setStat("");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteToDoList(@PathVariable int id){
        todolist.remove(id);
    }
}
