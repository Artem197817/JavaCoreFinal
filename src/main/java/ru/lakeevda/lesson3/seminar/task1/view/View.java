package ru.lakeevda.lesson3.seminar.task1.view;

import ru.lakeevda.lesson3.seminar.task1.model.Employee;
import ru.lakeevda.lesson3.seminar.task1.model.Priority;

public class View {

    public static void printConsole(String message) {
        System.out.println(message);
    }

    /**
     * Информирование работника о поступлении нового задания.
     */
    public static void informingEmployee(Employee employee, Priority priority) {
        View.printConsole("Для " + employee + " назначена новая задача с приоритетом " + priority);
    }

    public static void help (){
        printConsole("HELP");
        printConsole("EXIT");
        printConsole("GET A");
        printConsole("GET FT");
        printConsole("GET E");
        printConsole("GET AE");
        printConsole("GET ADE");
        printConsole("CREATE E");
        printConsole("CREATE T");
        printConsole("TASK M");
    }
}
