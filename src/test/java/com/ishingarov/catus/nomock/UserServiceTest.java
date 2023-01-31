//package com.ishingarov.catus.nomock;
//
//import com.ishingarov.catus.model.UserRole;
//import com.ishingarov.catus.service.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@Slf4j
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//public class UserServiceTest {
//
//    @Autowired
//    private UserService userService;
//
//    @Test
//    public void createUserTest() {
//        User user = new User();
//        user.setName("TestName");
//        user.setLogin("TestLogin");
//        user.setPassword("TestPassword");
//        user.setRole(UserRole.STUDENT);
//        userService.createUser(user);
//    }
//}
////