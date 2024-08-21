package com.example.starttdd.chapter5.lifecycle;

import org.junit.jupiter.api.*;

class LifecycleTest {

    // 각 테스트 메서드 실행 전 실행
    public LifecycleTest() {
        System.out.println("new LifecycleTest");
    }

    // 한 클래스 내의 각 테스트 메서드 실행 전 실행
    @BeforeEach
    void setUp() {
        System.out.println("setUp");
    }

    // 한 클래스 내의 모든 테스트 메서드 실행 전 한 번만 실행
    @BeforeAll
    static void beforeAll() {
        System.out.println("beforeAll");
    }

    // 한 클래스 내의 각 테스트 메서드 실행 후 실행
    @AfterEach
    void tearDown() {
        System.out.println("tearDown");
    }

    // 한 클래스 내의 모든 테스트 메서드 실행 후 한 번만 실행
    @AfterAll
    static void afterAll() {
        System.out.println("afterAll");
    }

    @Test
    void test1() {
        System.out.println("test1");
    }

    @Test
    void test2() {
        System.out.println("test2");
    }
}
