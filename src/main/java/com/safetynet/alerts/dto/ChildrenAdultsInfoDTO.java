package com.safetynet.alerts.dto;

import java.util.List;

public class ChildrenAdultsInfoDTO {

    private List<PersonDTO> adults;
    private List<PersonDTO> children;

    public List<PersonDTO> getAdults() {
        return adults;
    }

    public void setAdults(List<PersonDTO> adults) {
        this.adults = adults;
    }

    public ChildrenAdultsInfoDTO adults(List<PersonDTO> adults) {
        this.adults = adults;
        return this;
    }

    public List<PersonDTO> getChildren() {
        return children;
    }

    public void setChildren(List<PersonDTO> children) {
        this.children = children;
    }

    public ChildrenAdultsInfoDTO children(List<PersonDTO> children) {
        this.children = children;
        return this;
    }

    @Override
    public String toString() {
        return "ChildrenAdultsInfoDTO{" +
                "adults=" + adults +
                ", children=" + children +
                '}';
    }
}
