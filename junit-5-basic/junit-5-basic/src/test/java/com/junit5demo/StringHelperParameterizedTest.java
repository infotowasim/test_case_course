//package com.junit5demo;
//
//
//import org.junit.jupiter.api.Test;
//
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//
//
//import java.lang.reflect.ParameterizedType;
//import java.util.Arrays;
//import java.util.Collection;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(Parameterized.class)
//class StringHelperParameterizedTest {
//
//    StringHelper stringHelper = new StringHelper();
//
//    private String input;
//    private String expectedOutput;
//
//    public StringHelperParameterizedTest(String input, String expectedOutput) {
//        this.input = input;
//        this.expectedOutput = expectedOutput;
//    }
//
//
//    @Parameter
//    public static Collection<String[]> testCondition(){
//
//        String  expectedOutputs [][] =
//                {
//                {"AACD","CD"},{"ACD","CD"} };
//        return Arrays.asList(expectedOutputs);
//    }
//
//
//    @Test
//    void truncateAInFirst2Positions() {
//        // String actual = stringHelper.truncateAInFirst2Positions("AACD");
//        // String expected = "CD";
//        // AACD=> CD, ACD=>CD, CDEF=>CDEF, CDAA=>CDAA
//        assertEquals(expectedOutput, stringHelper.truncateAInFirst2Positions(input));
//
//    }
//}