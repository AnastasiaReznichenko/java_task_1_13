package com.SlenS.Task1;

public class Task {
    String number;

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public Task(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Task{" +
                "number='" + number + '\'' +
                '}';
    }
}

