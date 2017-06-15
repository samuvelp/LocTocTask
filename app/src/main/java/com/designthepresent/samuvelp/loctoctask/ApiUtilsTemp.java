package com.designthepresent.samuvelp.loctoctask;

public class ApiUtilsTemp {

    private ApiUtilsTemp() {}

    public static final String BASE_URL = "http://139.59.22.50/";

    public static ApiServiceTemp getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(ApiServiceTemp.class);

    }
}
