package com.example.todolist.service;

import com.example.todolist.domain.Users;

public interface UserService {
    Boolean emailChk(String email);

    void join(Users users);

    Users login(Users users);

    Users getUserData(Long userId);

}
