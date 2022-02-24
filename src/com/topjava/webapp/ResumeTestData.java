package com.topjava.webapp;

import com.topjava.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        resume.setContact(ContactType.LINKEDIN, new Link("Профиль LinkedIn", "https://www.linkedin.com/in/gkislin").getUrl());
        resume.setContact(ContactType.GITHUB, new Link("Профиль Github", "https://github.com/gkislin").getUrl());
        resume.setContact(ContactType.STACKOVERFLOW, new Link("Профиль Stackoverflow", "https://stackoverflow.com/users/548473/grigory-kislin").getUrl());
        resume.setContact(ContactType.HOMEPAGE, new Link("Домашняя страница", "http://gkislin.ru/").getUrl());
//
        resume.setSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.setSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        List<String> achiveList = new ArrayList<>();
        achiveList.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achiveList.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achiveList.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.\n");
        achiveList.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        resume.setSection(SectionType.ACHIEVEMENT, new ListSection(achiveList));

        List<String> qualifList = new ArrayList<>();
        qualifList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifList.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualifList.add("MySQL, SQLite, MS SQL, HSQLDB");
        resume.setSection(SectionType.QUALIFICATIONS, new ListSection(qualifList));

        List<Organization> organizationList = new ArrayList<>();
        organizationList.add(new Organization(new Link("Java Online Projects", "https://javaops.ru/"),
                new Organization.Experience(LocalDate.of(2013, 10, 1), LocalDate.now(), "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок.")));
        organizationList.add(new Organization(new Link("Java Online Projects", "https://javaops.ru/"),
                new Organization.Experience(LocalDate.of(2010, 1, 1), LocalDate.of(2011, 9, 1), "Директор", "Управлние")));
        organizationList.add(new Organization(new Link("Wrike", "https://www.wrike.com/"),
                new Organization.Experience(LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")));
        resume.setSection(SectionType.EXPERIENCE, new OrganizationSection(organizationList));

        List<Organization> educationList = new ArrayList<>();
        educationList.add(new Organization(new Link("Coursera", "https://www.coursera.org/learn/scala-functional-programming"),
                new Organization.Experience(LocalDate.of(2013, 3, 1), LocalDate.of(2013, 5, 1), "\"Functional Programming Principles in Scala\" by Martin Odersky", "")));
        educationList.add(new Organization(new Link("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366"),
                new Organization.Experience(LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1), "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.", ""),
                new Organization.Experience(LocalDate.of(2016, 12, 1), LocalDate.now(), "Tester", "Hello world"),
                new Organization.Experience(LocalDate.of(2015, 4, 13), LocalDate.of(2015, 6, 29), "Manager", "buying/selling")));
        resume.setSection(SectionType.EDUCATION, new OrganizationSection(educationList));
        return resume;
    }
}