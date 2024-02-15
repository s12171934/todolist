package com.todo.todolist.mappers;

import com.todo.todolist.dto.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    Member getMemberByEmail(String email);
    void save(Member member);

}
