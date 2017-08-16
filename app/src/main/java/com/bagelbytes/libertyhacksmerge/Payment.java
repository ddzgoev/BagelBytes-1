package com.bagelbytes.libertyhacksmerge;

/**
 * Created by tkaram on 8/16/2017.
 */

public class Payment {

    //this is model class

    public String name;
    public String service;
    public String date;
    public Double pay;

    public String getName() {
        return name;
    }

    public String getService() {
        return service;
    }

    public String getDate() {
        return date;
    }

    public Double getPay() {
        return pay;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public Payment(String name, String service, String date, Double pay) {
        this.name = name;
        this.service = service;
        this.date = date;
        this.pay = pay;
    }
}