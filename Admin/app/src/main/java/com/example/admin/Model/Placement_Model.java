package com.example.admin.Model;

public class Placement_Model {

    String placementyear;
    String placementpdfurl;
    String id;

    public Placement_Model() {

    }

    public Placement_Model(String placementyear, String placementpdfurl, String id) {
        this.placementyear = placementyear;
        this.placementpdfurl = placementpdfurl;
        this.id = id;
    }

    public String getPlacementyear() {
        return placementyear;
    }

    public void setPlacementyear(String placementyear) {
        this.placementyear = placementyear;
    }

    public String getPlacementpdfurl() {
        return placementpdfurl;
    }

    public void setPlacementpdfurl(String placementpdfurl) {
        this.placementpdfurl = placementpdfurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
