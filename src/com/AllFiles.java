package com;

import java.io.File;

public class AllFiles {
    public static void main(String[] args) {
        File projectFile = new File("/home/jd/TopJava/basejava");
        if (projectFile.isDirectory()) {
            for (String name : projectFile.list()) {
                System.out.println(name);
            }
        }
    }
}
