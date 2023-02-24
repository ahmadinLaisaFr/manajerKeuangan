package com.frenzydevel.manajerkeuangan.model;

public class Uang {

    private String id, profile_id, income, outcome;

    public Uang(){

    }

    public Uang(String id, String profile_id, String income, String outcome) {
        this.id = id;
        this.profile_id = profile_id;
        this.income = income;
        this.outcome = outcome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(String profile_id) {
        this.profile_id = profile_id;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }
}
