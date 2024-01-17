package com.example.sportsdating;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

import com.example.sportsdating.tokenizer.Parser;
import com.example.sportsdating.tokenizer.Tokenizer;
/**
 * @Author Ke Yan
 */
public class ParserTest {
    @Test (timeout = 1000)
    public void simpleTest() {
        Tokenizer tokenizer= new Tokenizer("what is the  token");
        Parser parser = new Parser(tokenizer);
        parser.parseExp();
        assertEquals(4, parser.output().get("title").size());
        assertEquals(0, parser.output().get("time").size());
        assertEquals(0, parser.output().get("activity").size());

        Tokenizer tokenizer1= new Tokenizer("what is the  token time: 15");
        Parser parser1 = new Parser(tokenizer1);
        parser1.parseExp();
        assertEquals(4, parser1.output().get("title").size());
        assertEquals(1, parser1.output().get("time").size());
        assertEquals("15", parser1.output().get("time").get(0));

        Tokenizer tokenizer2= new Tokenizer("what is the  token location: 15");
        Parser parser2 = new Parser(tokenizer2);
        parser2.parseExp();
        assertEquals(4, parser2.output().get("title").size());
        assertEquals(1, parser2.output().get("location").size());
        assertEquals("15", parser2.output().get("location").get(0));
    }

    @Test (timeout = 1000)
    public void timeTest() {
        Tokenizer tokenizer2= new Tokenizer("time: 15");
        Parser parser2 = new Parser(tokenizer2);
        parser2.parseExp();
        assertEquals(0, parser2.output().get("title").size());
        assertEquals(1, parser2.output().get("time").size());
        assertEquals("15", parser2.output().get("time").get(0));
    }



    @Test (timeout = 1000)
    public void activityInTitleTest() {
        Tokenizer tokenizer= new Tokenizer("what is the  running");
        Parser parser = new Parser(tokenizer);
        parser.parseExp();
        assertEquals(4, parser.output().get("title").size());
        assertEquals(0, parser.output().get("location").size());
        assertEquals(1, parser.output().get("activity").size());
    }

    @Test (timeout = 1000)
    public void complicateTest() {
        Tokenizer tokenizer= new Tokenizer("what is the  running  activity :basketball what1 ");
        Parser parser = new Parser(tokenizer);
        parser.parseExp();
        System.out.println(parser.output());

        assertEquals(5, parser.output().get("title").size());
        assertEquals(2, parser.output().get("activity").size());
    }
}
