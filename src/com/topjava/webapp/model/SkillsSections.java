package com.topjava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class SkillsSections extends Sections{
    private List<Sections> sectionsList = new ArrayList<>();

    public SkillsSections(List<Sections> sectionsList) {
        this.sectionsList = sectionsList;
    }

    public List<Sections> getSectionsList() {
        return sectionsList;
    }

    @Override
    public String toString() {
        return sectionsList.toString();
    }
}



