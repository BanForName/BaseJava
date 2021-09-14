package com.topjava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Organization extends AbstractSection {
    private List<Experience> sectionsList = new ArrayList<>();

    public Organization(List<Experience> sectionsList) {
        this.sectionsList = sectionsList;
    }

    public List<Experience> getSectionsList() {
        return sectionsList;
    }

    @Override
    public String toString() {
        return sectionsList.toString();
    }
}



