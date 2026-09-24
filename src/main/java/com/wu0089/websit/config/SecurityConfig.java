package com.wu0089.websit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

import com.wu0089.websit.repository.UserRepos;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth

                // 需要登入
                //.requestMatchers("/products/add").hasRole("ADMIN")
                .requestMatchers("/logout","/services" 
                                 ).authenticated()
                
                // 其他頁面不用
                .anyRequest().permitAll()
            )

            // 使用登入表單
            .formLogin(form -> form

                // 使用自己的登入頁
                .loginPage("/login")

                // 登入成功後回首頁
                .defaultSuccessUrl("/")

                .permitAll()
            )

            // 登出
            .logout(logout -> logout

                // 登出成功回登入頁
                .logoutSuccessUrl("/login")

                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
    }

    @Bean
public UserDetailsService userDetailsService(UserRepos userRepos) {

    return username -> {

        com.wu0089.websit.entity.User user =
                userRepos.findByUsername(username)
                        .orElseThrow(() ->
                                new UsernameNotFoundException("查無此使用者"));

        return User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    };
}
}