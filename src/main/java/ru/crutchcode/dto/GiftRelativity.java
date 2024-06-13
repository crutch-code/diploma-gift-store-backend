package ru.crutchcode.dto;

public class GiftRelativity {

    private String category;

    private String ages;

    private Double percents;

    private int countScore;

    private int assignCount;

    private int disapproveScore;


    public String getCategory() {
        return category;
    }

    public GiftRelativity setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getAges() {
        return ages;
    }

    public GiftRelativity setAges(String ages) {
        this.ages = ages;
        return this;
    }

    public Double getPercents() {
        return percents;
    }

    public GiftRelativity setPercents(Double percents) {
        this.percents = percents;
        return this;
    }

    public int getCountScore() {
        return countScore;
    }

    public GiftRelativity setCountScore(int countScore) {
        this.countScore = countScore;
        return this;
    }

    public int getAssignCount() {
        return assignCount;
    }

    public GiftRelativity setAssignCount(int assignCount) {
        this.assignCount = assignCount;
        return this;
    }

    public int getDisapproveScore() {
        return disapproveScore;
    }

    public GiftRelativity setDisapproveScore(int disapproveScore) {
        this.disapproveScore = disapproveScore;
        return this;
    }
}
