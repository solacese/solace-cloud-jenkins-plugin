
package com.solace.cloud.dto;

import java.util.List;

public class ManagementProtocol {

    private String name;
    private String username;
    private String password;
    private List<EndPoint_> endPoints = null;
    private Limits_ limits;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<EndPoint_> getEndPoints() {
        return endPoints;
    }

    public void setEndPoints(List<EndPoint_> endPoints) {
        this.endPoints = endPoints;
    }

    public Limits_ getLimits() {
        return limits;
    }

    public void setLimits(Limits_ limits) {
        this.limits = limits;
    }

}
