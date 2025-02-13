package lemon;

public class Task {
    protected String description;
    protected boolean isdone;

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
