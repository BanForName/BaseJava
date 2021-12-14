package com.topjava.webapp.model;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSections extends AbstractSection {
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<String> list;

    public ListSections(String... list) {
        this(Arrays.asList(list));
    }

    public ListSections(List<String> list) {
        Objects.requireNonNull(list, "textList must not be null");
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSections that = (ListSections) o;
        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}