package lemon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;


public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<lemon.Task>();
        File file = new File(filePath);

        if (!file.exists()) {
            //check if a tasklist file is present, and create one if it is not present
            System.out.println("You don't have a file to store tasks yet. Let me create one for you!");
            try {
                if (file.createNewFile()) {
                    System.out.println("You have created a new tasklist file!");
                } else {
                    System.out.println("Tasklist file could not be created");
                }
            } catch (IOException e) {
                System.out.println("Error creating tasklist file" + e.getMessage());
            }
            return tasks;

        } else {
            //load the content of the tasklist file into the Tasks arraylist
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();

                    String taskDescription = line.substring(6);
                    //need to create specific type of task instead of task in general
                    //but have a lot ot repetitive code...
                    String[] splitByCharacter = line.split("");
                    String[] splitByPart = taskDescription.split("/");
                    if(splitByPart.length == 1) {
                        //to do
                        Todo todoTask = new Todo(taskDescription);
                        if(splitByCharacter[4].equals("X")) {
                            todoTask.isDone = true;
                        }
                        tasks.add(todoTask);

                    } else if(splitByPart.length == 2) {
                        //deadline
                        //problem: the time representation only apply to deadline tasks and not event tasks, not consistent
                        String description = splitByPart[0];
                        String[] deadlineDate = splitByPart[1].split("-");
                        int year = Integer.parseInt(deadlineDate[0]);
                        int month = Integer.parseInt(deadlineDate[1]);
                        int day = Integer.parseInt(deadlineDate[2]);
                        LocalDate date = LocalDate.of(year, month, day);
                        Deadline deadlineTask = new Deadline(description, date);
                        //Deadline deadlineTask = new Deadline(description, by);
                        if(splitByCharacter[4].equals("X")) {
                            deadlineTask.isDone = true;
                        }
                        tasks.add(deadlineTask);

                    } else if(splitByPart.length == 3) {
                        //event
                        String description = splitByPart[0];
                        String start = splitByPart[1];
                        String end = splitByPart[2];
                        Event eventTask = new Event(description, start, end);
                        if(splitByCharacter[4].equals("X")) {
                            eventTask.isDone = true;
                        }
                        tasks.add(eventTask);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Tasklist could not be found");
            }
            return tasks;
        }

    }

    public void storeTasks(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter("tasklist.txt")) {
            for(Task task: tasks) {
                String taskString = task.toString();
                writer.write(taskString + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file" + e.getMessage());
        }
    }

}
