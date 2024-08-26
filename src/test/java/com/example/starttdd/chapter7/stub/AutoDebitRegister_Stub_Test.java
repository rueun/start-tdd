package com.example.starttdd.chapter7.stub;


import com.example.starttdd.chapter7.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AutoDebitRegister_Stub_Test {
    private AutoDebitRegister register;
    private StubCardNumberValidator stubValidator;
    private StubAutoDebitInfoRepository stubRepository;

    @BeforeEach
    void setUp() {
        stubValidator = new StubCardNumberValidator();
        stubRepository = new StubAutoDebitInfoRepository();
        register = new AutoDebitRegister(stubValidator, stubRepository);
    }


    @DisplayName("유효하지 않은 카드번호인 경우 등록 실패를 반환한다")
    @Test
    void invalidCard() {
        // given
        var invalidCardNumber = "111122223333";
        stubValidator.setInvalidNo(invalidCardNumber);
        AutoDebitReq req = new AutoDebitReq("user1", invalidCardNumber);
        // when
        final RegisterResult register = this.register.register(req);
        // then
        assertEquals(CardValidity.INVALID, register.getValidity());
    }

    @DisplayName("도난 신고된 카드번호인 경우 등록 실패를 반환한다")
    @Test
    void theftCard() {
        // given
        var theftCardNumber = "1234123412341234";
        stubValidator.setTheftNo(theftCardNumber);
        AutoDebitReq req = new AutoDebitReq("user1", theftCardNumber);
        // when
        final RegisterResult register = this.register.register(req);
        // then
        assertEquals(CardValidity.THEFT, register.getValidity());
    }
}
