package com.topjava.webapp;

import com.topjava.webapp.model.*;
import com.topjava.webapp.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
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
                new Organization.Experience(DateUtil.of(2013, Month.OCTOBER), DateUtil.NOW, "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок.")));
        organizationList.add(new Organization(new Link("Java Online Projects", "https://javaops.ru/"),
                new Organization.Experience(DateUtil.of(2010, Month.JANUARY), DateUtil.of(2011, Month.SEPTEMBER), "Директор", "Управлние")));
        organizationList.add(new Organization(new Link("Wrike", "https://www.wrike.com/"),
                new Organization.Experience(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2016, Month.JANUARY), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")));
        resume.setSection(SectionType.EXPERIENCE, new OrganizationSection(organizationList));

        List<Organization> educationList = new ArrayList<>();
        educationList.add(new Organization(new Link("Coursera", "https://www.coursera.org/learn/scala-functional-programming"),
                new Organization.Experience(DateUtil.of(2013, Month.MARCH), DateUtil.of(2013, Month.MAY), "\"Functional Programming Principles in Scala\" by Martin Odersky", "")));
        educationList.add(new Organization(new Link("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366"),
                new Organization.Experience(DateUtil.of(2011, Month.MARCH), DateUtil.of(2011, Month.MARCH), "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.", ""),
                new Organization.Experience(DateUtil.of(2016, Month.DECEMBER), DateUtil.NOW, "Tester", "Hello world"),
                new Organization.Experience(DateUtil.of(2015, Month.APRIL), DateUtil.of(2015, Month.JUNE), "Manager", "buying/selling")));
        resume.setSection(SectionType.EDUCATION, new OrganizationSection(educationList));
        return resume;
    }
}