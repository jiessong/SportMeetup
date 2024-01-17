package com.example.sportsdating.activities.service;


import com.example.sportsdating.activities.Tree;
import com.example.sportsdating.activities.dao.SportActivityDao;
import com.example.sportsdating.activities.model.SportActivity;


import java.util.List;
import java.util.Set;

public class SportActivityService {

    private static SportActivityDao dao = SportActivityDao.getInstance();

    private static SportActivityService instance;

    public Tree<SportActivity> tree = new Tree<>();

    public static SportActivityService getInstance() {
        if (instance == null) {
            instance = new SportActivityService();
        }
        return instance;
    }

    private SportActivityService() {
        List<SportActivity> allActivity = findAllActivity();
        for (SportActivity ac : allActivity) {
            tree.insert(ac);
        }
    }

    public Set<SportActivity> findActivityBySportType(String sportType) {
        SportActivity needFind = new SportActivity();
        needFind.setSportType(sportType);
        Set<SportActivity> all = tree.findAll(needFind);
        return all;
    }

    public static List<SportActivity> findAllActivity() {
        return dao.findAllActivities();
    }

    public void saveActivity(SportActivity activity) {
        dao.saveActivity(activity);
    }

    public void deleteActivity(Integer activityId) {
        dao.deleteActivity(activityId);
    }

    public void updateActivity(Integer activityId, SportActivity activity) {
        dao.updateActivity(activityId, activity);
    }

    public void joinActivity(Integer userId, Integer activityId) {
        dao.joinActivity(activityId, userId);
    }

    public void quitActivity(Integer userId, Integer activityId) {
        dao.quitActivity(activityId, userId);
    }

    public List<SportActivity> findMyActivity(Integer userId) {
        return dao.findMyActivity(userId);
    }

}
