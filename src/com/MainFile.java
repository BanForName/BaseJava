package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) throws IOException {
        File file = new File("./.gitignore");
        try {
            System.out.println(file.getCanonicalFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Список файлов в каталоге
        File dir = new File("./src/com/topjava/webapp/model");
        System.out.println(dir.isDirectory());
        for (String name : Objects.requireNonNull(dir.list())) {
            System.out.println(name);
        }

        //try-with-resources
        try (FileInputStream fis = new FileInputStream(file);) {
            System.out.println(fis.read());
            int c;
            while ((c = fis.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
