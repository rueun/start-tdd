package com.example.starttdd.chapter5;

public record EqualTestRecord (int num, int num2) {
    public EqualTestRecord update() {
        return new EqualTestRecord(num, num2);
    }
}
