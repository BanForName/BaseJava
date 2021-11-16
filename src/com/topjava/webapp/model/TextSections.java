package com.topjava.webapp.model;

import java.util.Objects;

public class TextSections extends AbstractSection {
    private final String text;

    public TextSections(String text) {
        Objects.requireNonNull(text, "text must be not null");
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSections that = (TextSections) o;
        return text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}