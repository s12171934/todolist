package com.todo.todolist.login;

import com.todo.todolist.login.dto.LoginForm;
import com.todo.todolist.login.dto.Member;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("")
public class SimpleLoginController {
    static HashMap<String,Member> memberList = new HashMap<>();

    @GetMapping("/login")
    public String loginForm(){
        return "redirect:/index.html";
    }
    @GetMapping("/signup")
    public String signUpForm(){
        return "redirect:/signup/signup.html";
    }
    @GetMapping("/success")
    public String loginSuccess(){return "redirect:/login/login-success.html";}
    @GetMapping("/fail")
    public String loginFail(){return "redirect:/login/login-fail.html";}
    @GetMapping("/logout")
    public String logoutForm(){return "redirect:/login/logout.html";}
    @GetMapping("/todo")
    public String afterLogin(){return "redirect:/todo/todo.html";}

    @PostMapping("/login")
    public String login(@Valid LoginForm loginForm, Errors errors, HttpServletRequest request, HttpServletResponse response){
        boolean isFail = errors.hasErrors()
                || !memberList.containsKey(loginForm.getEmail())
                || !memberList.get(loginForm.getEmail()).getPassword().equals(loginForm.getPassword());
        if(isFail){
            return "redirect:/fail";
        } else{
            request.getSession().setAttribute("member",memberList.get(loginForm.getEmail()));
            response.addCookie(saveEmail(loginForm.getEmail(),loginForm.isSaveEmail()));
            return "redirect:/success";
        }
    }
    @PostMapping("/signup")
    public String signUp(@Valid Member member, Errors errors){
        boolean isFail = errors.hasErrors()
                || memberList.containsKey(member.getEmail())
                || !member.getPassword().equals(member.getPasswordCheck());

        if(isFail){
            return "redirect:/signup";
        } else{
            memberList.put(member.getEmail(),member);
            return "redirect:/signup/signup-success.html";
        }
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("member");
        return "redirect:/login";
    }
    public boolean isLogin(HttpServletRequest request){
       HttpSession session = request.getSession();
       return session.getAttribute("member") != null;
    }
    public Cookie saveEmail(String email, boolean isSave){
        Cookie cookie = new Cookie("email", email);
        cookie.setPath("/");
        cookie.setMaxAge(isSave ? 60 * 60 : 0);
        return cookie;
    }
}