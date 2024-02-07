package com.todo.todolist.mappers;

import com.todo.todolist.dto.RequestTodo;
import com.todo.todolist.dto.ResponseTodo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
public interface TodoMapper {
    List<ResponseTodo> findAll();
    ResponseTodo findById(Long id);
    ResponseTodo findRecent();
    void save(RequestTodo todo);
    void update(Long id);
    void delete(Long id);
}
