package com.github.johnkeno.gadsleadersboard.network.main;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIPostService {

    @FormUrlEncoded
    @POST("1FAIpQLSdjIIDDnQlRcDMjkNu-Pr8o9tWMaeVoP95YMnSpVPrACagucQ/formResponse")
    Call<PostData> submitData(
            @Field("entry.27885089") String firstName,
            @Field("entry.542538513") String LastName,
            @Field("entry.17301063") String email,
            @Field("entry.924667838") String GitHubLink
    );
}
