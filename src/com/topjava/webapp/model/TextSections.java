package com.topjava.webapp.model;

import java.util.Objects;

public class TextSections extends Sections {
    private String text;

    public TextSections(String text) {
        Objects.requireNonNull("text must be not null");
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
