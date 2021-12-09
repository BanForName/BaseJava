package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) throws IOException {
        File file = new File("./.gitignore");
        try {
            System.out.println(file.getCanonicalFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Список файлов в каталоге
        File dir = new File("./src/com/topjava/webapp/model");
        System.out.println(dir.isDirectory());
        Arrays.stream(Objects.requireNonNull(dir.list())).forEach(System.out::println);

        // try-with-resources
        try (FileInputStream fis = new FileInputStream(file)) {
            System.out.println(fis.read());
            int c;
            while ((c = fis.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            throw new RuntimeException("error", e);
        }

        File projectFile = new File("../baseJava");
        printAllFiles(projectFile);
    }

    private static void printAllFiles(File file) {
        // рекурсивнй обход дерева каталогов
        File[] folders = file.listFiles();
        for (File entry : Objects.requireNonNull(folders)) {
            if (entry.isDirectory()) {
                System.out.println("Directory | " + entry.getName());
                printAllFiles(entry);
                continue;
            }
            System.out.println("File |" + entry.getName());
        }
    }
}
