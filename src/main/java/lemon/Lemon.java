/**
 * This is the main entry point for the chatbot Lemon, which can help manage your tasks
 * This class creates the chatbot, processes ths user inputs and manage interactions
 */

package lemon;

import java.time.LocalDate;


public class Lemon {
    private final UI ui;
    private final Storage storage;
    private final Tasklist tasklist;

    public Lemon(String filepath) {
        this.storage = new Storage(filepath);
        this.ui = new UI();
        this.tasklist = new Tasklist(storage.loadTasks());
    }


    public String getResponse(String input) {
        Command command = Parser.parse(input);
        int inputLength = input.split(" ").length;
        String message = "";
        switch (command.getType()) {
        case LIST:
            if(inputLength > 1) {
                message = ui.displayWrongFormatMessage();
            } else {
                message = tasklist.listTask();
            }
            break;

        case EXIT:
            if(inputLength > 1) {
                message = ui.displayWrongFormatMessage();
            } else {
                storage.storeTasks(tasklist.getTasks());
                message = ui.displayExitMessage();
            }
            break;

        case MARK:
            if(inputLength != 2) {
                message = ui.displayWrongFormatMessage();
            } else {
                String[] parts1 = input.split(" ");
                try {
                    int index = Integer.parseInt(parts1[1]);
                    message = tasklist.markTask(index);
                } catch (NumberFormatException e) {
                    message = ui.displayWrongFormatMessage();
                }
            }
            break;

        case UNMARK:
            if(inputLength != 2) {
                message = ui.displayWrongFormatMessage();
            } else {
                String[] parts2 = input.split(" ");
                try {
                    int index = Integer.parseInt(parts2[1]);
                    message = tasklist.unmarkTask(index);
                } catch (NumberFormatException e) {
                    message = ui.displayWrongFormatMessage();
                }
            }
            break;

        case DELETE:
            if(inputLength != 2) {
                message = ui.displayWrongFormatMessage();
            } else {
                String[] parts3 = input.split(" ");
                try {
                    int index = Integer.parseInt(parts3[1]);
                    message = tasklist.deleteTask(index);
                } catch (NumberFormatException e) {
                    message = ui.displayWrongFormatMessage();
                }
            }
            break;

        case FIND:
            if(inputLength < 2) {
                message = ui.displayWrongFormatMessage();
            } else {
                String[] parts4 = input.split(" ");
                message = tasklist.findTask(parts4[1]);
            }
            break;
        case HELP:
            message = Help.getHelpPage();
            break;
        case HELP_TECH:
            message = Help.getTechHelp();
            break;
        case HELP_EMO:
            message = Help.getEmoHelp();
            break;
        case ADD:
            message = handleAddingTask(input);
            break;
        default:
            message = ui.displayConfusionMessage();
            break;
        }
        return message;
    }

    public String handleAddingTask(String input) {
        String addMessage = "";
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            addMessage = "Sorry! The description of a task cannot be empty!";
        } else {
            if (parts[0].equals("todo")) {
                Todo newTask = new Todo(parts[1]);
                addMessage = tasklist.addTask(newTask);
            } else if (parts[0].equals("deadline")) {
                String[] details = parts[1].split("/by ");
                if(details.length < 2) {
                    addMessage = "Sorry! The description of deadline task is not correct!";
                } else {
                    Deadline newTask = new Deadline(details[0], LocalDate.parse(details[1]));
                    addMessage = tasklist.addTask(newTask);
                }
            } else if (parts[0].equals("event")) {
                String[] details = parts[1].split("/from | /to ");
                if(details.length < 3) {
                    addMessage = "Sorry! The description of event task is not correct!";
                } else {
                    Event newTask = new Event(details[0], details[1], details[2]);
                    addMessage = tasklist.addTask(newTask);
                }
            }
        }
        return addMessage;
    }

    public String run() {
        return ui.displayWelcomeMessage();
    }
    
    public static void main(String[] args) {
        new Lemon("tasklist.txt").run();
    }
}




