package com.wu0089.websit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth

                // 需要登入
                .requestMatchers("/logout" 
                                 ).authenticated()
                
                // 其他頁面不用
                .anyRequest().permitAll()
            )

            // 使用登入表單
            .formLogin(form -> form

                // 使用自己的登入頁
                .loginPage("/login")

                // 登入成功後回首頁
                .defaultSuccessUrl("/", true)

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

        return PasswordEncoderFactories
                .createDelegatingPasswordEncoder();
    }


    /*
     * 暫時測試用
     * 使用者目前存在記憶體
     */
    @Bean
    public UserDetailsService userDetailsService(
            PasswordEncoder passwordEncoder) {

        UserDetails admin = User
                .withUsername("admin")
                .password(passwordEncoder.encode("123456"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
}

