package com.bagelbytes.libertyhacksmerge;

/**
 * Created by jaypatel1885 on 8/18/17.
 */

public class Register {

    Integer id;
    String fullName;
    String name;
    String password;
    Integer zip;

    public Register(){

    }

    public Integer getid() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public Register(Integer id, String fullName, String name, String password, Integer zip)
    {
        this.id = id;
        this.fullName = fullName;
        this.name = name;
        this.password = password;
        this.zip = zip;
    }
}
