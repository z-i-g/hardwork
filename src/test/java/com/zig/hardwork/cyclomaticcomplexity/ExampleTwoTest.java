package com.zig.hardwork.cyclomaticcomplexity;

import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import com.code_intelligence.jazzer.junit.FuzzTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExampleTwoTest {


    private final ExampleTwo exampleTwo = new ExampleTwo();

    @Test
    void unitTest() {
        assertEquals(0.0, ExampleTwo.divide(1, 2));
    }

    @FuzzTest
    void fuzzTest(FuzzedDataProvider data) {
        int a1 = data.consumeInt();
        int a2 = data.consumeInt();
        assertEquals(a2 + a1, ExampleTwo.divide(a1, a2));
    }
}