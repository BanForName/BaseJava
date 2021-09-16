package com.topjava.webapp.model;

public enum ContactType {
    PHONE("Телефон"),
    SKYPE("SKYPE"),
    EMAIL("Эл/почта"),
    LINKEDIN("Профиль Linkedin"),
    GITHUB("Профиль Github"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOMEPAGE("Домашняя страница");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title + ": ";
    }
}