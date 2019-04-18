// package com.nimbalkar.chetan;

public class RoundRobin extends Scheduling{
    private int quantum;

    public void execute() {
        init();
        System.out.println("Enter time quantum : ");
        quantum = scanner.nextInt();

    }
}
