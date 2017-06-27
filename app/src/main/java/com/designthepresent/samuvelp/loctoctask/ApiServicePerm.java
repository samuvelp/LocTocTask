package com.designthepresent.samuvelp.loctoctask;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by samuv on 6/27/2017.
 */

public interface ApiServicePerm {
    @POST("/getCoordinates")
    Call<PostPerm> postPermKey(@Body PostPerm postPerm);
}
