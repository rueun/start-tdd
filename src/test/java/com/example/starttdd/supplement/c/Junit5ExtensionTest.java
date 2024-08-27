package com.example.starttdd.supplement.c;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class Junit5ExtensionTest {
    @Mock
    private GameNumGen genMock;

    @Test
    void mockTest() {
        BDDMockito.given(genMock.generate(GameLevel.EASY)).willReturn("123");
        String num = genMock.generate(GameLevel.EASY);
        assertEquals("123", num);
    }

    @Test
    void mockThrowTest() {
        BDDMockito.given(genMock.generate(null)).willThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> genMock.generate(null));
    }
}

