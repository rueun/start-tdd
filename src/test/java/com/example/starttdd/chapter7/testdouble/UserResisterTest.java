package com.example.starttdd.chapter7.testdouble;

import com.example.starttdd.chapter7.testdouble.fake.MemoryUserRepository;
import com.example.starttdd.chapter7.testdouble.stub.StubWeakPasswordChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserResisterTest {

    private UserRegister userResister;
    private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();


    @BeforeEach
    void setUp() {
        userResister = new UserRegister(stubWeakPasswordChecker, fakeRepository, spyEmailNotifier);
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

    @DisplayName("같은 ID가 없으면 가입 성공")
    @Test
    void noDupId_RegisterSuccess() {
        userResister.register("id", "pw", "email");

        User savedUser = fakeRepository.findById("id").get();

        // 저장된 사용자 정보가 일치하는지 확인
        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());
    }

    @DisplayName("가입하면 이메일을 전송함")
    @Test
    void whenRegisterThenSendMail() {
        userResister.register("id", "pw", "email@email.com");

        // 이메일을 전송했는지 확인
        assertTrue(spyEmailNotifier.isCalled());
        assertEquals("email@email.com", spyEmailNotifier.getEmail()));

    }
}
