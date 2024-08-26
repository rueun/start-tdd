package com.example.starttdd.chapter7.testdouble;

import com.example.starttdd.chapter7.testdouble.fake.MemoryUserRepository;
import com.example.starttdd.chapter7.testdouble.stub.StubWeakPasswordChecker;
import com.example.starttdd.chapter7.testdouble.stub.WeakPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserResisterTest {

    private UserRegister userResister;
    private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();


    @BeforeEach
    void setUp() {
        userResister = new UserRegister(stubWeakPasswordChecker, fakeRepository);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {
        stubWeakPasswordChecker.setWeak(true); // 약한 암호로 설정

        assertThrows(WeakPasswordException.class, () -> {
            userResister.register("id", "pw", "email");
        });
    }

    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    @Test
    void dupIdExists() {
        fakeRepository.save(new User("id", "pw", "email"));

        assertThrows(DupIdException.class, () -> {
            userResister.register("id", "pw2", "email");
        });
    }
}
