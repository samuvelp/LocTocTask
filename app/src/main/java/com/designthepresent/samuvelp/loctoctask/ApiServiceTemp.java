package com.designthepresent.samuvelp.loctoctask;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by samuv on 6/15/2017.
 */

public interface ApiServiceTemp {

        @POST("/getKey")
        Call<PostTemp> postTempKey(@Body PostTemp postTemp);
}
