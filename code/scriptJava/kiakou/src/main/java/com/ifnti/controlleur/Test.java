package com.ifnti.controlleur;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        String str = "Oui";
        String[] listStr = str.split("&");
        for (String string : listStr) {
            System.out.println(string);
        }
    }
}
