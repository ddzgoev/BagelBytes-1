package com.bagelbytes.libertyhacksmerge;

/**
 * Created by David on 8/17/2017.
 */

class PaymentMethod {
    public Integer id;
    public String type;
    public String paypalEmail;
    public String paypalPassword;
    public String bankAccountName;
    public String bankRoutingNumber;
    public String bankAccountNumber;
    public String creditcardNumber;
    public String creditcardExpirationDate;
    public String creditcardSecurityCode;

    public PaymentMethod(){

    }

    public PaymentMethod(String type, String paypalEmail, String paypalPassword,
                         String bankAccountName, String bankRoutingNumber, String bankAccountNumber,
                         String creditcardNumber, String creditcardExpirationDate,
                         String creditcardSecurityCode)
    {

        this.type = type;
        this.paypalEmail = paypalEmail;
        this.paypalPassword = paypalPassword;
        this.bankAccountName = bankAccountName;
        this.bankRoutingNumber = bankRoutingNumber;
        this.bankAccountNumber = bankAccountNumber;
        this.creditcardNumber = creditcardNumber;
        this.creditcardExpirationDate = creditcardExpirationDate;
        this.creditcardSecurityCode = creditcardSecurityCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPaypalEmail() {
        return paypalEmail;
    }

    public void setPaypalEmail(String paypalEmail) {
        this.paypalEmail = paypalEmail;
    }

    public String getPaypalPassword() {
        return paypalPassword;
    }

    public void setPaypalPassword(String paypalPassword) {
        this.paypalPassword = paypalPassword;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankRoutingNumber() {
        return bankRoutingNumber;
    }

    public void setBankRoutingNumber(String bankRoutingNumber) {
        this.bankRoutingNumber = bankRoutingNumber;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getCreditcardNumber() {
        return creditcardNumber;
    }

    public void setCreditcardNumber(String creditcardNumber) {
        this.creditcardNumber = creditcardNumber;
    }

    public String getCreditcardExpirationDate() {
        return creditcardExpirationDate;
    }

    public void setCreditcardExpirationDate(String creditcardExpirationDate) {
        this.creditcardExpirationDate = creditcardExpirationDate;
    }

    public String getCreditcardSecurityCode() {
        return creditcardSecurityCode;
    }

    public void setCreditcardSecurityCode(String creditcardSecurityCode) {
        this.creditcardSecurityCode = creditcardSecurityCode;
    }
}