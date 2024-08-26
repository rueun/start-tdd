package com.example.starttdd.chapter7.testdouble;

import com.example.starttdd.chapter7.testdouble.stub.StubWeakPasswordChecker;
import com.example.starttdd.chapter7.testdouble.stub.WeakPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserResisterTest {

    private UserRegister userResister;
    private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();


    @BeforeEach
    void setUp() {
        userResister = new UserRegister(stubWeakPasswordChecker);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {
        stubWeakPasswordChecker.setWeak(true); // 약한 암호로 설정

        assertThrows(WeakPasswordException.class, () -> {
            userResister.register("id", "pw", "email");
        });
    }
}
