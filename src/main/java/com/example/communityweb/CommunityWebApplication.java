package com.example.communityweb;

import com.example.communityweb.domain.User;
import com.example.communityweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class CommunityWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityWebApplication.class, args);
    }

}
