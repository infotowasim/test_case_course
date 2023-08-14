package com.junit5demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringHelperTest {

   // StringHelper stringHelper = new StringHelper();
    StringHelper stringHelper;


    @BeforeEach
    public void setUp(){
        stringHelper = new StringHelper();
    }

    @Test
    void truncateAInFirst2Positions_AinFirst2Positions() {
        // String actual = stringHelper.truncateAInFirst2Positions("AACD");
        // String expected = "CD";
        // AACD=> CD, ACD=>CD, CDEF=>CDEF, CDAA=>CDAA
        assertEquals("CD", stringHelper.truncateAInFirst2Positions("AACD"));

    }


    @Test
    void truncateAInFirst2Positions_AinFirstPosition() {
        // String actual = stringHelper.truncateAInFirst2Positions("ACD");
        // String expected = "CD";
        // AACD=> CD, ACD=>CD, CDEF=>CDEF, CDAA=>CDAA
        assertEquals("CD", stringHelper.truncateAInFirst2Positions("ACD"));

    }

    @Test
    void areFirstAndLastTwoCharactersTheSame_BasicNegativeScenario() {
        // ABCD=> false, ABAB=> true, AB=>true, A=> false

        // boolean actualValue = stringHelper.areFirstAndLastTwoCharactersTheSame("ABCD");
        // boolean expectedValue = false;
      //  assertEquals(false, stringHelper.areFirstAndLastTwoCharactersTheSame("ABCD"));

//        assertFalse(stringHelper.areFirstAndLastTwoCharactersTheSame("ABCD"));
        assertFalse(stringHelper.areFirstAndLastTwoCharactersTheSame("ABCD"), "The condition failed");
    }


    @Test
    void areFirstAndLastTwoCharactersTheSame_BasicPositiveScenario() {
        // ABCD=> false, ABAB=> true, AB=>true, A=> false

        // boolean actualValue = stringHelper.areFirstAndLastTwoCharactersTheSame("ABAB");
        // boolean expectedValue = true;
        //  assertEquals(true, stringHelper.areFirstAndLastTwoCharactersTheSame("ABAB"));

        assertTrue(stringHelper.areFirstAndLastTwoCharactersTheSame("ABAB"));
    }
}