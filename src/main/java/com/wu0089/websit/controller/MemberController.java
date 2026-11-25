package com.wu0089.websit.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wu0089.websit.entity.User;
import com.wu0089.websit.service.MemberService;


@Controller 
public class MemberController {
    
    private final MemberService memberService;
    public MemberController(MemberService memberService){
        this.memberService= memberService;
    }

    @GetMapping("/member")
    public String Member(Model model) {

        List<User> users = memberService.findAll();

        model.addAttribute("users", users);

        return "memberlist";
    }
    
}
