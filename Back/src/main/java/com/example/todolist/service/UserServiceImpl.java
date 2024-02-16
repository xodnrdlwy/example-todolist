package com.example.todolist.service;

import com.example.todolist.domain.Role;
import com.example.todolist.domain.Users;
import com.example.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean emailChk(String email) {
        boolean result = false;
        int num = userRepository.emailCheck(email);
        log.info("username Chk : {}", num);
        if (num == 0) {
            result = true;
        }
        return result;
    }

    @Override
    public void join(Users users) {
        String encodedPassword = passwordEncoder.encode(users.getPassword());
        users.setPassword(encodedPassword);
        users.setUser_role(Role.USER);
        userRepository.join(users);
    }

    @Override
    public Users login(Users users) {
        Users LoginUser = userRepository.getEmail(users.getEmail());
        log.info("LoginUser Data : {} ", LoginUser);
        if (LoginUser != null) {
            if(passwordEncoder.matches(users.getPassword(), LoginUser.getPassword())){
                return LoginUser;
            }
        }
        return null;
    }

    @Override
    public Users getUserData(Long userId) {
        return userRepository.getUserData(userId);
    }






}
