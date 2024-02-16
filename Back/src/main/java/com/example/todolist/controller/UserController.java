package com.example.todolist.controller;

import com.example.todolist.domain.Users;
import com.example.todolist.service.RegisterMail;
import com.example.todolist.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin( origins =  {"http://localhost:3000"})
public class UserController {
    private final UserService userService;
    private final RegisterMail registerMail;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/emailChk")
    public ResponseEntity<?> emailChk (@RequestBody String email) {
        email = email.replaceAll("\"", "");
        log.info("userData : {}", email);
        Boolean result = userService.emailChk(email);
        log.info("Result : {}", result);
        return ResponseEntity.ok(result);
    }



    @PostMapping("/mailConfirm")
    public String mailConfirm(@RequestBody String email) throws Exception {
        email = email.replaceAll("\"", "");
        System.out.println("Email 누구한테 보낼꺼 ? : " + email);
            try {
                // Rest of your logic
                String code = registerMail.sendSimpleMessage(email);
                System.out.println("code : " + code);
                return code;
            } catch (Exception e) {
                e.printStackTrace();
                return "오류 !!! 이메일을 정확히 입력하세요!";
            }
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody Users users) {
        log.info("users : {}", users);
        userService.join(users);
        return ResponseEntity.ok("Sucess!!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users users, HttpServletResponse response, HttpSession session) {
        log.info("remember Me : {} ", users);
        Users dbuser = userService.login(users);

        if (dbuser != null) {   // 회원일때
            // 쿠키 등록 rememberMe를 선택했을 시
            if (users.isRememberMe()) {
                Cookie cookie = new Cookie("E-mail", String.valueOf(dbuser.getEmail()));
                cookie.setMaxAge(60 * 60 * 24 * 7);   // 쿠키 수명 설정 초단위
                cookie.setPath("/");    // 모든 경로에 적용
                response.addCookie(cookie);
            }
            // 세션 등록
            log.info("DB User Data : {}", dbuser.getUserId());
            session.setAttribute("id", dbuser.getUserId());
            Long user_id = (Long) session.getAttribute("id");
            log.info("users_id Data : {}", user_id);
            return ResponseEntity.ok("Success");
        } else { // 회원이 아닐때
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/showUser")
    public ResponseEntity<Users> getUser(HttpSession session) {
        Long user_id = (Long) session.getAttribute("id");
        log.info("LoginUser Data : {} ", user_id);
        Users userData = userService.getUserData(user_id);
        if (userData != null) {
            return ResponseEntity.ok(userData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("logout Success!!");
    }

    @PostMapping("/PwChk")
    public ResponseEntity<Users> PwChk(HttpSession session, @RequestBody Users users) {
        Long user_id = (Long) session.getAttribute("id");
        log.info("pwChk Data : {} ", user_id);
        Users dbUser = userService.getUserData(user_id);
        log.info("dbUser Password : {}", dbUser.getPassword());
        log.info("dbUser Password : {}", dbUser);
        if(passwordEncoder.matches(users.getPassword(), dbUser.getPassword())) {
            return ResponseEntity.ok(dbUser);
        }else {
            return ResponseEntity.notFound().build();
        }
    }




}
