package com.psj.coderHack.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
public class User {

    @Id
    private String userId;
    private String username;
    private int score;
    private Set<String> badges;

    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.score = 0;
        this.badges = new HashSet<>();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Set<String> getBadges() {
        return badges;
    }

    public void setBadges(Set<String> badges) {
        this.badges = badges;
    }

    private void setBadgesBasedOnScore(int score) {
        badges.clear();
        if (score >= 1 && score < 30) {
            badges.add("Code Ninja");
        }
        if (score >= 30 && score < 60) {
            badges.add("Code Champ");
        }
        if (score >= 60 && score <= 100) {
            badges.add("Code Master");
        }
    }
}
