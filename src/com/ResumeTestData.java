package com;

import com.topjava.webapp.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.time.LocalTime.of;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");
        TextSections text;
        ListSections list;
        SkillsSections skills;

        resume.setContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.setContact(ContactType.SKYPE, "grigory.kislin");
        resume.setContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.setContact(ContactType.LINKEDIN, "link");
        resume.setContact(ContactType.GITHUB, "Linlink");
        resume.setContact(ContactType.STACKOVERFLOW, "StackLink");
        resume.setContact(ContactType.HOMEPAGE, "HomeLink");
        System.out.println(resume.getContacts()+ "\n");

        resume.setSection(SectionType.OBJECTIVE, text = new TextSections("" +
                "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        System.out.println(SectionType.OBJECTIVE.getTitle() + "\n" + text.getText() + "\n");

        resume.setSection(SectionType.PERSONAL, text = new TextSections(
                "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        System.out.println(SectionType.PERSONAL.getTitle() + "\n" + text.getText() + "\n");

        List<String> listAchive = new ArrayList<>();
        listAchive.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\"." +
                " Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        resume.setSection(SectionType.ACHIEVEMENT, list = new ListSections(listAchive));
        System.out.println(SectionType.ACHIEVEMENT.getTitle() + "\n" + list + "\n");

        List<String> listQualifications = new ArrayList<>();
        listQualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        listQualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        listQualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        listQualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        resume.setSection(SectionType.QUALIFICATIONS, list = new ListSections(listQualifications));
        System.out.println(SectionType.QUALIFICATIONS.getTitle() + "\n" + list + "\n");

        resume.setSection(SectionType.EXPERIENCE, skills = new SkillsSections(
                "Java Online Project",
                LocalDate.of(2013, 10, 1),
                LocalDate.now(),
                "Автор проекта",
                "Создание, организация и проведение Java онлайн проектов и стажировок."));
        System.out.println(SectionType.EXPERIENCE.getTitle() + "\n" +
                skills.getStartDate() + " - " + skills.getEndDate() + " " + skills.getTitle() + "\n" + skills.getText() + "\n");

        resume.setSection(SectionType.EXPERIENCE, skills = new SkillsSections(
                "Wrike",
                LocalDate.of(2014, 10, 1),
                LocalDate.of(2016, 01, 1),
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
        System.out.println(SectionType.EXPERIENCE.getTitle() + "\n" +
                skills.getStartDate() + " - " + skills.getEndDate() + " " + skills.getTitle() + "\n" + skills.getText() + "\n");

        resume.setSection(SectionType.EXPERIENCE, skills = new SkillsSections(
                "Alcatel",
                LocalDate.of(1997, 9, 1),
                LocalDate.of(2005, 01, 1),
                "Инженер по аппаратному и программному тестированию",
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."));
        System.out.println(SectionType.EXPERIENCE.getTitle() + "\n" +
                skills.getStartDate() + " - " + skills.getEndDate() + " " + skills.getTitle() + "\n" + skills.getText() + "\n");

        resume.setSection(SectionType.EDUCATION, skills = new SkillsSections(
                "Coursera",
                LocalDate.of(2013, 03, 01),
                LocalDate.of(2013, 05, 01),
                "\"Functional Programming Principles in Scala\" by Martin Odersky",
                ""));
        System.out.println(SectionType.EDUCATION.getTitle() + "\n" +
                skills.getStartDate() + " - " + skills.getEndDate() + " " + skills.getTitle() + "\n" + skills.getText() + "\n");

    }
}
