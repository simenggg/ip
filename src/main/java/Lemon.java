import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.io.File;


public class Lemon {
    private UI ui;
    private Storage storage;
    private Tasklist tasklist;

    public Lemon(String filepath) {
        this.storage = new Storage(filepath);
        this.ui = new UI();
        this.tasklist = new Tasklist(storage.loadTasks());
    }

    public void run() {
        ui.displayWelcomeMessage();

        while (true) {
            String input = ui.getInput();
            Command command = Parser.parse(input);

            switch (command.getType()) {
                case LIST:
                    tasklist.listTask();
                    break;
                case EXIT:
                    storage.storeTasks(tasklist.getTasks());
                    ui.displayExitMessage();
                    return;
                case MARK:
                    String[] parts1 = input.split(" ");
                    tasklist.markTask(Integer.parseInt(parts1[1]));
                    break;
                case UNMARK:
                    String[] parts2 = input.split(" ");
                    tasklist.unmarkTask(Integer.parseInt(parts2[1]));
                    break;
                case DELETE:
                    String[] parts3 = input.split(" ");
                    tasklist.deleteTask(Integer.parseInt(parts3[1]));
                    break;
                case ADD:
                    String[] parts = input.split(" ", 2);
                    if (parts[0].equals("todo")) {
                        Todo newTask = new Todo(parts[0]);
                        tasklist.addTask(newTask);
                    } else if (parts[0].equals("deadline")) {
                        //some problems with creating deadline task
                        String[] details = parts[1].split("/by ");
                        Deadline newTask = new Deadline(details[0], LocalDate.parse(details[1]));
                        tasklist.addTask(newTask);
                    } else if (parts[0].equals("event")) {
                        String[] details = parts[1].split("/from | /to ");
                        Event newTask = new Event(details[0], details[1], details[2]);
                        tasklist.addTask(newTask);
                    }
                    break;
                default:
                    ui.displayConfusionMessage();
            }
        }
    }

    public static void main(String[] args) {
        new Lemon("tasklist.txt").run();
    }
}




