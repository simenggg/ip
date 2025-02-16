/**
 * Represents a subclass of the Task class, a task to be complete
 * The description describes what the Todo task is about
 */

package lemon;

public class Todo extends Task {

    /**
     * Construct a todo task
     * @param description The description of the todo task
     */

    public Todo(String description) {
        super(description);
    }

    public static String checkValidity(String description) {
        if (description == null || description.split(" ").length == 1) {
            throw new IllegalArgumentException("Todo description cannot be empty.");
        }
        return description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
