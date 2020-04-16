package com.solace.cloud.jenkinsplugin.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;

import javax.ws.rs.client.SyncInvoker;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.WithoutJenkins;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.solace.cloud.SolaceCloudBuilder;
import com.solace.cloud.dto.Data;
import com.solace.cloud.dto.SolaceCloudRequest;
import com.solace.cloud.dto.SolaceCloudResponse;

import hudson.model.TaskListener;

public class SolaceCloudBuilderTest {

    @Rule
    public JenkinsRule jenkins = new JenkinsRule();

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(); // defaults to port 8080

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
    public void TestSolaceCloudCreateRequest() throws Exception {
	SolaceCloudBuilder scb = new SolaceCloudBuilder("fake-api-token", "aws-us-east-1b", "enterprise-giga",
		"my-test-service");

	TaskListener mockTaskListener = mock(TaskListener.class);
	when(mockTaskListener.getLogger()).thenReturn(System.out);
	
	scb.perform(null, null, null, mockTaskListener);

	SyncInvoker mockInvoker = mock(SyncInvoker.class);

	Data fakeResponseBodyData = new Data();
	fakeResponseBodyData.setServiceId("123456789");
	fakeResponseBodyData.setAdminProgress("completed");
	SolaceCloudResponse fakeResponseBody = new SolaceCloudResponse();
	fakeResponseBody.setData(fakeResponseBodyData);

	//return a successful "create" response
	Response fakeResponse = Response.status(201).entity(fakeResponseBody).build();
	when(mockInvoker.post(any())).thenReturn(fakeResponse);
	
	//automatically return a 200 with "completed" status so our test doesn't block
	fakeResponse = Response.status(200).entity(fakeResponseBody).build();
	when(mockInvoker.get()).thenReturn(fakeResponse);
    }

}
