package com.todo.todolist.controller;

import com.todo.todolist.dto.Todo;
import com.todo.todolist.dto.Member;
import com.todo.todolist.mappers.TodoMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    @Autowired
    TodoMapper todoMapper;
    @GetMapping
    public ArrayList<Todo> showToDoList(HttpServletRequest request){
        Long memberId = ((Member)request.getSession().getAttribute("member")).getId();
        return (ArrayList<Todo>) todoMapper.findAll(memberId);
    }
    @GetMapping("/user")
    public Member responseMember(HttpServletRequest request){
        return (Member)request.getSession().getAttribute("member");
    }

    @PostMapping
    public Todo addToDoList(@RequestBody Todo todo, HttpServletRequest request){
        todo.setMemberId(((Member)request.getSession().getAttribute("member")).getId());
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
