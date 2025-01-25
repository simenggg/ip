import java.util.Scanner;

public class Lemon {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("——————————————————————————————————————————————");
        System.out.println("Hello! I'm Lemon");
        System.out.println("What can I do for you?");
        System.out.println("——————————————————————————————————————————————");

        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int index = 0;

        while(true) {
            String userInput = scanner.nextLine();
            if(userInput.equals("bye")) {
                System.out.println("——————————————————————————————————————————————");
                System.out.println("Bye! Have a nice day and hope to see you again soon!");
                System.out.println("——————————————————————————————————————————————");
                break;
            } else if(userInput.equals("list")) {
                for(int i = 1; i < index + 1; i++) {
                    System.out.println(i + ". " + tasks[i - 1]);
                }
            } else {
                System.out.println("——————————————————————————————————————————————");
                tasks[index++] = userInput;
                System.out.println("added: " + userInput);
                System.out.println("——————————————————————————————————————————————");
            }
        }




    }
}
