package com;

import java.io.File;
import java.util.Objects;

public class ProjectTree {
    public static void main(String[] args) {
        File projectFile = new File("../basejava");
        printAllFiles(projectFile);
    }

    private static void printAllFiles(File file) {
        File[] folders = file.listFiles();
        for (File entry : Objects.requireNonNull(folders)) {
            if (entry.isDirectory()) {
                System.out.println(entry.getName());
                printAllFiles(entry);
                continue;
            }
            System.out.println("   |" + entry.getName());
        }
    }
}
