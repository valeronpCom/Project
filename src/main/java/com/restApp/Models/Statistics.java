package com.restApp.Models;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "statistics_table")
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "total_count")
    private Integer amount;

    @Column(name = "min_value")
    private Integer min;

    @Column(name = "max_value")
    private Integer max;

    @Column(name = "popular_value")
    private Integer mostPopular;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getMostPopular() {
        return mostPopular;
    }

    public void setMostPopular(Integer mostPopular) {
        this.mostPopular = mostPopular;
    }

    public void update(List<Integer> response) {
        for (Integer number : response) {
            if (min == null || number.compareTo(min) < 0){
                min = number;;
            }
            if (max == null || number.compareTo(max) > 0){
                max = number;
            }
            if (mostPopular == null || mostPopular < Collections.frequency(response, number)) {
                mostPopular = number;
            }
            amount = response.size();
        }
    }
    public Statistics() {

    }

}
