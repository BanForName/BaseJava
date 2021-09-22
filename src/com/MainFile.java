package com;

import com.google.gwt.thirdparty.guava.common.io.Closer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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
        for (String name : dir.list()) {
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

        //try-with-resources in google guava(class Closer)
        Closer closer = Closer.create();
        try {
            FileInputStream fileInputStream = closer.register(new FileInputStream("file"));
        } catch (Throwable e) {
            throw closer.rethrow(e);
        } finally {
            closer.close();
        }
    }
}
