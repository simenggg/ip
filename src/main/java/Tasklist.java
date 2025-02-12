import java.util.ArrayList;

public class Tasklist {
    private ArrayList<Task> tasks;

    public Tasklist(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

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

    public void deleteTask(int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index - 1).toString());
        tasks.remove(index - 1);
        //problem: there will be empty holes in the array, need to move the tasks to fill in the hole
        /*for (int i = index - 1; i < tasks.size(); i++) {
            Task temp = tasks.get(i + 1);
            tasks.set(i, temp);
            tasks.set(i + 1, null);
        } */
        System.out.println("Now you have " + index + " tasks in the list.");
    }

    public void markTask(int index) {
        System.out.println("Nice! I've marked this task as done:");
        tasks.get(index - 1).markDone();
        System.out.println(tasks.get(index - 1).toString());

    }

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