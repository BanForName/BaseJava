package com;

import com.topjava.webapp.model.*;
import com.topjava.webapp.util.DateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalTime.of;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        resume.setContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.setContact(ContactType.SKYPE, "grigory.kislin");
        resume.setContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.setContact(ContactType.LINKEDIN, new Link("Профиль LinkedIn", "https://www.linkedin.com/in/gkislin").getUrl());
        resume.setContact(ContactType.GITHUB, new Link("Профиль Github", "https://github.com/gkislin").getUrl());
        resume.setContact(ContactType.STACKOVERFLOW, new Link("Профиль Stackoverflow", "https://stackoverflow.com/users/548473/grigory-kislin").getUrl());
        resume.setContact(ContactType.HOMEPAGE, new Link("Домашняя страница", "http://gkislin.ru/").getUrl());

        resume.setSection(SectionType.OBJECTIVE, new TextSections("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.setSection(SectionType.PERSONAL, new TextSections("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        List<String> achiveList = new ArrayList<>();
        achiveList.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achiveList.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achiveList.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.\n");
        achiveList.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        resume.setSection(SectionType.ACHIEVEMENT, new ListSections(achiveList));

        List<String> qualifList = new ArrayList<>();
        qualifList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifList.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualifList.add("MySQL, SQLite, MS SQL, HSQLDB");
        resume.setSection(SectionType.QUALIFICATIONS, new ListSections(qualifList));

        List<Experience> experienceList = new ArrayList<>();
        experienceList.add(new Experience(new Link(
                "Java Online Projects", "https://javaops.ru/"),
                LocalDate.of(2013, 10, 01),
                LocalDate.now(),
                "Автор проекта",
                "Создание, организация и проведение Java онлайн проектов и стажировок."));
        experienceList.add(new Experience(new Link(
                "Wrike", "https://www.wrike.com/"),
                LocalDate.of(2014, 10, 01),
                LocalDate.of(2016, 01, 01),
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
        resume.setSection(SectionType.EXPERIENCE, new Organization(experienceList));

        List<Experience> educationList = new ArrayList<>();
        educationList.add(new Experience(new Link(
                "Coursera", "https://www.coursera.org/learn/scala-functional-programming"),
                LocalDate.of(2013, 03, 01),
                LocalDate.of(2013, 05, 01),
                "\"Functional Programming Principles in Scala\" by Martin Odersky",
                ""));
        educationList.add(new Experience(new Link(
                "Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366"),
                LocalDate.of(2011, 03, 01),
                LocalDate.of(2011, 04, 01),
                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.",
                ""));
        resume.setSection(SectionType.EDUCATION, new Organization(educationList));

        System.out.println(resume);
        System.out.println(resume.getContacts());
        System.out.println(resume.getSections());
    }
}