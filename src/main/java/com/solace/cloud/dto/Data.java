
package com.solace.cloud.dto;

import java.util.List;

public class Data {

    private String type;
    private Long timestamp;
    private String userId;
    private String serviceId;
    private String name;
    private String datacenterId;
    private Attributes attributes;
    private String serviceTypeId;
    private String serviceClassId;
    private String adminState;
    private String adminProgress;
    private Long created;
    private String creationState;
    private List<MessagingProtocol> messagingProtocols = null;
    private List<ManagementProtocol> managementProtocols = null;
    private MsgVpnAttributes msgVpnAttributes;
    private Boolean locked;
    private Long messagingStorage;
    private String serviceStage;
    private String servicePackageId;
    private ServiceClassDisplayedAttributes serviceClassDisplayedAttributes;
    private List<AccountingLimit> accountingLimits = null;
    private List<String> certificateAuthorities = null;
    private List<String> clientProfiles = null;
    private Cluster cluster;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
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

    public String getAdminProgress() {
        return adminProgress;
    }

    public void setAdminProgress(String adminProgress) {
        this.adminProgress = adminProgress;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getCreationState() {
        return creationState;
    }

    public void setCreationState(String creationState) {
        this.creationState = creationState;
    }

    public List<MessagingProtocol> getMessagingProtocols() {
        return messagingProtocols;
    }

    public void setMessagingProtocols(List<MessagingProtocol> messagingProtocols) {
        this.messagingProtocols = messagingProtocols;
    }

    public List<ManagementProtocol> getManagementProtocols() {
        return managementProtocols;
    }

    public void setManagementProtocols(List<ManagementProtocol> managementProtocols) {
        this.managementProtocols = managementProtocols;
    }

    public MsgVpnAttributes getMsgVpnAttributes() {
        return msgVpnAttributes;
    }

    public void setMsgVpnAttributes(MsgVpnAttributes msgVpnAttributes) {
        this.msgVpnAttributes = msgVpnAttributes;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Long getMessagingStorage() {
        return messagingStorage;
    }

    public void setMessagingStorage(Long messagingStorage) {
        this.messagingStorage = messagingStorage;
    }

    public String getServiceStage() {
        return serviceStage;
    }

    public void setServiceStage(String serviceStage) {
        this.serviceStage = serviceStage;
    }

    public String getServicePackageId() {
        return servicePackageId;
    }

    public void setServicePackageId(String servicePackageId) {
        this.servicePackageId = servicePackageId;
    }

    public ServiceClassDisplayedAttributes getServiceClassDisplayedAttributes() {
        return serviceClassDisplayedAttributes;
    }

    public void setServiceClassDisplayedAttributes(ServiceClassDisplayedAttributes serviceClassDisplayedAttributes) {
        this.serviceClassDisplayedAttributes = serviceClassDisplayedAttributes;
    }

    public List<AccountingLimit> getAccountingLimits() {
        return accountingLimits;
    }

    public void setAccountingLimits(List<AccountingLimit> accountingLimits) {
        this.accountingLimits = accountingLimits;
    }

    public List<String> getCertificateAuthorities() {
        return certificateAuthorities;
    }

    public void setCertificateAuthorities(List<String> certificateAuthorities) {
        this.certificateAuthorities = certificateAuthorities;
    }

    public List<String> getClientProfiles() {
        return clientProfiles;
    }

    public void setClientProfiles(List<String> clientProfiles) {
        this.clientProfiles = clientProfiles;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

}
