package com.solace.cloud;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import hudson.AbortException;
import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.util.FormValidation;
import jenkins.tasks.SimpleBuildStep;

public class SolaceCloudBuilder extends Builder implements SimpleBuildStep {

    private final String SOLACE_CLOUD_ENDPOINT="http://localhost:8080/api/v0/services";
    
    private String apiToken;
    private String cloudProvider;
    private String cloudRegion;
    private String serviceTypeId;
    private String serviceClassId;
    private String serviceName;

    @DataBoundConstructor
    public SolaceCloudBuilder(String apiToken, String cloudProvider, String cloudRegion, String serviceTypeId,
	    String serviceClassId, String serviceName) {
	this.apiToken = apiToken;
	this.cloudProvider = cloudProvider;
	this.cloudRegion = cloudRegion;
	this.serviceTypeId = serviceTypeId;
	this.serviceClassId = serviceClassId;
	this.serviceName = serviceName;
    }

    
    @Override
    public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener)
	    throws InterruptedException, IOException {

	// make the actual Solace Cloud call
	invokeSolaceCloud(listener.getLogger());
    }

    public void invokeSolaceCloud(PrintStream logger) throws AbortException {

	
	//create a hashmap to be converted by Jackson into JSON
	HashMap<String,String> solaceCloudCreateRequest= new HashMap<String,String>();
	
	//populate the hashmap with necessary values
	solaceCloudCreateRequest.put("name", serviceName);
	solaceCloudCreateRequest.put("dataCenterId", cloudRegion);
	solaceCloudCreateRequest.put("partitionId", "default");
	solaceCloudCreateRequest.put("serviceClassId", serviceClassId);
	solaceCloudCreateRequest.put("serviceTypeId", serviceTypeId);
	solaceCloudCreateRequest.put("adminState", "start");
	
	Client client = ClientBuilder.newClient();

	try {
	    Response response = client
		    .target(SOLACE_CLOUD_ENDPOINT)
		    .request(MediaType.APPLICATION_JSON)
		    .header("Authorization", apiToken)
		    .post(Entity.entity(solaceCloudCreateRequest, MediaType.APPLICATION_JSON));

	    logger.println(response.toString());
	} catch (Exception ex) {
	    throw new AbortException(ex.getMessage());
	}
    }

    @Symbol("solacecloud")
    @Extension
    // TODO: Externalize strings
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

	public FormValidation doCheckApiToken(@QueryParameter String value, @QueryParameter String serviceName)
		throws IOException, ServletException {

	    if (value.trim().length() == 0)
		return FormValidation.error("Please provide an API token");

	    return FormValidation.ok();
	}

	public FormValidation doCheckServiceName(@QueryParameter String value) {

	    if (value.trim().length() < 5)
		return FormValidation
			.error("The name of your Solace Cloud service should be at least 5 characters long.");

	    return FormValidation.ok();
	}

	@Override
	public boolean isApplicable(Class<? extends AbstractProject> aClass) {
	    return true;
	}

	@Override
	public String getDisplayName() {
	    return "Create a Solace Cloud Instance";
	}

    }
}
