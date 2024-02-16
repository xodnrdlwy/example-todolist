package com.example.todolist.repository;

import com.example.todolist.domain.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int emailCheck(String email);

    void join(Users users);

    Users getEmail(String email);

    Users getUserData(Long userId);


}
