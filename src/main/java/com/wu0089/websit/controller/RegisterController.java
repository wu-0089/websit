package com.wu0089.websit.controller;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wu0089.websit.entity.User;
import com.wu0089.websit.repository.UserRepos;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller 
public class RegisterController {
    
    private final UserRepos userRepos;
    private final PasswordEncoder passwordEncoder;
    public RegisterController(UserRepos userRepos, PasswordEncoder passwordEncoder){
        this.userRepos=         userRepos;
        this.passwordEncoder=   passwordEncoder;
    }

    @GetMapping ("/register")
    public String register(){
        return "register";
    } 

    @PostMapping("/register")
    public String postMethodName(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String email,
                                 @RequestParam String confirmPassword,
                                               Model model) {
        if(!password.equals(confirmPassword)){
            model.addAttribute("erro", "兩次密碼不一致");
            return "register";
        }
        //Bcrypt加密
        String encodPassword = passwordEncoder.encode(password);
        
        //建立user物件
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodPassword);
        user.setEmail(email);
        user.setCreatetime(LocalDateTime.now());
        user.setRole("user");
        try {
            userRepos.save(user);
        } catch (Exception e) {
            throw new RuntimeException("註冊失敗"+ e.getMessage());
        }
        
        
        return "redirect:/login";
    }
    

}

