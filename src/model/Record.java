package model;

import java.io.Serializable;

public class Record implements Serializable {

    private final String name;
    private String definition;
    private String review;
    private Status status;

    public Record(String name, String definition, Status status) {
        this.name = name;
        this.definition = definition;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getDefinition() {
        return definition;
    }

    public Status getStatus() {
        return status;
    }

    public String getReview() {return review; }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setReview(String review) {this.review = review;}
}
