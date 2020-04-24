package com.solace.cloud.helpers;

import java.io.IOException;

import com.solace.cloud.dto.SolaceCloudRequest;
import com.solace.cloud.dto.SolaceCloudResponse;

import hudson.AbortException;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class InvocationHelper {

    // holder for the response from Solace Cloud.
    private SolaceCloudResponse cloudResponse = null;
    private SolaceCloudService service = null;

    private String apiToken = null;

    public InvocationHelper(String baseUrl, String apiToken) {

	this.apiToken = apiToken;

	Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
						  .addConverterFactory(JacksonConverterFactory.create())
						  .build();

	service = retrofit.create(SolaceCloudService.class);
    }

    // creates a solace cloud service
    public String createSolaceCloudService(SolaceCloudRequest createServiceRequest) throws AbortException {

	try {

	    // POST our request & retrieve only the service Id

	    String serviceId = service.createService(apiToken, createServiceRequest)
				      .execute()
				      .body()
				      .getData()
				      .getServiceId();

	    return serviceId;
	} catch (IOException ex) {
	    AbortException abort = new AbortException("The Solace Cloud API didn't return a ServiceId!");
	    abort.addSuppressed(ex);
	    throw abort;
	}
    }

    // Retrieves the status of the service identified by "serviceId"
    // Also makes the general response JSON available for use
    public String checkSolaceCloudServiceStartup(String serviceId) throws AbortException {

	try {
	    SolaceCloudResponse response = service.getServiceInfo(apiToken, serviceId).execute().body();

	    setSolaceCloudResponse(response);

	    String serviceAdminProgress = response.getData().getAdminProgress();

	    //if we hit the service too early during startup, the adminProgress is null, guard against a failure if that happens
	    if (serviceAdminProgress != null) {
		return serviceAdminProgress.toLowerCase();
	    } else {
		return "inprogress";
	    }
	    
	} catch (IOException ex) {
	    AbortException abort = new AbortException("Error polling serviceId:" + serviceId);
	    abort.addSuppressed(ex);
	    throw abort;
	}
    }

    private void setSolaceCloudResponse(SolaceCloudResponse cloudResponse) {
	this.cloudResponse = cloudResponse;
    }

    private SolaceCloudResponse getSolaceCloudResponse() {
	return cloudResponse;
    }
}
