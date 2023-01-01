package com.topjava.webapp;

import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.arrayStorage.SortedArrayStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Interactive test for com.topjava.webapp.storage.AbstractStorage.ArrayStorage implementation
 * (just run, no need to understand)
 */
public class SortedMainArray {
    private static SortedArrayStorage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume r;
        while (true) {
            System.out.print("Введите одну из команд - (list | size | save fullName | update fullName | delete fullName | get fullName | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 3) {
                System.out.println("Неверная команда.");
                continue;
            }
            String fullName = null;
            if (params.length > 1) {
                fullName = params[1].intern();
            }
            switch (params[0]) {
                case "list":
                    printAll();
                    break;
                case "size":
                    System.out.println(SORTED_ARRAY_STORAGE.size());
                    break;
                case "save":
                    r = new Resume(fullName);
                    SORTED_ARRAY_STORAGE.save(r);
                    printAll();
                    break;
                case "delete":
                    SORTED_ARRAY_STORAGE.delete(fullName);
                    printAll();
                    break;
                case "get":
                    System.out.println(SORTED_ARRAY_STORAGE.get(fullName));
                    break;
                case "update":
                    r = new Resume(fullName, params[2]);
                    SORTED_ARRAY_STORAGE.update(r);
                    printAll();
                    break;
                case "clear":
                    SORTED_ARRAY_STORAGE.clear();
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
        List<Resume> all = SORTED_ARRAY_STORAGE.getAllSorted();
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
