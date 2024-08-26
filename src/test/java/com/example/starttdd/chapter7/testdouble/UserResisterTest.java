package com.example.starttdd.chapter7.testdouble;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserResisterTest {

    private UserResister userResister;
    private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();

    @BeforeEach
    void setUp() {
        userResister = new UserResister(stubWeakPasswordChecker);
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
