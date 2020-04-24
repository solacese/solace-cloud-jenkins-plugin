package com.solace.cloud.helpers;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.solace.cloud.dto.SolaceCloudRequest;
import com.solace.cloud.dto.SolaceCloudResponse;

import hudson.AbortException;

public class InvocationHelper {

    private Client client;

    public InvocationHelper(String baseURI) {
	client = ClientBuilder.newClient();
    }

    public String createSolaceCloudService(String uri, String apiToken, SolaceCloudRequest createServiceRequest)
	    throws AbortException {

	try {
	    Response cloudResponse = client.target(uri)
					   .request(MediaType.APPLICATION_JSON)
					   .header(HttpHeaders.AUTHORIZATION, apiToken)
					   .post(Entity.entity(createServiceRequest, MediaType.APPLICATION_JSON));

	    // Check if the http status code is 201 ("Created") otherwise we have an error
	    if (cloudResponse.getStatus() != 201) {
		throw new AbortException(String.format(
			"Unexpected HTTP status code of %s with message '%s'. The expected HTTP status code is 201.",
			cloudResponse.getStatus(), cloudResponse.getStatusInfo()));
	    }

	    // we only really need the serviceId
	    String serviceId = cloudResponse.readEntity(SolaceCloudResponse.class).getData().getServiceId();

	    if (serviceId.equals(null) || serviceId.equals(""))
		throw new AbortException("Solace Cloud did not return a valid serviceId!");

	    return serviceId;
	} catch (ResponseProcessingException ex) {
	    throw new AbortException(
		    "Could't process the Service Create response from the Solace Cloud API - " + ex.getMessage());
	} catch (ProcessingException ex) {
	    throw new AbortException(
		    "General processing problem trying to call the Solace Cloud API to create a service - "
			    + ex.toString());
	} catch (IllegalArgumentException ex) {
	    throw new AbortException("Malformed Solace Cloud URI provided when creating the service- " + uri);
	} catch (NullPointerException ex) {
	    throw new AbortException("The Solace Cloud URI cannot be 'null'");
	} finally {
	    
	}
    }

    public SolaceCloudResponse checkSolaceCloudServiceStartup(String uri, String apiToken) throws AbortException {

	try {
	    Response cloudResponse = client.target(uri)
					   .request(MediaType.APPLICATION_JSON)
					   .header(HttpHeaders.AUTHORIZATION, apiToken)
					   .get();

	    // Check if the http status code is 200 ("OK") otherwise we have an error
	    if (cloudResponse.getStatus() != 200) {
		throw new AbortException(String.format(
			"Unexpected HTTP status code of %s with message '%s'. The expected HTTP status code is 200.",
			cloudResponse.getStatus(), cloudResponse.getStatusInfo()));
	    }

	    return cloudResponse.readEntity(SolaceCloudResponse.class);

	} catch (ResponseProcessingException ex) {
	    throw new AbortException(
		    "Could't process the Service Status response from the Solace Cloud API - " + ex.toString());
	} catch (ProcessingException ex) {
	    throw new AbortException(
		    "General processing problem trying to call the Solace Cloud API to poll the service - "
			    + ex.toString());
	} catch (IllegalArgumentException ex) {
	    throw new AbortException("Malformed Solace Cloud URI provided when polling the service- " + uri);
	} catch (NullPointerException ex) {
	    throw new AbortException("The Solace Cloud URI cannot be 'null'");
	} finally {
	    client.close();
	}
    }
}
