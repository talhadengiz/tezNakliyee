package com.example.talha.teznakliyee;

import com.example.talha.teznakliyee.models.ServerRequest;
import com.example.talha.teznakliyee.models.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Talha on 22.5.2017.
 */

public interface RequestInterface {
    @POST("http://www.istutransport.com/")
    Call<ServerResponse> operation(@Body ServerRequest request);

}
