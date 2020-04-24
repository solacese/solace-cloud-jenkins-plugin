
package com.solace.cloud.dto;

import java.util.List;

public class Cluster {

    private String name;
    private String password;
    private String remoteAddress;
    private String primaryRouterName;
    private List<String> supportedAuthenticationMode = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public String getPrimaryRouterName() {
        return primaryRouterName;
    }

    public void setPrimaryRouterName(String primaryRouterName) {
        this.primaryRouterName = primaryRouterName;
    }

    public List<String> getSupportedAuthenticationMode() {
        return supportedAuthenticationMode;
    }

    public void setSupportedAuthenticationMode(List<String> supportedAuthenticationMode) {
        this.supportedAuthenticationMode = supportedAuthenticationMode;
    }

}
