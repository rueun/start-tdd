package com.example.starttdd.chapter7.testdouble.mock;

import com.example.starttdd.chapter7.testdouble.EmailNotifier;
import com.example.starttdd.chapter7.testdouble.UserRegister;
import com.example.starttdd.chapter7.testdouble.WeakPasswordChecker;
import com.example.starttdd.chapter7.testdouble.WeakPasswordException;
import com.example.starttdd.chapter7.testdouble.fake.MemoryUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;

class UserRegisterMockTest {
    private UserRegister userRegister;
    private WeakPasswordChecker mockPassWordChecker = mock(WeakPasswordChecker.class);
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier = mock(EmailNotifier.class);

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPassWordChecker, fakeRepository, mockEmailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {
        // mock 객체의 행동 설정
        // checkPasswordWeak("pw")가 호출되면 true를 리턴하도록 설정
        given(mockPassWordChecker.checkPasswordWeak("pw")).willReturn(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @DisplayName("회원가입 시 암호 검사 수행됨")
    @Test
    void checkPassword() {
        userRegister.register("id", "pw", "email");

        then(mockPassWordChecker)
                .should(only())
                .checkPasswordWeak("pw");
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        then(mockEmailNotifier)
                .should()
                .sendRegisterEmail(captor.capture());

        String realEmail = captor.getValue();
        assertEquals("email@email.com", realEmail);
    }
}
