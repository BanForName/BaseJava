package com.topjava.webapp.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Experience extends AbstractSection {
    private final Link link;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String text;

    public Experience(Link link, LocalDate startDate, LocalDate endDate, String title, String text) {
        Objects.requireNonNull(link, " must be not null");
        Objects.requireNonNull(startDate, " must be not null");
        Objects.requireNonNull(endDate, " must not be null");
        Objects.requireNonNull(title, " must be not null");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return link.equals(that.link) && startDate.equals(that.startDate) && endDate.equals(that.endDate) && title.equals(that.title) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, startDate, endDate, title, text);
    }
}