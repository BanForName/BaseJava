package com.topjava.webapp.model;

import java.time.LocalDate;

public class SkillsSections extends Sections{
    private String link;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String text;

    public SkillsSections(String link, LocalDate startDate, LocalDate endDate, String title, String text) {
        this.link = link;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}



