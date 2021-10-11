package com.topjava.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {
    private List<Organization> sectionsList = new ArrayList<>();

    public OrganizationSection(List<Organization> sectionsList) {
        Objects.requireNonNull(sectionsList, " must be not null");
        this.sectionsList = sectionsList;
    }

    public List<Organization> getSectionsList() {
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
        OrganizationSection that = (OrganizationSection) o;
        return sectionsList.equals(that.sectionsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectionsList);
    }
}