package com.main.triviaquizapp.entity;

public abstract class Trivia {
    private String id;
    private String triviaTopic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTriviaTopic() {
        return triviaTopic;
    }

    public void setTriviaTopic(String triviaTopic) {
        this.triviaTopic = triviaTopic;
    }
}
