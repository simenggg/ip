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
                    System.out.println(i + ". " + tasks[i - 1].toString());
                }

            } else if(userInputSplitted[0].equals("mark")){
                int taskIndex = Integer.parseInt(userInputSplitted[1]);
                System.out.println("Nice! I've marked this task as done:");
                tasks[taskIndex - 1].markDone();
                System.out.println(tasks[taskIndex].toString());


            } else if(userInputSplitted[0].equals("unmark")) {
                int taskIndex = Integer.parseInt(userInputSplitted[1]);
                System.out.println("OK, I've marked this task as not done yet");
                tasks[taskIndex - 1].markUndone();
                System.out.println(tasks[taskIndex].toString());

            } else {
                System.out.println("——————————————————————————————————————————————");
                int spaceindex = userInput.indexOf(" ");   //find the index of the first space

                if(userInputSplitted[0].equals("todo")) {
                    String taskDescription = userInput.substring(spaceindex + 1);
                    Task newTask = new Todo(taskDescription);
                    tasks[index++] = newTask;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + newTask.toString());
                    System.out.println("Now you have " + index + " tasks in the list.");

                } else if(userInputSplitted[0].equals("deadline")) {
                    String task = userInput.substring(spaceindex + 1);
                    String[] taskSplitted = task.split("/");
                    String taskDescription = taskSplitted[0];
                    int space = taskSplitted[1].indexOf(" ");
                    String by = taskSplitted[1].substring(space + 1);
                    Task newTask = new Deadline(taskDescription, by);
                    tasks[index++] = newTask;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + newTask.toString());
                    System.out.println("Now you have " + index + " tasks in the list.");

                } else if(userInputSplitted[0].equals("event")) {
                    String task = userInput.substring(spaceindex + 1);
                    String[] taskSplitted = task.split("/");
                    String taskDescription = taskSplitted[0];
                    int space1 = taskSplitted[1].indexOf(" ");
                    String start = taskSplitted[1].substring(space1 + 1);
                    int space2 = taskSplitted[2].indexOf(" ");
                    String end = taskSplitted[2].substring(space2 + 1);
                    Task newTask = new Event(taskDescription, start, end);
                    tasks[index++] = newTask;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + newTask.toString());
                    System.out.println("Now you have " + index + " tasks in the list.");
                }

                System.out.println("——————————————————————————————————————————————");
            }
        }


    }
}
