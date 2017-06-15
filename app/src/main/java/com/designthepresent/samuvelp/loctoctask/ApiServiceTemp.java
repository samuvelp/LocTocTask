package com.designthepresent.samuvelp.loctoctask;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by samuv on 6/15/2017.
 */

public interface ApiServiceTemp {
        @FormUrlEncoded
        @POST("/getKey")
        Call<PostTemp> savePost(@Field("tempKey") String tempKey);
}
