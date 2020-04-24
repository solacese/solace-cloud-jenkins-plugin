
package com.solace.cloud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceClassDisplayedAttributes {

    @JsonProperty("High Availability")
    private String highAvailability;
    
    @JsonProperty("Network Speed")
    private String networkSpeed;
    
    @JsonProperty("Storage")
    private String storage;
    
    @JsonProperty("Message Broker Tenancy")
    private String messageBrokerTenancy;
    
    @JsonProperty("Queues")
    private String queues;
    
    @JsonProperty("Clients")
    private String clients;
    
    @JsonProperty("Network Usage")
    private String networkUsage;

    public String getHighAvailability() {
        return highAvailability;
    }

    public void setHighAvailability(String highAvailability) {
        this.highAvailability = highAvailability;
    }

    public String getNetworkSpeed() {
        return networkSpeed;
    }

    public void setNetworkSpeed(String networkSpeed) {
        this.networkSpeed = networkSpeed;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getMessageBrokerTenancy() {
        return messageBrokerTenancy;
    }

    public void setMessageBrokerTenancy(String messageBrokerTenancy) {
        this.messageBrokerTenancy = messageBrokerTenancy;
    }

    public String getQueues() {
        return queues;
    }

    public void setQueues(String queues) {
        this.queues = queues;
    }

    public String getClients() {
        return clients;
    }

    public void setClients(String clients) {
        this.clients = clients;
    }

    public String getNetworkUsage() {
        return networkUsage;
    }

    public void setNetworkUsage(String networkUsage) {
        this.networkUsage = networkUsage;
    }

}
