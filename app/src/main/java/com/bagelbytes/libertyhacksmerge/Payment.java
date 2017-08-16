package com.bagelbytes.libertyhacksmerge;

/**
 * Created by tkaram on 8/16/2017.
 */

public class Payment {

    //this is model class

    public Integer id;
    public String name;
    public String service;
    public String date;
    public Double pay;
    public Integer auto;

    public Payment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public Integer getAuto() {
        return auto;
    }

    public void setAuto(Integer auto) {
        this.auto = auto;
    }

    public Payment(Integer id, String name, String service, String date, Double pay, Integer auto) {
        this.id = id;
        this.name = name;
        this.service = service;
        this.date = date;
        this.pay = pay;
        this.auto = auto;
    }
}