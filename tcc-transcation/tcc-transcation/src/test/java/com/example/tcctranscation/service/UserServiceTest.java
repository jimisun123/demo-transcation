package com.example.tcctranscation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void main(){
        userService.testXaTranscation();
    }

}