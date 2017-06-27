package com.designthepresent.samuvelp.loctoctask;

public class ApiUtilsPerm{

    private ApiUtilsPerm() {}

    public static final String BASE_URL = "http://139.59.22.50/";

    public static ApiServicePerm getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(ApiServicePerm.class);

    }
}
