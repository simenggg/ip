/**
 * Represents the list that contains all tasks the user has put
 * The tasklist is represented by an arraylist
 * and various operations can be done to modify the tasklist
 */

package lemon;

import java.util.ArrayList;

public class Tasklist {
    private final ArrayList<Task> tasks;

    /**
     * Construct a tasklist with a specified arraylist of tasks
     * @param tasks The arraylist of tasks
     */
    public Tasklist(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    /**
     * Add a task to the tasklist
     * @param task The task to be added
     */
    public void addTask(Task task) {
        //do not need to split into three categories, will handle in the main class
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task.toString());
        if (tasks.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }


    /**
     * Delete a task from the tasklist
     * @param index The index of the task in the tasklist to be deleted
     */
    public void deleteTask(int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index - 1).toString());
        tasks.remove(index - 1);
        System.out.println("Now you have " + index + " tasks in the list.");
    }


    /**
     * Mark a task as done
     * @param index The index of the task in the tasklist that is to be marked as done
     */
    public void markTask(int index) {
        System.out.println("Nice! I've marked this task as done:");
        tasks.get(index - 1).markDone();
        System.out.println(tasks.get(index - 1).toString());

    }


    /**
     * Mark a task as not done
     * @param index The index of the task in the tasklist that is to be marked as not done
     */
    public void unmarkTask(int index) {
        System.out.println("OK, I've marked this task as not done yet");
        tasks.get(index - 1).markUndone();
        System.out.println(tasks.get(index - 1).toString());
    }

    public void listTask() {
        if(tasks.isEmpty()) {
            System.out.println("There are no tasks in the list yet!");
        } else {
            for(Task task: tasks) {
                System.out.println(task);
            }
        }

    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

}