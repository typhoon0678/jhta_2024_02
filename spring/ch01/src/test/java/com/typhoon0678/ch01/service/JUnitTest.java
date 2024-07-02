package com.typhoon0678.ch01.service;

import org.junit.jupiter.api.*;

public class JUnitTest {

    @BeforeEach
    void beforeEach() {
        System.out.println("=============before===============");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("=============before All============");
    }

    @AfterEach
    void afterEach() {
        System.out.println("=============after==============");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("=============after All=============");
    }

    @Test
    void unitTest01() {
        System.out.println("first test");
    }

    @Test
    void unitTest02() {
        System.out.println("second test");
    }
}
