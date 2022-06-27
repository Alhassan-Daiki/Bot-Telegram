package com.ifnti.controlleur;


public class Test {
    public static void main(String[] args) {
        String str = "Oui";
        String[] listStr = str.split("&");
        for (String string : listStr) {
            System.out.println(string);
        }
    }
}
