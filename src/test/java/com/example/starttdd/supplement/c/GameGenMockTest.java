package com.example.starttdd.supplement.c;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameGenMockTest {
    @Test
    void mockTest() {
        GameNumGen genMock = Mockito.mock(GameNumGen.class);
        BDDMockito.given(genMock.generate(new GameLevel())).willReturn("123");

        String num = genMock.generate(new GameLevel());
        assertEquals("123", num);
    }
}
