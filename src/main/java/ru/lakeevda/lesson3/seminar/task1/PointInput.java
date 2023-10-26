package ru.lakeevda.lesson3.seminar.task1;

import ru.lakeevda.lesson3.seminar.task1.services.StartService;
import ru.lakeevda.lesson3.seminar.task1.controller.GUI;

public class PointInput {

    public static void main(String[] args) {
        StartService startService = new StartService();
        startService.init();
        GUI gui = new GUI();
        gui.run();


    }
}
