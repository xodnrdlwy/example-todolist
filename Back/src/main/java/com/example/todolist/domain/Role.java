package com.example.todolist.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//사용자 권한 Enum
@RequiredArgsConstructor
@Getter
public enum Role {

    //사용자, 관리자
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN"),;

    private final String value;
}
