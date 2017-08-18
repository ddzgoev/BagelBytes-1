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
    public int bankRoutingNumber;
    public int bankAccountNumber;
    public int creditcardNumber;
    public String creditcardExpirationDate;
    public int creditcardSecurityCode;

    public PaymentMethod(Integer id, String type, String paypalEmail, String paypalPassword,
                         String bankAccountName, int bankRoutingNumber, int bankAccountNumber,
                         int creditcardNumber, String creditcardExpirationDate,
                         int creditcardSecurityCode)
    {
        this.id = id;
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

    public int getBankRoutingNumber() {
        return bankRoutingNumber;
    }

    public void setBankRoutingNumber(int bankRoutingNumber) {
        this.bankRoutingNumber = bankRoutingNumber;
    }

    public int getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(int bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public int getCreditcardNumber() {
        return creditcardNumber;
    }

    public void setCreditcardNumber(int creditcardNumber) {
        this.creditcardNumber = creditcardNumber;
    }

    public String getCreditcardExpirationDate() {
        return creditcardExpirationDate;
    }

    public void setCreditcardExpirationDate(String creditcardExpirationDate) {
        this.creditcardExpirationDate = creditcardExpirationDate;
    }

    public int getCreditcardSecurityCode() {
        return creditcardSecurityCode;
    }

    public void setCreditcardSecurityCode(int creditcardSecurityCode) {
        this.creditcardSecurityCode = creditcardSecurityCode;
    }
}
