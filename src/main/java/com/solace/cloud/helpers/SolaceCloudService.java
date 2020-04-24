package com.solace.cloud.helpers;

import com.solace.cloud.dto.SolaceCloudRequest;
import com.solace.cloud.dto.SolaceCloudResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SolaceCloudService {

    @Headers("Content-Type:application/json")
    @POST("api/v0/services")
    Call<SolaceCloudResponse> createService(@Header("Authorization") String apiToken, @Body SolaceCloudRequest request);
    
    @GET("api/v0/services/{serviceId}")
    Call<SolaceCloudResponse> getServiceInfo(@Header("Authorization") String apiToken, @Path("serviceId") String serviceId);
}
