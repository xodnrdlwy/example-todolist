package com.example.todolist.controller;


import com.example.todolist.service.RegisterMail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MailController {
    private final RegisterMail mailService;
}
