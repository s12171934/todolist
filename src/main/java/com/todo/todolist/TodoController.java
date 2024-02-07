package com.todo.todolist;

import com.todo.todolist.dto.RequestTodo;
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

    @PostMapping
    public RequestTodo addToDoList(@RequestBody RequestTodo todo){
        todoMapper.save(todo);
        return todo;
    }

    @PutMapping("/{id}")
    public boolean changeStat(@PathVariable Long id){
        todoMapper.update(id);
        return todoMapper.findById(id).isDone();
    }

    @DeleteMapping("/{id}")
    public String deleteToDoList(@PathVariable Long id){
        todoMapper.delete(id);
        return "success";
    }
}
