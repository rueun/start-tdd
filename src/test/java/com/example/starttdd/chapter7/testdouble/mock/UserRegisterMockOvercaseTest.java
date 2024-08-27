package com.example.starttdd.chapter7.testdouble.mock;

import com.example.starttdd.chapter7.testdouble.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class UserRegisterMockOvercaseTest {
    private UserRegister userRegister;
    private WeakPasswordChecker mockPassWordChecker = mock(WeakPasswordChecker.class);
    private UserRepository mockRepository = mock(UserRepository.class);
    private EmailNotifier mockEmailNotifier = mock(EmailNotifier.class);

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPassWordChecker, mockRepository, mockEmailNotifier);
    }


    @DisplayName("중복되지 않은 ID면 가입 성공")
    @Test
    void noDupId_RegisterSuccess() {
        userRegister.register("id", "pw", "email@email.com");

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        then(mockRepository)
                .should()
                .save(captor.capture());

        User savedUser = captor.getValue();
        assertEquals("id", savedUser.getId());
        assertEquals("email@email.com", savedUser.getEmail());
    }
}
