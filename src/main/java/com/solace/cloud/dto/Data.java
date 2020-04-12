
package com.solace.cloud.dto;

import java.util.List;

public class Data {

    private String type;
    private String serviceId;
    private String name;
    private String datacenterId;
    private String partitionId;
    private String serviceTypeId;
    private String serviceClassId;
    private String adminState;
    private String created;
    private String creationState;
    private MsgVpnAttributes msgVpnAttributes;
    private PlanAttributes planAttributes;
    private PlanDisplayAttributes planDisplayAttributes;
    private List<AccountingLimit> accountingLimits = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

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

    public String getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(String partitionId) {
        this.partitionId = partitionId;
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

    public String getAdminState() {
        return adminState;
    }

    public void setAdminState(String adminState) {
        this.adminState = adminState;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreationState() {
        return creationState;
    }

    public void setCreationState(String creationState) {
        this.creationState = creationState;
    }

    public MsgVpnAttributes getMsgVpnAttributes() {
        return msgVpnAttributes;
    }

    public void setMsgVpnAttributes(MsgVpnAttributes msgVpnAttributes) {
        this.msgVpnAttributes = msgVpnAttributes;
    }

    public PlanAttributes getPlanAttributes() {
        return planAttributes;
    }

    public void setPlanAttributes(PlanAttributes planAttributes) {
        this.planAttributes = planAttributes;
    }

    public PlanDisplayAttributes getPlanDisplayAttributes() {
        return planDisplayAttributes;
    }

    public void setPlanDisplayAttributes(PlanDisplayAttributes planDisplayAttributes) {
        this.planDisplayAttributes = planDisplayAttributes;
    }

    public List<AccountingLimit> getAccountingLimits() {
        return accountingLimits;
    }

    public void setAccountingLimits(List<AccountingLimit> accountingLimits) {
        this.accountingLimits = accountingLimits;
    }

}
