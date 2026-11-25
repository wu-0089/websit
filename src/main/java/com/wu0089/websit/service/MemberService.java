package com.wu0089.websit.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wu0089.websit.entity.User;
import com.wu0089.websit.repository.UserRepos;


@Service 
public class MemberService {
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  UserRepos userRepos;

    public void register(User user){
        //Bcrypt加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setCreatetime(LocalDateTime.now());
        user.setRole("user");
        userRepos.save(user);
    }

    public List<User> findAll(){
        return  userRepos.findAll();
    }
    
    
    public void delete(Integer id){
        userRepos.deleteById(id);
    }

}