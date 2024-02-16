package com.example.todolist.repository;

import com.example.todolist.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{
    private final UserMapper userMapper;

    @Override
    public int emailCheck(String email) {
        return userMapper.emailCheck(email);
    }

    @Override
    public void join(Users users) {
        userMapper.join(users);
    }

    @Override
    public Users getEmail(String email) {
        return userMapper.getEmail(email);
    }

    @Override
    public Users getUserData(Long userId) {
        return userMapper.getUserData(userId);
    }


}
