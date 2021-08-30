package com.topjava.webapp.model;

public enum Contacts {
    PHONE("Телефон"),
    SKYPE("SKYPE"),
    EMAIL("Эл/почта"),
    LINKEDIN("Профиль Linkedin"),
    GITHUB("Профиль Github"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOMEPAGE("Домашняя страница");

    private String title;

    Contacts(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
