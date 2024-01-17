package com.example.sportsdating;

import com.example.sportsdating.datagenerator.DataGenerator;
import com.example.sportsdating.post.dao.PostDAOImpl;
import com.example.sportsdating.post.model.Post;
import com.example.sportsdating.user.dao.UserDAOImp;
import com.example.sportsdating.user.model.User;

import org.junit.Test;

import java.util.List;

public class DataGeneratorTest {
    @Test
    public void testScheduleGenerator(){
        DataGenerator dataGenerator = DataGenerator.getInstance();
        //dataGenerator.scheduledStream();
    }
    @Test
    public void testStopGenerator(){
        DataGenerator.getInstance().stopStream();
    }
}
