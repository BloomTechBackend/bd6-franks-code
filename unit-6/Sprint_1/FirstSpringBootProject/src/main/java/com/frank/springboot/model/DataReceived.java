package com.frank.springboot.model;

public class DataReceived {
    String dataSent;  // The name of the JSON attribute must match data member name

    public String getDataSent() {
        return dataSent;
    }
    public void setDataSent(String dataSent) {
        this.dataSent = dataSent;
    }
}
