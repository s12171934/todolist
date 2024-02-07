package com.todo.todolist.mappers;

import com.todo.todolist.dto.Todo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TodoMapper {
    List<Todo> findAll(Long memberId);
    Todo findById(Long id);
    Todo findRecent();
    void save(Todo todo);
    void update(Long id);
    void delete(Long id);
}
