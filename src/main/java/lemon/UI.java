package lemon;

import java.util.Scanner;

public class UI {
    private Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    public void displayWelcomeMessage() {
        String logo =
                "  __     _____  ___      ___   ____   __    __\n"
                + "| |   |  __ | ||\\    //|| //    \\ ||\\  ||\n"
                + "| |   | |___  || \\  // || ||    || || \\ ||\n"
                + "| |__ | |___| ||  \\//  || ||    || ||  \\||\n"
                + "|____||__,__| ||   __   ||  \\__//  ||   \\|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Lemon, your task manager!");
        System.out.println("What can I do for you?");

    }

    public void displayExitMessage() {

        System.out.println("Bye! Have a nice day and hope to see you again soon!");

    }

    public void displayConfusionMessage() {
        System.out.println("Ohh sorry I don't understand this...");
    }

    public String getInput() {
        return scanner.nextLine();
    }



}