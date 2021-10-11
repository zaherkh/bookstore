package com.zaher.bookstore.bookstore.statistics;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Statistics {

    private String month;
    private Integer totalOrderCount;
    private Integer totalBookCount;
    private float totalPurchasedAmount;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getTotalOrderCount() {
        return totalOrderCount;
    }

    public void setTotalOrderCount(Integer totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    public Integer getTotalBookCount() {
        return totalBookCount;
    }

    public void setTotalBookCount(Integer totalBookCount) {
        this.totalBookCount = totalBookCount;
    }

    public float getTotalPurchasedAmount() {
        return totalPurchasedAmount;
    }

    public void setTotalPurchasedAmount(float totalPurchasedAmount) {
        this.totalPurchasedAmount = totalPurchasedAmount;
    }

}
