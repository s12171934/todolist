package com.todo.todolist;

import com.todo.todolist.dto.ResponseTodo;
import com.todo.todolist.mappers.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    @Autowired
    TodoMapper todoMapper;
    @GetMapping
    public ArrayList<ResponseTodo> showToDoList(){
        return (ArrayList<ResponseTodo>) todoMapper.findAll();
    }
/*
    @PostMapping
    public Todo addToDoList(@RequestBody Todo todo){
        int id = 0;
        try {
            id = Collections.max(todolist.keySet()) + 1;
        } catch (Exception e){}
        todo.setId(id);
        todolist.put(id,todo);
        return todo;
    }

    @PutMapping("/{id}")
    public String changeStat(@PathVariable int id){
        Todo todo = todolist.get(id);
        if(todo.isDone()){
            todo.setDone(true);
        } else{
            todo.setDone(false);
        }
        return todo.isDone();
    }

    @DeleteMapping("/{id}")
    public String deleteToDoList(@PathVariable int id){
        todolist.remove(id);
        return "success";
    }
 */
}
