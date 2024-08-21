package com.example.starttdd.chapter5.equal;

public class EqualTestClass {
    private final int num;
    private final int num2;

    public EqualTestClass(int num, int num2) {
        this.num = num;
        this.num2 = num2;
    }

    public EqualTestClass update() {
        return new EqualTestClass(num, num2);
    }

    // equals 메서드를 오버라이드하여 객체의 필드값을 비교한다.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EqualTestClass that)) return false;
        return num == that.num && num2 == that.num2;
    }
}
