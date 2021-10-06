package com.topjava.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Experience extends AbstractSection {
    private final Link link;
    private List<ListExp> listExp = new ArrayList<>();

    public Experience(Link link, ListExp... listExp) {
        Objects.requireNonNull(link, " must be not null");
        this.link = link;
        this.listExp = Arrays.asList(listExp);
    }

    public Link getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "\n" + link + "\n" + listExp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Experience that = (Experience) o;

        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (listExp != null ? !listExp.equals(that.listExp) : that.listExp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = link != null ? link.hashCode() : 0;
        result = 31 * result + (listExp != null ? listExp.hashCode() : 0);
        return result;
    }

    public static class ListExp {
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String title;
        private final String text;

        public ListExp(LocalDate startDate, LocalDate endDate, String title, String text) {
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

            ListExp listExp = (ListExp) o;

            if (startDate != null ? !startDate.equals(listExp.startDate) : listExp.startDate != null) return false;
            if (endDate != null ? !endDate.equals(listExp.endDate) : listExp.endDate != null) return false;
            if (title != null ? !title.equals(listExp.title) : listExp.title != null) return false;
            if (!text.equals(listExp.text)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = startDate != null ? startDate.hashCode() : 0;
            result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
            result = 31 * result + (title != null ? title.hashCode() : 0);
            result = 31 * result + text.hashCode();
            return result;
        }
    }
}