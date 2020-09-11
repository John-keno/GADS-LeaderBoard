package com.github.johnkeno.gadsleadersboard.network.main;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIGetClient {

    private static final String BASE_URL = "https://gadsapi.herokuapp.com/";
    private static Retrofit sGetRetrofit = null;

    public static Retrofit getGetRetrofit(){
        if (sGetRetrofit == null) {
            sGetRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sGetRetrofit;
    }
    public static APIGetService sAPIGetService(){
        return getGetRetrofit().create(APIGetService.class);
    }
}
