package com.example.sportsdating;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.example.sportsdating.tokenizer.Token;
import com.example.sportsdating.tokenizer.Tokenizer;
/**
 * @Author Ke Yan
 */
public class TokenizerTest {
    @Test
    public void simpleTokenTest() {
        Tokenizer tokenizer= new Tokenizer("what is the  token");
        assertEquals(Token.Type.TITLE, tokenizer.current().getType());
        tokenizer.next();
        assertEquals(Token.Type.TITLE, tokenizer.current().getType());
        tokenizer.next();
        assertEquals(Token.Type.TITLE, tokenizer.current().getType());
        tokenizer.next();
        assertEquals(Token.Type.TITLE, tokenizer.current().getType());
        tokenizer.next();
        assertNull(tokenizer.current());
    }

    @Test
    public void headerTokenTest() {
        Tokenizer tokenizer= new Tokenizer("what  location: 16");
        assertEquals(Token.Type.TITLE, tokenizer.current().getType());
        tokenizer.next();
        assertEquals(Token.Type.LOCATION_HEADER, tokenizer.current().getType());
        tokenizer.next();
        assertNull(tokenizer.current());
    }

    @Test (timeout = 1000)
    public void formatStringTest() {
        Tokenizer tokenizer= new Tokenizer("what  location   :16");
        assertEquals(Token.Type.TITLE, tokenizer.current().getType());
        tokenizer.next();
        assertEquals(Token.Type.LOCATION_HEADER, tokenizer.current().getType());
        assertEquals("location:16", tokenizer.current().getToken());
        tokenizer.next();
        assertNull(tokenizer.current());

        Tokenizer tokenizer1= new Tokenizer("what  activity   :running");
        assertEquals(Token.Type.TITLE, tokenizer1.current().getType());
        tokenizer1.next();
        assertEquals(Token.Type.ACTIVITY_HEADER, tokenizer1.current().getType());
        assertEquals("activity:running", tokenizer1.current().getToken());
        tokenizer1.next();
        assertNull(tokenizer1.current());
    }

    @Test
    public void timeHeaderTokenTest() {
        Tokenizer tokenizer2= new Tokenizer("what  time   :1.2.3");
        assertEquals(Token.Type.TITLE, tokenizer2.current().getType());
        tokenizer2.next();
        assertEquals(Token.Type.TIME_HEADER, tokenizer2.current().getType());
        assertEquals("time:1.2.3", tokenizer2.current().getToken());
        tokenizer2.next();
        assertNull(tokenizer2.current());
    }



    @Test (expected = Token.IllegalTokenException.class)
    public void illegalTest() throws Token.IllegalTokenException{
        Tokenizer tokenizer= new Tokenizer("what  activity: 1");
        tokenizer.next();
        tokenizer.next();
    }

//    @Test (expected = Token.IllegalTokenException.class)
//    public void illegalTest1() throws Token.IllegalTokenException{
//        Tokenizer tokenizer= new Tokenizer("what  id:");
//        tokenizer.next();
//        tokenizer.next();
//    }
}
