/**
 * This class deals with operations that involves storage such as
 * loading the tasklist file from memory to the chatbot
 * and overwriting the tasklist file after changes are made through the chatbot
 */

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

    /**
     * Construct a file for storage with a specified file path
     * @param filePath The path of the file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Check if the file for storage of a specified path is present
     * If not present, create a new file of that path for storage of the tasklist
     * If present, load the content of the file to the chatbot
     * @return The arraylist that contains all the tasks in the specified storage file
     */
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
                    String[] split_by_character = line.split("");
                    String[] split_by_part = taskDescription.split("/");
                    if(split_by_part.length == 1) {
                        //to do
                        Todo todoTask = new Todo(taskDescription);
                        if(split_by_character[4].equals("X")) {
                            todoTask.isdone = true;
                        }
                        tasks.add(todoTask);

                    } else if(split_by_part.length == 2) {
                        //deadline
                        //problem: the time representation only apply to deadline tasks and not event tasks, not consistent
                        String description = split_by_part[0];
                        String[] deadlinedate = split_by_part[1].split("-");
                        int year = Integer.parseInt(deadlinedate[0]);
                        int month = Integer.parseInt(deadlinedate[1]);
                        int day = Integer.parseInt(deadlinedate[2]);
                        LocalDate date = LocalDate.of(year, month, day);
                        Deadline deadlineTask = new Deadline(description, date);
                        //Deadline deadlineTask = new Deadline(description, by);
                        if(split_by_character[4].equals("X")) {
                            deadlineTask.isdone = true;
                        }
                        tasks.add(deadlineTask);

                    } else if(split_by_part.length == 3) {
                        //event
                        String description = split_by_part[0];
                        String start = split_by_part[1];
                        String end = split_by_part[2];
                        Event eventTask = new Event(description, start, end);
                        if(split_by_character[4].equals("X")) {
                            eventTask.isdone = true;
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


    /**
     * Write the tasklist to the specified storage file
     * @param tasks The arraylist that contains all the tasks from user input
     */

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
