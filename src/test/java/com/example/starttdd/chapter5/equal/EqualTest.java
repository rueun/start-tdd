package com.example.starttdd.chapter5.equal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EqualTest {
    @Test
    void testAssertEquals() {
        final EqualTestClass equalTestClass = new EqualTestClass(1, 2);
        final EqualTestClass equalTestClass2 = new EqualTestClass(1, 2);

        // assertEquals는 객체의 equals 메서드를 호출하여 비교한다.
        assertEquals(equalTestClass, equalTestClass2);
    }

    @Test
    void testAssertNotEquals() {
        final EqualTestClass equalTestClass = new EqualTestClass(1, 2);
        final EqualTestClass equalTestClass2 = new EqualTestClass(1, 3);

        // assertEquals는 객체의 equals 메서드를 호출하여 비교한다.
        assertNotEquals(equalTestClass, equalTestClass2);
    }

    @Test
    void testAssertSame() {
        final EqualTestClass equalTestClass = new EqualTestClass(1, 2);
        final EqualTestClass equalTestClass2 = equalTestClass;

        assertSame(equalTestClass, equalTestClass2);
    }

    @Test
    void testAssertNotSame() {
        final EqualTestClass equalTestClass = new EqualTestClass(1, 2);
        final EqualTestClass equalTestClass2 = new EqualTestClass(1, 2);

        // assertNotSame는 객체의 주소값이 다른지를 비교한다.
        assertNotSame(equalTestClass, equalTestClass2);
    }

    @Test
    void testAssertNotSame2() {
        final EqualTestClass equalTestClass = new EqualTestClass(1, 2);
        final EqualTestClass equalTestClass2 = equalTestClass.update();

        // assertNotSame는 객체의 주소값이 다른지를 비교한다.
        assertNotSame(equalTestClass, equalTestClass2);
    }

    @Test
    void testAssertEqualsRecord() {
        EqualTestRecord record = new EqualTestRecord(1, 2);
        EqualTestRecord record2 = new EqualTestRecord(1, 2);

        assertEquals(record, record2);
    }

    @Test
    void testAssertNotEqualsRecord() {
        EqualTestRecord record = new EqualTestRecord(1, 2);
        EqualTestRecord record2 = new EqualTestRecord(1, 3);

        assertNotEquals(record, record2);
    }

    @Test
    void testAssertSameRecord() {
        EqualTestRecord record = new EqualTestRecord(1, 2);
        EqualTestRecord record2 = record;

        assertSame(record, record2);
    }

    @Test
    void testAssertNotSameRecord() {
        EqualTestRecord record = new EqualTestRecord(1, 2);
        EqualTestRecord record2 = new EqualTestRecord(1, 2);

        assertNotSame(record, record2);
    }
}
