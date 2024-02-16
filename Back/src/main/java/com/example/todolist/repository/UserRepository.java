package com.example.todolist.repository;

import com.example.todolist.domain.Users;

public interface UserRepository {
    int emailCheck(String email);

    void join(Users users);

    Users getEmail(String email);

    Users getUserData(Long userId);

}
