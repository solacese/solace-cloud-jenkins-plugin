package com.solace.cloud.dto;

public class CreateRequest {

    private String cloudProvider;
    private String cloudRegion;
    private String serviceTypeId;
    private String serviceClassId;
    private String serviceName;
    
    private final String adminState = "start";

    public CreateRequest(String cloudProvider, String cloudRegion, String serviceTypeId,
	    String serviceClassId, String serviceName) {

	this.cloudProvider = cloudProvider;
	this.cloudRegion = cloudRegion;
	this.serviceTypeId = serviceTypeId;
	this.serviceClassId = serviceClassId;
	this.serviceName = serviceName;
    }


    public String getCloudProvider() {
        return cloudProvider;
    }

    public void setCloudProvider(String cloudProvider) {
        this.cloudProvider = cloudProvider;
    }

    public String getCloudRegion() {
        return cloudRegion;
    }

    public void setCloudRegion(String cloudRegion) {
        this.cloudRegion = cloudRegion;
    }

    public String getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getServiceClassId() {
        return serviceClassId;
    }

    public void setServiceClassId(String serviceClassId) {
        this.serviceClassId = serviceClassId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public String getAdminState() {
	return adminState;
    }
}
