package com.example.starttdd.supplement.c;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameGenMockTest {
    @Test
    void mockTest() {
        // 1. Mock 객체 생성
        GameNumGen genMock = Mockito.mock(GameNumGen.class);
        // 2. Mock 객체의 행동 설정(스텁 설정)
        BDDMockito.given(genMock.generate(GameLevel.EASY)).willReturn("123");
        // 3. 스텁 설정에 매칭되는 메서드 호출
        String num = genMock.generate(GameLevel.EASY);
        assertEquals("123", num);
    }

    @Test
    void mockThrowTest() {
        GameNumGen genMock = Mockito.mock(GameNumGen.class);
        BDDMockito.given(genMock.generate(null)).willThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> genMock.generate(null));
    }
}

