package com.github.johnkeno.gadsleadersboard.network.main;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIPostService {

    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<PostData> submitData(
            @Field("entry.1877115667") String firstName,
            @Field("entry.2006916086") String LastName,
            @Field("entry.1824927963") String email,
            @Field("entry.284483984") String GitHubLink
    );
}
