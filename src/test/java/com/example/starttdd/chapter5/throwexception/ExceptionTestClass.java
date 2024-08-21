package com.example.starttdd.chapter5.throwexception;

import java.util.Objects;

public class ExceptionTestClass {

    private final Integer num;
    private final Integer num2;

    public ExceptionTestClass(Integer num, Integer num2) {
        if (Objects.isNull(num)) { throw new IllegalArgumentException("num is null"); }
        if (Objects.isNull(num2)) { throw new IllegalArgumentException("num2 is null"); }

        this.num = num;
        this.num2 = num2;
    }
}
