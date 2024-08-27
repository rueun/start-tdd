package com.example.starttdd.supplement.c;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class AnyMatcherTest {

    @Test
    void anyTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(any())).willReturn("123");

        String num = genMock.generate(GameLevel.EASY);
        assertEquals("123", num);

        String num2 = genMock.generate(GameLevel.NORMAL);
        assertEquals("123", num2);
    }
}
