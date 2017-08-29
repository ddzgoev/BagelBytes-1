package com.bagelbytes.libertyhacksmerge;

/**
 * Created by tkaram on 8/28/2017.
 */

public class Bill {
    String accountNumber;
    String accountHolder;
    String provider;
    String accountZip;
    String billName;
    Integer paymentMethod;
    Integer user;
    String id = "None";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setAccountZip(String accountZip) {
        this.accountZip = accountZip;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setUser(Integer user) {
        this.user = user;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getProvider() {
        return provider;
    }

    public String getAccountZip() {
        return accountZip;
    }

    public String getBillName() {
        return billName;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }


    public Integer getUser() {
        return user;
    }

    public Bill(String accountNumber, String accountHolder,
                String provider, String accountZip,
                String billName, Integer paymentMethod,
                Integer user){
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.accountZip = accountZip;
        this.billName = billName;
        this.paymentMethod = paymentMethod;
        this.provider = provider;
        this.user = user;

    }

    public Bill(){

    }
}
