package com.solace.cloud.jenkinsplugin.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileReader;

import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.WithoutJenkins;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.solace.cloud.dto.SolaceCloudRequest;
import com.solace.cloud.dto.SolaceCloudResponse;

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

	String expectedJSONRequest = "{\"name\":\"name\",\"datacenterId\":\"aws-us-east-1b\",\"partitionId\":\"default\",\"serviceClassId\":\"enterprise\",\"serviceTypeId\":\"enterprise-giga\",\"adminState\":\"start\"}";

	assertTrue("Solace Cloud request check", testRequestAsString.equals(expectedJSONRequest));
    }

    
    //Simple unit test to ensure response JSON is accurately handled
    @Test
    @WithoutJenkins
    public void testResponseJSON() throws Exception {

	FileReader f = new FileReader("src/test/resources/testResponse.json");
	String expectedJSONResponse = "";

	int i = 0;

	while ((i = f.read()) > -1)
	    expectedJSONResponse += (char) i;

	SolaceCloudResponse actualResponse = null;

	ObjectMapper mapper = new ObjectMapper();
	actualResponse = mapper.readValue(expectedJSONResponse, SolaceCloudResponse.class);

	assertNotNull(actualResponse);
    }
}
