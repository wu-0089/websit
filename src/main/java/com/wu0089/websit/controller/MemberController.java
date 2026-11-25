package com.wu0089.websit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wu0089.websit.entity.User;
import com.wu0089.websit.service.MemberService;



@Controller 
@RequestMapping("/member")
public class MemberController {
    
    @Autowired
    private  MemberService memberService;


    @GetMapping
    public String Member(Model model) {

        List<User> users = memberService.findAll();

        model.addAttribute("users", users);

        return "memberlist";
    }
    
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id){
        memberService.delete(id);
        return "redirect:/member";
    }

}
