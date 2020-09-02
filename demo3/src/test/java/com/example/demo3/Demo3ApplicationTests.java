package com.example.demo3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootTest
class Demo3ApplicationTests {

    @Autowired
    private UserRepository userRepository;
    @Test
    void contextLoads() {
        User user1 = new User();
        user1.setUserName("user01");
        String password = BCrypt.hashpw("123", BCrypt.gensalt());
        user1.setPassword(password);
        user1.setEnable(true);
        user1.setRoles("ROLE_user");
        userRepository.save(user1);
    }

}
