package com;

import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.listStorage.ListStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Interactive test for com.topjava.webapp.storage.AbstractStorage.ArrayStorage implementation
 * (just run, no need to understand)
 */
public class MainList {
    private final static ListStorage LIST_STORAGE = new ListStorage();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume r;
        while (true) {
            System.out.print("Введите одну из команд - (list | size | save fullName | update uuid fullname | delete uuid | get uuid | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 3) {
                System.out.println("Неверная команда.");
                continue;
            }
            String fullname = null;
            if (params.length > 1) {
                fullname = params[1].intern();
            }
            switch (params[0]) {
                case "list":
                    printAll();
                    break;
                case "size":
                    System.out.println(LIST_STORAGE.size());
                    break;
                case "save":
                    r = new Resume(fullname);
                    LIST_STORAGE.save(r);
                    printAll();
                    break;
                case "delete":
                    LIST_STORAGE.delete(fullname);
                    printAll();
                    break;
                case "get":
                    System.out.println(LIST_STORAGE.get(fullname));
                    break;
                case "update":
                    r = new Resume(fullname, params[2]);
                    LIST_STORAGE.update(r);
                    printAll();
                    break;
                case "clear":
                    LIST_STORAGE.clear();
                    printAll();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Неверная команда.");
                    break;
            }
        }
    }

    static void printAll() {
        List<Resume> all = LIST_STORAGE.getAllSorted();
        System.out.println("----------------------------");
        if (all.size() == 0) {
            System.out.println("Empty");
        } else {
            for (Resume r : all) {
                System.out.println(r.getUuid() + " : " + r.getFullName());
            }
        }
        System.out.println("----------------------------");
    }
}
