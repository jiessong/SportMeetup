package com.example.sportsdating;

import com.example.sportsdating.activities.model.SportActivity;
import com.example.sportsdating.activities.service.SportActivityService;

import org.junit.Test;

import java.util.Set;

public class ActivitiesTest {

    @Test
    public void testAdd() {
        SportActivityService sportActivityService = SportActivityService.getInstance();
        for (int i = 1; i < 5; i++) {
            SportActivity sportActivity = new SportActivity();
            sportActivity.setCreatorId(1);
            sportActivity.setDescription("let's play" + i);
            sportActivity.setSportType("basketball"+ i);
            sportActivity.setLocation("ANU"+ i);
            sportActivity.setTitle("let's run" + i);
            sportActivity.setTime("2022-5-" + i);
            sportActivityService.saveActivity(sportActivity);
        }

    }

    @Test
    public void testDelete() {
        SportActivityService sportActivityService = SportActivityService.getInstance();
        for (int i = 1; i < 30; i++) {
            sportActivityService.deleteActivity(i);
        }
    }

    @Test
    public void testUpdate() {
        SportActivityService sportActivityService = SportActivityService.getInstance();
        SportActivity sportActivity = new SportActivity();
        sportActivity.setCreatorId(1);
        sportActivity.setDescription("let's play!!!!!!!!!!");
        sportActivity.setSportType("running");
        sportActivity.setLocation("ANU");
        sportActivity.setTime("");
        sportActivity.setTitle("let's run!!!!!!!!!");
        sportActivityService.updateActivity(2, sportActivity);
    }

    @Test
    public void testJoin() {
        SportActivityService sportActivityService = SportActivityService.getInstance();
        sportActivityService.joinActivity(2, 2);
    }

    @Test
    public void testQuit() {
        SportActivityService sportActivityService = SportActivityService.getInstance();
        sportActivityService.quitActivity(2, 2);
    }

    @Test
    public void testFind() {
        SportActivityService sportActivityService = SportActivityService.getInstance();
        Set<SportActivity> running = sportActivityService.findActivityBySportType("basketball");
        System.out.println(running.toString());
    }

}
