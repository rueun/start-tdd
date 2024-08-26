package com.example.starttdd.chapter7;


import com.example.starttdd.chapter7.stub.StubAutoDebitInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AutoDebitRegisterTest {
    private AutoDebitRegister register;

    @BeforeEach
    void setUp() {
        AutoDebitInfoRepository repository = new StubAutoDebitInfoRepository();
        CardNumberValidator validator = new CardNumberValidator();
        register = new AutoDebitRegister(validator, repository);
    }


    @DisplayName("정상적인 신청이면 등록 성공을 반환한다")
    @Test
    void validCard() {
        // given
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
        // when
        final RegisterResult register = this.register.register(req);
        // then
        assertEquals(CardValidity.VALID, register.getValidity());
    }

    @DisplayName("유효하지 않은 카드번호인 경우 등록 실패를 반환한다")
    @Test
    void invalidCard() {
        // given
        AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
        // when
        final RegisterResult register = this.register.register(req);
        // then
        assertEquals(CardValidity.INVALID, register.getValidity());
    }

    @DisplayName("도난 신고된 카드번호인 경우 등록 실패를 반환한다")
    @Test
    void theftCard() {
        // given
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
        // when
        final RegisterResult register = this.register.register(req);
        // then
        assertEquals(CardValidity.THEFT, register.getValidity());
    }
}
