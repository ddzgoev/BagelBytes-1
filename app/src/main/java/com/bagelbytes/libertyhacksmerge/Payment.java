package com.bagelbytes.libertyhacksmerge;

import java.io.Serializable;

/**
 * Created by tkaram on 8/16/2017.
 */

public class Payment implements Serializable {

    //this is model class
    private String accountNumber;
    private String accountHolder;
    private Integer zip;
    private Integer paymentMethod;
    private Integer id;
    private String name;
    private  String service; //provider
    private  String date;
    private  Double pay;
    private Integer auto;


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


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    public Payment(Integer id, String name, String service, String date, Double pay, Integer auto) {
        this.id = id;
        this.name = name;
        this.service = service;
        this.date = date;
        this.pay = pay;
        this.auto = auto;
    }

    public Payment(Integer id, String name, String service, String date, Double pay, Integer auto, String accountNumber, String accountHolder, Integer zip, Integer paymentMethod) {
        this.id = id;
        this.name = name;
        this.service = service;
        this.date = date;
        this.pay = pay;
        this.auto = auto;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.zip = zip;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountHolder='" + accountHolder + '\'' +
                ", zip=" + zip +
                ", paymentMethod=" + paymentMethod +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", service='" + service + '\'' +
                ", date='" + date + '\'' +
                ", pay=" + pay +
                ", auto=" + auto +
                '}';
    }
}