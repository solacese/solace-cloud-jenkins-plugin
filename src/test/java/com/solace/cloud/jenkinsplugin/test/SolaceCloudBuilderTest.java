package com.solace.cloud.jenkinsplugin.test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.mockito.Mockito.mock;

import java.io.PrintStream;
import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import org.apache.http.protocol.HTTP;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.WithoutJenkins;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.solace.cloud.SolaceCloudBuilder;

public class SolaceCloudBuilderTest {

    @Rule
    public JenkinsRule jenkins = new JenkinsRule();

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(); // defaults to port 8080

    @Test
    @WithoutJenkins
    public void testSolaceCloudInvocation() throws Exception {

	String targetURI = "/api/v0/services";

	// stub out the Solace Cloud Service
	stubFor(post(urlEqualTo(targetURI)).withHeader("Accept", equalTo(MediaType.APPLICATION_JSON))
		.willReturn(aResponse().withStatus(201).withHeader(HTTP.CONTENT_TYPE, MediaType.APPLICATION_JSON)
			.withBody("{\"result\":\"success\"}")));

	// create an instance of our Jenkins plugin and call the Solace Cloud method
	SolaceCloudBuilder scb = new SolaceCloudBuilder("fake-api-token", "Azure", "us-east-2", "free", "free",
		"test-service");

	//mock a logger for use by our method
	PrintStream mockedLogger = mock(PrintStream.class);
	scb.invokeSolaceCloud(mockedLogger);

	HashMap<String, String> expectedRequest = new HashMap<String, String>();

	// populate the hashmap with necessary values
	expectedRequest.put("name", "test-service");
	expectedRequest.put("dataCenterId", "us-east-2");
	expectedRequest.put("partitionId", "default");
	expectedRequest.put("serviceClassId", "free");
	expectedRequest.put("serviceTypeId", "free");
	expectedRequest.put("adminState", "start");

	//turn our request into a JSON string
	ObjectMapper mapper= new ObjectMapper();
	String expectedRequestAsString = mapper.writeValueAsString(expectedRequest);

	//verify the test result
	verify(postRequestedFor(urlEqualTo(targetURI)).withHeader("Content-Type", equalTo(MediaType.APPLICATION_JSON))
		.withHeader("Authorization", equalTo("fake-api-token")).withRequestBody(equalTo(expectedRequestAsString)));
    }

}
