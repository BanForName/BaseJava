package com.topjava.webapp.model;

import static com.topjava.webapp.util.DateUtil.of;
import static com.topjava.webapp.util.DateUtil.NOW;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Link link;
    private final List<Experience> exp;

    public Organization(Link link, Experience... exp) {
        Objects.requireNonNull(link, " must be not null");
        this.link = link;
        this.exp = Arrays.asList(exp);
    }

    public Organization(Link link, List<Experience> exp) {
        this.link = link;
        this.exp = exp;
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

    public static class Experience implements Serializable {
        private static final long serialVersionUID = 1L;

        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String title;
        private final String description;

        public Experience(int startYear, Month startMonth, String title, String description) {
            this(of(startYear, startMonth), NOW, title, description);
        }

        public Experience(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), title, description);
        }

        public Experience(LocalDate startDate, LocalDate endDate, String title, String description) {
            Objects.requireNonNull(startDate, " must be not null");
            Objects.requireNonNull(endDate, " must be not null");
            Objects.requireNonNull(title, " must be not null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
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
            return description;
        }

        @Override
        public String toString() {
            return "\n" + startDate + " - " + endDate + "  " + title + "\n" + description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Experience that = (Experience) o;
            return Objects.equals(startDate, that.startDate) &&
                    Objects.equals(endDate, that.endDate) &&
                    Objects.equals(title, that.title) &&
                    description.equals(that.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, title, description);
        }
    }
}