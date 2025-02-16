package lemon;

public class Todo extends Task {

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
