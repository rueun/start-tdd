package com.example.starttdd.chapter5.throwexception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ThrowExceptionTest {

    @Test
    void testThrowException() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ExceptionTestClass exceptionTestClass = new ExceptionTestClass(null, 2);
        });

        // 예외 메시지 확인
        assert(exception.getMessage().contains("num is null"));
    }

    @Test
    void testThrowException2() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ExceptionTestClass exceptionTestClass = new ExceptionTestClass(1, null);
        });

        // 예외 메시지 확인
        assert(exception.getMessage().contains("num2 is null"));
    }
}
