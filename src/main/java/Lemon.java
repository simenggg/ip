import java.util.Scanner;

public class Lemon {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("——————————————————————————————————————————————");
        System.out.println("Hello! I'm Lemon");
        System.out.println("What can I do for you?");
        System.out.println("——————————————————————————————————————————————");

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int index = 0;

        while(true) {
            String userInput = scanner.nextLine();
            String[] userInputSplitted = userInput.split(" ");

            if(userInput.equals("bye")) {
                System.out.println("——————————————————————————————————————————————");
                System.out.println("Bye! Have a nice day and hope to see you again soon!");
                System.out.println("——————————————————————————————————————————————");
                break;

            } else if(userInput.equals("list")) {
                for (int i = 1; i < index + 1; i++) {
                    if(tasks[i - 1].isdone) {
                        System.out.println(i + ".[X] " + tasks[i - 1].description);
                    } else {
                        System.out.println(i + ". [ ]" + tasks[i - 1].description);
                    }
                }

            } else if(userInputSplitted[0].equals("mark")){
                int taskIndex = Integer.parseInt(userInputSplitted[1]);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" [X] " + tasks[taskIndex - 1].description);
                tasks[taskIndex - 1].markDone();

            } else if(userInputSplitted[0].equals("unmark")) {
                int taskIndex = Integer.parseInt(userInputSplitted[1]);
                System.out.println("OK, I've marked this task as not done yet");
                System.out.println(" [ ] " + tasks[taskIndex - 1].description);
                tasks[taskIndex - 1].markUndone();

            } else {
                System.out.println("——————————————————————————————————————————————");
                Task newTask = new Task(userInput);
                tasks[index++] = newTask;
                System.out.println("added: " + userInput);
                System.out.println("——————————————————————————————————————————————");
            }
        }


    }
}
