package com.wu0089.websit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wu0089.websit.entity.User;
import com.wu0089.websit.repository.UserRepos;

@Service 
public class MemberService {
    private final UserRepos userRepos;

    public MemberService(UserRepos userRepos){
        this.userRepos= userRepos;
    }

    public List<User> findAll(){
        return  userRepos.findAll();
    }
}