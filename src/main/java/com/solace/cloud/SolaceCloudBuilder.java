package com.solace.cloud;

import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import com.solace.cloud.constants.SolaceCloudConstants;
import com.solace.cloud.dto.SolaceCloudRequest;

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
import hudson.util.ListBoxModel;
import jenkins.tasks.SimpleBuildStep;

public class SolaceCloudBuilder extends Builder implements SimpleBuildStep {

    private final String SOLACE_CLOUD_ENDPOINT = "http://localhost:8080/api/v0/services";

    private String apiToken;
    private SolaceCloudRequest solaceCloudRequest;

    @DataBoundConstructor
    public SolaceCloudBuilder(String apiToken, String datacenterId, String serviceTypeId, String serviceName) {

	this.apiToken = apiToken;

	solaceCloudRequest = new SolaceCloudRequest();
	solaceCloudRequest.setName(serviceName);
	solaceCloudRequest.setDatacenterId(datacenterId);
	solaceCloudRequest.setServiceClassId(serviceTypeId.split("-")[0]);
	solaceCloudRequest.setServiceTypeId(serviceTypeId);
    }

    @Override
    public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener)
	    throws InterruptedException, IOException {

	// make the actual Solace Cloud call
	invokeSolaceCloud(listener.getLogger());
    }

    public void invokeSolaceCloud(PrintStream logger) throws AbortException {

	// create a new client for our RESTful endpoint
	Client client = ClientBuilder.newClient();

	try {
	    Response cloudResponse = client.target(SOLACE_CLOUD_ENDPOINT)
					   .request(MediaType.APPLICATION_JSON)
					   .header(HttpHeaders.AUTHORIZATION, apiToken)
					   .post(Entity.entity(solaceCloudRequest, MediaType.APPLICATION_JSON));

	    // Check if the http status code is 201 ("Created") otherwise we have an error
	    if (cloudResponse.getStatus() != 201) {
		throw new AbortException(String.format(
			"Unexpected HTTP status code of %s with message '%s'. The expected HTTP status code is 201.",
			cloudResponse.getStatus(), cloudResponse.getStatusInfo()));
	    }

	} catch (ResponseProcessingException ex) {
	    throw new AbortException("Could't process the response from Solace Cloud - " + ex.toString());
	} catch (ProcessingException ex) {
	    throw new AbortException(
		    "General processing problem trying to call the Solace Cloud API - " + ex.toString());
	} catch (IllegalArgumentException ex) {
	    throw new AbortException("Malformed Solace Cloud URI - " + SOLACE_CLOUD_ENDPOINT);
	} catch (NullPointerException ex) {
	    throw new AbortException("The Solace Cloud URI cannot be 'null'");
	} finally {
	    client.close();
	}
    }

    @Symbol("solacecloud")
    @Extension
    // TODO: Externalize strings
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

	public ListBoxModel doFillCloudRegionItems(@QueryParameter String cloudProvider) {
	    ListBoxModel regionList = new ListBoxModel();
	    String[] currentRegions = { "Please Select" };

	    switch (cloudProvider) {
	    case "aws":
		currentRegions = SolaceCloudConstants.AWS_REGIONS;
		break;
	    case "azure":
		currentRegions = SolaceCloudConstants.AZURE_REGIONS;
		break;
	    case "gcp":
		currentRegions = SolaceCloudConstants.GCP_REGIONS;
		break;
	    default:
		break;
	    }

	    for (String region : currentRegions)
		regionList.add(region);

	    return regionList;
	}

	public ListBoxModel doFillServiceTypeIdItems(@QueryParameter String datacenterId) {
	    ListBoxModel serviceTypeIdList = new ListBoxModel();
	    String[] serviceTypes = { "Please Select" };

	    // Without this condition, an NPE is throw since this field depends
	    // on another calculated field, cloudRegion, that may not be set when this
	    // method is invoked.
	    if (!datacenterId.equals(""))
		serviceTypes = SolaceCloudConstants.SERVICE_TYPE_ID_BY_REGION.get(datacenterId);

	    for (String serviceType : serviceTypes)
		serviceTypeIdList.add(serviceType);

	    return serviceTypeIdList;
	}

	public FormValidation doCheckApiToken(@QueryParameter String value, @QueryParameter String serviceName)
		throws IOException, ServletException {

	    if (value.trim().length() == 0)
		return FormValidation.error("Please provide an API token");

	    return FormValidation.ok();
	}

	public FormValidation doCheckServiceName(@QueryParameter String value) {

	    if (value.trim().length() < 5)
		return FormValidation.error("Name of service should be at least 5 characters long");

	    return FormValidation.ok();
	}

	@Override
	public boolean isApplicable(Class<? extends AbstractProject> aClass) {
	    return true;
	}

	@Override
	public String getDisplayName() {
	    return "Create a Solace PubSub+ Cloud Instance";
	}

    }
}
