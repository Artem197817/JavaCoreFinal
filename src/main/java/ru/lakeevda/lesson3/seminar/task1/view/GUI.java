package ru.lakeevda.lesson3.seminar.task1.view;

import javax.swing.*;

public class GUI {
    public static String inputPane(String s){
        return JOptionPane.showInputDialog(null,s);
    }

    public static void messagePane (String message){
        JOptionPane.showMessageDialog(null,message);

    }
}
