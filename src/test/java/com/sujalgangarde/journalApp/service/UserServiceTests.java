package com.sujalgangarde.journalApp.service;

import com.sujalgangarde.journalApp.entity.User;
import com.sujalgangarde.journalApp.repository.UserRepository;
import com.sujalgangarde.journalApp.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user) {
        assertTrue(userService.saveNewUser(user));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1, 2, 3",
            "4, 5, 8",
    })
    public void test(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }


}
