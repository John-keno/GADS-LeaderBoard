package com.github.johnkeno.gadsleadersboard.network.main;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIPostClient {
    private static final String POST_BASE_URL = "https://docs.google.com/forms/d/e/";
    private static Retrofit sPostRetrofit = null;

    public static Retrofit getPostRetrofit(){
        if(sPostRetrofit == null){
            sPostRetrofit = new Retrofit.Builder()
                    .baseUrl(POST_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
            return sPostRetrofit;

    }
    public static APIPostService sAPIPostService(){
        return getPostRetrofit().create(APIPostService.class);
    }
}
