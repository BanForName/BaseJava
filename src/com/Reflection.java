package com;

import java.lang.reflect.Field;
import java.sql.Ref;

public class Reflection {
    private String name = "ILSHAT";

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        new Reflection().run();
    }
    public void run() throws IllegalAccessException, NoSuchFieldException {
        Reflection r = new Reflection();
        Class reflClass = Reflection.class;
        Field fields = reflClass.getDeclaredField("name");
        String s = "КАРТОШЕЧКА";
        fields.set(r, s);

        System.out.println(r.name);
    }
}
