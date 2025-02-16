package lemon;

public class Parser {
    public static Command parse(String userInput) {
        String[] parts = userInput.split(" ", 2);
        String command = parts[0];

        switch (command) {
        case "list":
            return new Command(CommandType.LIST);
        case "delete":
            return new Command(CommandType.DELETE);
        case "mark":
            return new Command(CommandType.MARK);
        case "unmark":
            return new Command(CommandType.UNMARK);
        case "bye":
            return new Command(CommandType.EXIT);
        case "todo":
        case "deadline":
        case "event":
            return new Command(CommandType.ADD);
        default:
            return new Command(CommandType.UNKNOWN);
        }

    }
}