package com.designthepresent.samuvelp.loctoctask;

/**
 * Created by samuv on 6/27/2017.
 */

public class PostPerm {
    private String permKey;
    private String latitude;
    private String longitude;
    public PostPerm(String permKey){
        this.permKey=permKey;
    }
    public String getLatitude(){
        return latitude;
    }
    public String getLongitude(){
        return longitude;
    }
}
