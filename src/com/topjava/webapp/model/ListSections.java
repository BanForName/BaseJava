package com.topjava.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListSections extends AbstractSection {
    private List<String> list = new ArrayList<>();

    public ListSections(List<String> list) {
        Objects.requireNonNull("list must not be null");
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
