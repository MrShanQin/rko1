package com.example.lizhi.rko.Class;

/**
 * Created by wsdf.s on 2018/3/28.
 */

public class MalfunClass {
    private String facilityName;
    private String facilityAddress;
    private String questionForm;
    private String distributePeople;
    private String phoneNumber;
    private String suggest;

    public MalfunClass(){}
    public MalfunClass(String facilityName, String facilityAddress, String questionForm, String distributePeople, String phoneNumber, String suggest) {
        this.facilityName = facilityName;
        this.facilityAddress = facilityAddress;
        this.questionForm = questionForm;
        this.distributePeople = distributePeople;
        this.phoneNumber = phoneNumber;
        this.suggest = suggest;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFacilityAddress() {
        return facilityAddress;
    }

    public void setFacilityAddress(String facilityAddress) {
        this.facilityAddress = facilityAddress;
    }

    public String getQuestionForm() {
        return questionForm;
    }

    public void setQuestionForm(String questionForm) {
        this.questionForm = questionForm;
    }

    public String getDistributePeople() {
        return distributePeople;
    }

    public void setDistributePeople(String distributePeople) {
        this.distributePeople = distributePeople;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }
}
