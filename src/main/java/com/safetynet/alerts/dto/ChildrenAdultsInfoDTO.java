package com.safetynet.alerts.dto;

import com.safetynet.alerts.model.Person;

import java.util.List;

public class ChildrenAdultsInfoDTO {

    private List<Person> adults;
    private List<Person> children;

    public List<Person> getAdults() {
        return adults;
    }

    public void setAdults(List<Person> adults) {
        this.adults = adults;
    }

    public ChildrenAdultsInfoDTO adults(List<Person> adults) {
        this.adults = adults;
        return this;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }

    public ChildrenAdultsInfoDTO children(List<Person> children) {
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
