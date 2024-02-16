package com.example.todolist.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Users {
        private Long userId;
        private String email;
        private String nickname;
        private String password;
        private boolean rememberMe;
        private LocalDateTime regdate;

        private Role user_role;
}
