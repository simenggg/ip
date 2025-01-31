import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

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


        File tasklist = new File("tasklist.txt");

        if(!tasklist.exists()) {
            //check if a tasklist file is present, and create one if it is not present
            System.out.println("You don't have a tasklist yet. Let me create one for you!");
            try {
                if(tasklist.createNewFile()) {
                    System.out.println("You have created a new tasklist file!");
                } else {
                    System.out.println("Tasklist could not be created");
                }
            } catch (IOException e) {
                System.out.println("Error creating tasklist file" + e.getMessage());
            }

        } else {
            //load the content of the tasklist
            try {
                Scanner scanner2 = new Scanner(tasklist);
                while(scanner2.hasNextLine()) {
                    System.out.println(scanner2.nextLine());
                }
            } catch (FileNotFoundException e) {
                    System.out.println("Tasklist could not be found");

            }
        }

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
                    if(tasks[i - 1] != null) {
                        System.out.println(i + ". " + tasks[i - 1].toString());
                    }
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


            } else if(userInputSplitted[0].equals("delete")) {
                int taskIndex = Integer.parseInt(userInputSplitted[1]);
                System.out.println("Noted. I've removed this task:");
                System.out.println(tasks[taskIndex - 1].toString());
                tasks[taskIndex - 1] = null;
                //problem: there will be empty holes in the array, need to move the tasks to fill in the hole
                for(int i = taskIndex - 1; i < index + 1; i++) {
                    Task temp = tasks[i + 1];
                    tasks[i] = temp;
                    tasks[i + 1] = null;
                }
                index--;
                System.out.println("Now you have " + index + " tasks in the list.");


            } else {
                System.out.println("——————————————————————————————————————————————");
                int spaceindex = userInput.indexOf(" ");   //find the index of the first space

                if(userInputSplitted[0].equals("todo")) {
                    String taskDescription = userInput.substring(spaceindex + 1);
                    if(userInputSplitted.length < 2) {
                        System.out.println("OOPS the description of todo is not correct!");
                    } else {
                        Task newTask = new Todo(taskDescription);

                        //add a task to the tasklist
                        try {
                            FileWriter writer = new FileWriter(newTask.toString(),true);
                            writer.close();
                            System.out.println("Got it. I've added this task:");
                            System.out.println(" " + newTask.toString());
                        } catch(IOException e) {
                            System.out.println("Error writing to file" + e.getMessage());
                        }
                        //tasks[index++] = newTask;

                        if(index == 1) {
                            System.out.println("Now you have 1 task in the list.");
                        } else {
                            System.out.println("Now you have " + index + " tasks in the list.");
                        }

                    }

                } else if(userInputSplitted[0].equals("deadline")) {
                    String task = userInput.substring(spaceindex + 1);
                    String[] taskSplitted = task.split("/");
                    String taskDescription = taskSplitted[0];
                    if(taskSplitted.length != 2 ) {
                        System.out.println("OOPS the description of deadline is not correct!");
                    } else {
                        int space = taskSplitted[1].indexOf(" ");
                        String by = taskSplitted[1].substring(space + 1);
                        Task newTask = new Deadline(taskDescription, by);
                        tasks[index++] = newTask;
                        System.out.println("Got it. I've added this task:");
                        System.out.println(" " + newTask.toString());
                        System.out.println("Now you have " + index + " tasks in the list.");

                    }

                } else if(userInputSplitted[0].equals("event")) {
                    String task = userInput.substring(spaceindex + 1);
                    String[] taskSplitted = task.split("/");
                    String taskDescription = taskSplitted[0];

                    if(taskSplitted.length != 3) {
                        System.out.println("OOPS the description of event is not correct!");
                    } else {
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

                } else {
                    System.out.println("Ohh sorry I don't understand this...");
                }

                System.out.println("——————————————————————————————————————————————");
            }
        }


    }

    public void addTask(Task t, int i) {
        //add a task to the tasklist
        try {
            FileWriter writer = new FileWriter(t.toString(),true);
            writer.close();
            System.out.println("Got it. I've added this task:");
            System.out.println(" " + t.toString());
        } catch(IOException e) {
            System.out.println("Error writing to file" + e.getMessage());
        }
        //tasks[index++] = newTask;

        if(i == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + i + " tasks in the list.");
        }
    }

}
