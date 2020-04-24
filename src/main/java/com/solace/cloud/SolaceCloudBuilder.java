package com.solace.cloud;

import java.io.IOException;

import javax.servlet.ServletException;

import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import com.solace.cloud.constants.SolaceCloudConstants;
import com.solace.cloud.dto.SolaceCloudRequest;
import com.solace.cloud.helpers.InvocationHelper;

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

    private String SOLACE_CLOUD_HOST = "https://api.solace.cloud";
    private final Long SOLACE_CLOUD_POLL_MS = 5000L;

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

	InvocationHelper cloudHelper = new InvocationHelper(SOLACE_CLOUD_HOST, apiToken);

	listener.getLogger().println("Calling Solace Cloud to create the service...");

	// make the actual Solace Cloud call to create a service
	String serviceId = cloudHelper.createSolaceCloudService(solaceCloudRequest);

	listener.getLogger().printf("Success! ServiceId is %s\n", serviceId);

	// temp variables to poll the service for create-completion
	Boolean done = false;
	String serviceStatus;

	listener.getLogger().print("Checking to see if service is fully up...\r");

	while (!done) {
	    serviceStatus = cloudHelper.checkSolaceCloudServiceStartup(serviceId);

	    switch (serviceStatus) {
	    case SolaceCloudConstants.ADMIN_PROGRESS_COMPLETED:
		done = true;
		break;

	    case SolaceCloudConstants.ADMIN_PROGRESS_FAILED:
		throw new AbortException("Service startup failed! ServiceId:" + serviceId);

	    default:
		listener.getLogger().println("Not up yet. Waiting to retry...");

		// block this thread and wait to check Solace Cloud again at some point
		Thread.sleep(SOLACE_CLOUD_POLL_MS);
		break;
	    }
	}

	listener.getLogger().println("All done! Your service is good to go.");
    }

    @Symbol("solacecloud")
    @Extension
// TODO: Externalize strings
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

	public ListBoxModel doFillDatacenterIdItems(@QueryParameter String cloudProvider) {
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
