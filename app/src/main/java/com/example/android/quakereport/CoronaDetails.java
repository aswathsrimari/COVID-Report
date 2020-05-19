package com.example.android.quakereport;

public class CoronaDetails {

    private String country;

    private  long cases;

    public CoronaDetails(String country,long cases){
        this.country = country;
        this.cases = cases;
    }

    public String getCountry() {
        return country;
    }
    public long getCases(){
        return cases;
    }
}
