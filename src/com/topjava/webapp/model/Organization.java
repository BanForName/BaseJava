package com.topjava.webapp.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link link;
    private List<Experience> exp;

    public Organization(Link link, Experience... exp) {
        Objects.requireNonNull(link, " must be not null");
        this.link = link;
        this.exp = Arrays.asList(exp);
    }

    public Link getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "\n" + link + "\n" + exp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(link, that.link) &&
                Objects.equals(exp, that.exp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, exp);
    }

    public static class Experience {
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String title;
        private final String text;

        public Experience(LocalDate startDate, LocalDate endDate, String title, String text) {
            Objects.requireNonNull(startDate, " must be not null");
            Objects.requireNonNull(endDate, " must be not null");
            Objects.requireNonNull(title, " must be not null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.text = text;
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
            return "\n" + startDate + " - " + endDate + "  " + title + "\n" + text;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Experience that = (Experience) o;
            return Objects.equals(startDate, that.startDate) &&
                    Objects.equals(endDate, that.endDate) &&
                    Objects.equals(title, that.title) &&
                    text.equals(that.text);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, title, text);
        }
    }
}