package com.todo.todolist.controller;

import com.todo.todolist.dto.LoginForm;
import com.todo.todolist.dto.Member;
import com.todo.todolist.mappers.MemberMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginRestController {
    @Autowired
    MemberMapper memberMapper;

    @PostMapping
    public String login(@Valid @RequestBody LoginForm loginForm, Errors errors, HttpServletRequest request, HttpServletResponse response){
        Member dbMember = memberMapper.getMemberByEmail(loginForm.getEmail());
        boolean isFail = errors.hasErrors()
                || dbMember == null
                || !dbMember.getPassword().equals(loginForm.getPassword());

        response.addCookie(saveEmail(loginForm.getEmail(),loginForm.isSaveEmail() && !isFail));

        if(isFail){
            return "fail";
        } else{
            request.getSession().setAttribute("member",dbMember);
            return "success";
        }
    }

    @DeleteMapping
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("member");
        return "success";
    }

    @PostMapping("/signup")
    public String signUp(@Valid @RequestBody Member member, Errors errors){
        boolean isFail = errors.hasErrors()
                || memberMapper.getMemberByEmail(member.getEmail()) != null
                || !member.getPassword().equals(member.getPasswordCheck());

        if(isFail){
            return "fail";
        } else{
            memberMapper.save(member);
            return "success";
        }
    }

    public Cookie saveEmail(String email, boolean isSave){
        Cookie cookie = new Cookie("email", email);
        cookie.setPath("/");
        cookie.setMaxAge(isSave ? 60 * 60 : 0);
        return cookie;
    }
}
