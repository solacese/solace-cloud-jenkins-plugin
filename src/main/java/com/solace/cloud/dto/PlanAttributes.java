
package com.solace.cloud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlanAttributes {

    private String vpnEventLargeMsgThreshold;
    
    @JsonProperty("Cost")
    private String cost;
    
    @JsonProperty("Message Storage Encryption")
    private String messageStorageEncryption;
    
    @JsonProperty("Configuration Encryption")
    private String configurationEncryption;
    private String vpnMaxConnectionCount;
    private String vpnMaxTransactedSessionCount;
    private String vpnMaxTransactionCount;
    private String vpnMaxMsgSpoolUsage;
    private String vpnMaxEndpointCount;
    private String vpnMaxEgressFlowCount;
    private String vpnMaxSubscriptionCount;
    private String vpnMaxIngressFlowCount;

    public String getVpnEventLargeMsgThreshold() {
        return vpnEventLargeMsgThreshold;
    }

    public void setVpnEventLargeMsgThreshold(String vpnEventLargeMsgThreshold) {
        this.vpnEventLargeMsgThreshold = vpnEventLargeMsgThreshold;
    }

    public String getCost() {
        return cost;
    }

    @JsonProperty("Cost")
    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getMessageStorageEncryption() {
        return messageStorageEncryption;
    }

    public void setMessageStorageEncryption(String messageStorageEncryption) {
        this.messageStorageEncryption = messageStorageEncryption;
    }

    public String getConfigurationEncryption() {
        return configurationEncryption;
    }

    public void setConfigurationEncryption(String configurationEncryption) {
        this.configurationEncryption = configurationEncryption;
    }

    public String getVpnMaxConnectionCount() {
        return vpnMaxConnectionCount;
    }

    public void setVpnMaxConnectionCount(String vpnMaxConnectionCount) {
        this.vpnMaxConnectionCount = vpnMaxConnectionCount;
    }

    public String getVpnMaxTransactedSessionCount() {
        return vpnMaxTransactedSessionCount;
    }

    public void setVpnMaxTransactedSessionCount(String vpnMaxTransactedSessionCount) {
        this.vpnMaxTransactedSessionCount = vpnMaxTransactedSessionCount;
    }

    public String getVpnMaxTransactionCount() {
        return vpnMaxTransactionCount;
    }

    public void setVpnMaxTransactionCount(String vpnMaxTransactionCount) {
        this.vpnMaxTransactionCount = vpnMaxTransactionCount;
    }

    public String getVpnMaxMsgSpoolUsage() {
        return vpnMaxMsgSpoolUsage;
    }

    public void setVpnMaxMsgSpoolUsage(String vpnMaxMsgSpoolUsage) {
        this.vpnMaxMsgSpoolUsage = vpnMaxMsgSpoolUsage;
    }

    public String getVpnMaxEndpointCount() {
        return vpnMaxEndpointCount;
    }

    public void setVpnMaxEndpointCount(String vpnMaxEndpointCount) {
        this.vpnMaxEndpointCount = vpnMaxEndpointCount;
    }

    public String getVpnMaxEgressFlowCount() {
        return vpnMaxEgressFlowCount;
    }

    public void setVpnMaxEgressFlowCount(String vpnMaxEgressFlowCount) {
        this.vpnMaxEgressFlowCount = vpnMaxEgressFlowCount;
    }

    public String getVpnMaxSubscriptionCount() {
        return vpnMaxSubscriptionCount;
    }

    public void setVpnMaxSubscriptionCount(String vpnMaxSubscriptionCount) {
        this.vpnMaxSubscriptionCount = vpnMaxSubscriptionCount;
    }

    public String getVpnMaxIngressFlowCount() {
        return vpnMaxIngressFlowCount;
    }

    public void setVpnMaxIngressFlowCount(String vpnMaxIngressFlowCount) {
        this.vpnMaxIngressFlowCount = vpnMaxIngressFlowCount;
    }

}
