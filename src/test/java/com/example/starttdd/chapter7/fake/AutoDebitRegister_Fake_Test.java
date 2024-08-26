package com.example.starttdd.chapter7.fake;


import com.example.starttdd.chapter7.AutoDebitInfo;
import com.example.starttdd.chapter7.AutoDebitRegister;
import com.example.starttdd.chapter7.AutoDebitReq;
import com.example.starttdd.chapter7.RegisterResult;
import com.example.starttdd.chapter7.stub.StubCardNumberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AutoDebitRegister_Fake_Test {
    private AutoDebitRegister register;
    private StubCardNumberValidator stubValidator;
    private MemoryAutoDebitInfoRepository repository;

    @BeforeEach
    void setUp() {
        stubValidator = new StubCardNumberValidator();
        repository = new MemoryAutoDebitInfoRepository();
        register = new AutoDebitRegister(stubValidator, repository);
    }

    @DisplayName("이미 존재하는 사용자의 경우 정보를 업데이트한다")
    @Test
    void alreadyRegistered_InfoUpdated() {
        // given
        repository.save(new AutoDebitInfo("user1", "1234567890", LocalDateTime.now()));
        // when
        final AutoDebitReq req = new AutoDebitReq("user1", "2345678901");
        final RegisterResult result = this.register.register(req);
        // then
        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("2345678901", saved.getCardNumber());
    }

    @DisplayName("새로운 사용자의 경우 신규 등록한다")
    @Test
    void notYetRegistered_newInfoRegistered() {
        // given
        // when
        final AutoDebitReq req = new AutoDebitReq("user1", "2345678901");
        final RegisterResult result = this.register.register(req);
        // then
        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("2345678901", saved.getCardNumber());
    }
}
