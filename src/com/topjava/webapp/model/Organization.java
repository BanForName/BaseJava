package com.topjava.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization extends AbstractSection {
    private List<Experience> sectionsList = new ArrayList<>();

    public Organization(List<Experience> sectionsList) {
        Objects.requireNonNull(sectionsList, " must be not null");
        this.sectionsList = sectionsList;
    }

    public List<Experience> getSectionsList() {
        return sectionsList;
    }

    @Override
    public String toString() {
        return sectionsList.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return sectionsList.equals(that.sectionsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectionsList);
    }
}