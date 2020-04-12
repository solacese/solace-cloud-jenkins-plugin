
package com.solace.cloud.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "datacenterId", "partitionId", "serviceClassId", "serviceTypeId", "adminState" })
public class SolaceCloudRequest {

    private String name;
    private String datacenterId;
    private final String PARTITION_ID = "default";
    private String serviceClassId;
    private String serviceTypeId;
    private final String ADMIN_STATE = "start";

    
    
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDatacenterId() {
	return datacenterId;
    }

    public void setDatacenterId(String datacenterId) {
	this.datacenterId = datacenterId;
    }

    @JsonProperty("partitionId")
    public String getPartitionId() {
	return PARTITION_ID;
    }

    public String getServiceClassId() {
	return serviceClassId;
    }

    public void setServiceClassId(String serviceClassId) {
	this.serviceClassId = serviceClassId;
    }

    public String getServiceTypeId() {
	return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeId) {
	this.serviceTypeId = serviceTypeId;
    }

    @JsonProperty("adminState")
    public String getAdminState() {
	return ADMIN_STATE;
    }

}