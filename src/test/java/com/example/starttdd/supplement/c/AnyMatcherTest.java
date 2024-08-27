package com.example.starttdd.supplement.c;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
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


    @Test
    void anyTest2() {
        List<String> mockList = mock(List.class);
        given(mockList.set(anyInt(), eq("123"))).willReturn("456");

        String old = mockList.set(0, "123");
        assertEquals("456", old);
    }
}
