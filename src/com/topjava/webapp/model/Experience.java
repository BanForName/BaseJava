package com.topjava.webapp.model;

import java.time.LocalDate;

public class Experience extends AbstractSection {
    private Link link;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String text;

    public Experience(Link link, LocalDate startDate, LocalDate endDate, String title, String text) {
        this.link = link;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.text = text;
    }

    public Link getLink() {
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

    @Override
    public String toString() {
        return link + "\n" + startDate + " - " + endDate + "  " + title + "\n" + text;
    }
}
