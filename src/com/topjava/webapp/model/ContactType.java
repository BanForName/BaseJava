package com.topjava.webapp.model;

import java.io.Serializable;

public enum ContactType {
    PHONE("Телефон"),
    SKYPE("SKYPE"),
    EMAIL("Эл/почта"),
    LINKEDIN("Профиль Linkedin"),
    GITHUB("Профиль Github"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOMEPAGE("Домашняя страница");

    private final String title;

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