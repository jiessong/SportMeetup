package com.example.sportsdating.activities.dao;

import com.example.sportsdating.activities.model.SportActivity;
import com.example.sportsdating.utils.FileUtil;
import com.example.sportsdating.utils.JSONUtil;
import com.example.sportsdating.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SportActivityDao {

    private static Integer maxId = 0;

    private static SportActivityDao instance;

    private static Map<Integer, SportActivity> activityMap = new HashMap<>();

    private final static String filePath = "sportActivity.json";

    private SportActivityDao() {
        getAllActivities();
    }

    public static SportActivityDao getInstance() {
        if (instance == null) {
            instance = new SportActivityDao();
        }
        return instance;
    }

    public static void getAllActivities() {
        String allData = FileUtil.readFile(filePath);
        if (StringUtil.isBlank(allData)) {
            return;
        }
        JSONUtil jsonUtil = new JSONUtil();
        List<SportActivity> allActivities = jsonUtil.jsonStringToObjList(allData, SportActivity.class);
        for (SportActivity activity : allActivities) {
            activityMap.put(activity.getId(), activity);
            if (activity.getId() > maxId) {
                maxId = activity.getId();
            }
        }
    }

    public List<SportActivity> findAllActivities() {
        return new ArrayList<>(activityMap.values());
    }

    public List<SportActivity> findByName(String name) {
        return null;
    }

    public List<SportActivity> findByLocation(String location) {
        return null;
    }

    public void saveActivity(SportActivity needSave) {
        maxId = maxId + 1;
        needSave.setId(maxId);
        activityMap.put(needSave.getId(), needSave);
        saveData();
    }

    public void joinActivity(Integer activityId, Integer userId) {
        SportActivity join = activityMap.get(activityId);
        Set<Integer> members = join.getMembers();
        if (members == null) {
            members = new HashSet<>();
            members.add(userId);
            join.setMembers(members);
        }
        members.add(userId);
        saveData();
    }

    public void quitActivity(Integer activityId, Integer userId) {
        SportActivity quit = activityMap.get(activityId);
        Set<Integer> members = quit.getMembers();
        members.remove(userId);
        saveData();
    }

    public void saveData() {
        JSONUtil jsonUtil = new JSONUtil();
        String allData = jsonUtil.objListToJSONString(new ArrayList(activityMap.values()));
        try {
            FileUtil.writeFile(filePath, allData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateActivity(Integer activityId, SportActivity updateInfo) {
        SportActivity sportActivity = activityMap.get(activityId);
        sportActivity.setTitle(updateInfo.getTitle());
        sportActivity.setSportType(updateInfo.getSportType());
        sportActivity.setDescription(updateInfo.getDescription());
        sportActivity.setTime(updateInfo.getTime());
        sportActivity.setLocation(updateInfo.getLocation());
        saveData();
    }

    public void deleteActivity(Integer activityId) {
        activityMap.remove(activityId);
        saveData();
    }

    public List<SportActivity> findMyActivity(Integer userId) {
        ArrayList<SportActivity> list = new ArrayList<>();
        List<SportActivity> allActivities = findAllActivities();
        for (SportActivity sa : allActivities) {
            if (sa.getMembers() != null && sa.getMembers().contains(userId)) {
                list.add(sa);
            } else if (sa.getCreatorId().equals(userId)) {
                list.add(sa);
            }
        }
        return list;
    }

}
