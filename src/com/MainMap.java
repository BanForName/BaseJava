package com;

import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.listStorage.ListStorage;
import com.topjava.webapp.storage.mapStorage.MapStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainMap {
    private final static MapStorage MAP_STORAGE = new MapStorage();

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
                    System.out.println(MAP_STORAGE.size());
                    break;
                case "save":
                    r = new Resume(fullname);
                    MAP_STORAGE.save(r);
                    printAll();
                    break;
                case "delete":
                    MAP_STORAGE.delete(fullname);
                    printAll();
                    break;
                case "get":
                    System.out.println(MAP_STORAGE.get(fullname));
                    break;
                case "update":
                    r = new Resume(fullname, params[2]);
                    MAP_STORAGE.update(r);
                    printAll();
                    break;
                case "clear":
                    MAP_STORAGE.clear();
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
        List<Resume> all = MAP_STORAGE.getAllSorted();
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
