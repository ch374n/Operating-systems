// package com.nimbalkar.chetan;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Scheduling {
	protected List<Process> waitQueue = new ArrayList<>();
	protected List<Process> readyQueue = new ArrayList<>();
	protected List<Process> finishQueue = new ArrayList<>();
    protected Scanner scanner = new Scanner(System.in);
    private int nums;

	public void init() {
        System.out.println("Enter the number of processes : ");
        nums = scanner.nextInt();
        for (int i = 0; i < nums; i++) {
            System.out.println("Enter arrival time and burst time of process : p " + (i + 1));
            int at = scanner.nextInt();
            int bt = scanner.nextInt();
            waitQueue.add(new Process("p" + (i+1), at, bt, 0, 0, 0, 0, 0));
        }
    }

    public void display(List<Process> queue, float avgtt, float avgwt) {
        System.out.println("\n\n\n\n");
        
        queue.forEach(System.out::println);
        System.out.println("\n\nAverage turn around time is : " + (avgtt / nums) +
                           "\nAverage waiting time is :  " + (avgwt / nums));
    }
    public abstract void execute();
}
