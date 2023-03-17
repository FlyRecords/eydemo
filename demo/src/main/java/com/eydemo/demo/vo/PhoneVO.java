package com.eydemo.demo.vo;

public class PhoneVO {
    private Integer id;
    private String number;
    private String cytycode;
    private String countrycode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCytycode() {
        return cytycode;
    }

    public void setCytycode(String cytycode) {
        this.cytycode = cytycode;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public PhoneVO(Integer id, String number, String cytycode, String countrycode) {
        this.id = id;
        this.number = number;
        this.cytycode = cytycode;
        this.countrycode = countrycode;
    }

    public PhoneVO() {
    }
}
