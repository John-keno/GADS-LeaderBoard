package com.github.johnkeno.gadsleadersboard.network.main;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIGetService {
    @GET("api/hours")
    Call<List<HourResponse>> getHourResponse();

    @GET("api/skilliq")
    Call<List<IqResponse>> getIqResponse();

}
