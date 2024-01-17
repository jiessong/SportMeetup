package com.example.sportsdating.datagenerator;

import com.example.sportsdating.datagenerator.action.Action;
import com.example.sportsdating.datagenerator.action.ActionFactory;

import java.util.Timer;
import java.util.TimerTask;

/**
 *  Control the data stream process to be performed every x seconds
 *
*/
public class DataGenerator {
    private static DataGenerator dataGenerator;
    private static Timer timer;
    private Action action;

    private DataGenerator() {
    }

    public static DataGenerator getInstance() {
        if (dataGenerator == null) {
            dataGenerator = new DataGenerator();
        }
        return dataGenerator;
    }

    /**
     * Timer is used to schedule a task and execute every x seconds
     * reference: https://stackoverflow.com/questions/54088211/how-can-i-execute-a-method-every-x-second-for-y-time-in-java
     */
    public void scheduledStream() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                dataStream();
                System.out.println("data stream running...");
            }
        };
        try {
            timer.schedule(timerTask, 0, 3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopStream() {
        try {
            timer.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("cancel timer");
    }


    /**
     * Get an action type, then call ActionFactory to create an Action object to do the action
     */
    public void dataStream() {
        action = ActionFactory.getInstance().createAction(ActionType.getType());
        if(action!=null){
            System.out.println("Simulating:" + action);
            action.performAction();
        }
    }
}
