package com.topjava.webapp.model;

public class Link {
    private String title;
    private String url;

    public Link(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return title;
    }
}
