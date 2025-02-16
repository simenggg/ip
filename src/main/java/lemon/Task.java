/**
 * Represents a task
 * The description argument describes what the task is about and
 * the isDone argument describes the status of the task, whether it has been done or not
 */

package lemon;

public class Task {
    protected String description;
    protected boolean isdone;

    /**
     * Construct a task with a specified description
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isdone = false;
    }

    public boolean getStatus() {
        return isdone;
    }

    public void markDone() {
        isdone = true;
    }

    public void markUndone() {
        isdone = false;
    }

    public String toString() {
        if(isdone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

}
