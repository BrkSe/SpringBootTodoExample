package com.burakkutbay.todo.util;

public class Calculator {

    public String percentTask(int taskCount, int completedTaskCount){

        double percent=0;

        percent=(100*completedTaskCount)/taskCount;

        return String.valueOf(percent);
    }
}
