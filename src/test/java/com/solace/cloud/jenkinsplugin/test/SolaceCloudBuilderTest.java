package com.solace.cloud.jenkinsplugin.test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;
import org.apache.http.protocol.HTTP;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.WithoutJenkins;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.solace.cloud.dto.Data;
import com.solace.cloud.dto.SolaceCloudRequest;
import com.solace.cloud.dto.SolaceCloudResponse;
import com.solace.cloud.helpers.InvocationHelper;

public class SolaceCloudBuilderTest {

    @Rule
    public JenkinsRule jenkins = new JenkinsRule();

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(9090); // defaults to port 8080

    private final String BASE_HOST = "http://localhost:9090";
    private final String BASE_TARGET_URI = "/api/v0/services";

    // Simple Unit test to ensure request JSON is accurate
    @Test
    @WithoutJenkins
    public void testRequestJSON() throws Exception {

	SolaceCloudRequest request = new SolaceCloudRequest();

	request.setName("name");
	request.setDatacenterId("aws-us-east-1b");
	request.setServiceClassId("enterprise");
	request.setServiceTypeId("enterprise-giga");

	ObjectMapper mapper = new ObjectMapper();
	String testRequestAsString = mapper.writeValueAsString(request);

	File f = new File("src/test/resources/testCreateRequest.json");
	String expectedJSONRequest = FileUtils.readFileToString(f, "UTF-8");

	assertTrue("Solace Cloud request check", testRequestAsString.equals(expectedJSONRequest));
    }

    // Simple unit test to ensure Service Create (POST) response JSON is accurately
    // handled
    @Test
    @WithoutJenkins
    public void testCreateResponseJSON() throws Exception {

	File f = new File("src/test/resources/testCreateResponse.json");
	String expectedJSONResponse = FileUtils.readFileToString(f, "UTF-8");

	SolaceCloudResponse actualResponse = null;

	ObjectMapper mapper = new ObjectMapper();
	actualResponse = mapper.readValue(expectedJSONResponse, SolaceCloudResponse.class);

	assertNotNull(actualResponse);
    }

    // Simple unit test to ensure Service Query (GET) response JSON is accurately
    // handled
    @Test
    @WithoutJenkins
    public void testGETResponseJSON() throws Exception {

	File f = new File("src/test/resources/testGETResponse.json");
	String expectedJSONResponse = FileUtils.readFileToString(f, "UTF-8");

	SolaceCloudResponse actualResponse = null;

	ObjectMapper mapper = new ObjectMapper();
	actualResponse = mapper.readValue(expectedJSONResponse, SolaceCloudResponse.class);

	assertNotNull(actualResponse);
    }

    @Test
    @WithoutJenkins
    public void TestSolaceCloudCreateRequest() throws Exception {

	Data responseData = new Data();
	responseData.setServiceId("123456");

	SolaceCloudResponse response = new SolaceCloudResponse();
	response.setData(responseData);

	ObjectMapper om = new ObjectMapper();
	String jsonResponseString = om.writeValueAsString(response);

	// stub out the Solace Cloud Service
	wireMockRule.stubFor(post(urlEqualTo("/api/v0/services")).willReturn(
		aResponse().withStatus(201)
			   .withHeader(HTTP.CONTENT_TYPE, MediaType.APPLICATION_JSON)
			   .withBody(jsonResponseString)));

	SolaceCloudRequest request = new SolaceCloudRequest();
	request.setName("test-service");
	request.setDatacenterId("aws-ap-northeast-1a");
	request.setServiceClassId("developer");
	request.setServiceTypeId("developer");

	InvocationHelper helper = new InvocationHelper();
	helper.createSolaceCloudService("http://localhost:9090/api/v0/services", "fake-api-token", request);

	// verify the test result
	verify(postRequestedFor(urlEqualTo("/api/v0/services"))
							       .withHeader(HTTP.CONTENT_TYPE,
								       equalTo(MediaType.APPLICATION_JSON))
							       .withHeader("Authorization", equalTo("fake-api-token")));
    }

    @Test
    @WithoutJenkins
    public void TestSolaceCloudPollRequest() throws Exception {

	Data responseData = new Data();
	responseData.setAdminProgress("completed");

	SolaceCloudResponse response = new SolaceCloudResponse();
	response.setData(responseData);

	ObjectMapper om = new ObjectMapper();
	String jsonResponseString = om.writeValueAsString(response);

	// stub out the Solace Cloud Service
	wireMockRule.stubFor(
		get(urlEqualTo("/api/v0/services/123456")).withHeader("Accept", equalTo(MediaType.APPLICATION_JSON))
							  .willReturn(aResponse().withStatus(200)
										 .withHeader(HTTP.CONTENT_TYPE,
											 MediaType.APPLICATION_JSON)
										 .withBody(jsonResponseString)));

	InvocationHelper helper = new InvocationHelper();
	SolaceCloudResponse cloudResponse = helper.checkSolaceCloudServiceStartup(
		"http://localhost:9090/api/v0/services/123456", "fake-api-token");

	// assert that the method returns something
	assertNotNull(cloudResponse);

	// verify the test result
	verify(getRequestedFor(urlEqualTo("/api/v0/services/123456")).withHeader("Authorization",
		equalTo("fake-api-token")));
    }

}
