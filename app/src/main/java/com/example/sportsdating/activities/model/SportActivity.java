package com.example.sportsdating.activities.model;

import java.util.Comparator;
import java.util.Date;
import java.util.Set;

public class SportActivity implements Comparable<SportActivity> {

    private Integer id;
    private String time;
    private String location;
    private String sportType;
    private Integer creatorId;
    private String title;
    private String description;
    private Set<Integer> members;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Integer> getMembers() {
        return members;
    }

    public void setMembers(Set<Integer> members) {
        this.members = members;
    }

    @Override
    public int compareTo(SportActivity sa) {
        String sportType = this.getTitle();
        String st = sa.getTitle();
        return sportType.compareTo(st);
    }

}
