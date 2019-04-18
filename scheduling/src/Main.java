// package com.nimbalkar.chetan;
import java.util.Scanner;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	private static Scheduling scheduling;
	public static void main(String[] args) {
		boolean quit = false;
		while(!quit) {
			switch(getChoice()) {
				case 0:
				        scheduling = new FCFS();
                        break;
				case 1:
				        scheduling = new SJF();
				        break;
				case 2:
				        scheduling = new RoundRobin();
				        break;
				case 3:
				        scheduling = new Priority();
				        break;
			}

			scheduling.execute();
            System.out.println("continue [y/n] ? ");
            quit = scanner.nextLine().charAt(0) == 'n' ? true : false;
		}
	}

	public static int getChoice() {
		int choice = 0;
		System.out.println("[0] FCFS\n" +
                           "[1] SJF\n" +
                           "[2] Round Robin\n" +
                           "[3] Priority\n\n" +
                           "please enter your choice : ");
		choice = scanner.nextInt();
		scanner.nextLine();
		return choice;
	}
}


/*

*/