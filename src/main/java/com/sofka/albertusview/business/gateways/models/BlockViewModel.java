package com.sofka.albertusview.business.gateways.models;

public class BlockViewModel {

    private String applicationID;
    private String data;

    public BlockViewModel(String applicationID, String data) {
        this.applicationID = applicationID;
        this.data = data;
    }


    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
