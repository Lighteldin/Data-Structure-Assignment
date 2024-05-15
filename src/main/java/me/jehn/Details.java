package me.jehn;

import java.math.BigDecimal;

public class Details {
    private String arrayType;
    private double time;
    private long comparisons;
    private long interchanges;

    public Details(String arrayType, double time, long comparisons, long interchanges) {
        this.arrayType = arrayType;
        this.time = Math.round(time * 100.0) / 100.0;
        this.comparisons = comparisons;
        this.interchanges = interchanges;
    }

    public String getArrayType() {
        return arrayType;
    }

    public void setArrayType(String arrayType) {
        this.arrayType = arrayType;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public long getComparisons() {
        return comparisons;
    }

    public void setComparisons(long comparisons) {
        this.comparisons = comparisons;
    }

    public long getInterchanges() {
        return interchanges;
    }

    public void setInterchanges(long interchanges) {
        this.interchanges = interchanges;
    }
}
