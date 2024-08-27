package com.example.starttdd.supplement.c;

import com.example.starttdd.chapter7.testdouble.EmailNotifier;
import com.example.starttdd.chapter7.testdouble.UserRegister;
import com.example.starttdd.chapter7.testdouble.fake.MemoryUserRepository;
import com.example.starttdd.chapter7.testdouble.stub.StubWeakPasswordChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class UserRegisterMockTest {
    private UserRegister userRegister;
    private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier = mock(EmailNotifier.class);


    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker, fakeRepository, mockEmailNotifier);
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com");

        // String 타입의 인자를 보관할 수 있는 ArgumentCaptor 생성
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        then(mockEmailNotifier)
                .should()
                .sendRegisterEmail(captor.capture()); // captor.capture()를 통해 전달된 인자를 저장

        String realEmail = captor.getValue();
        assertEquals("email@email.com", realEmail);
    }
}
