import java.util.Scanner;

public class Lemon {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);

        System.out.println("——————————————————————————————————————————————");
        System.out.println("Hello! I'm Lemon");
        System.out.println("What can I do for you?");
        System.out.println("——————————————————————————————————————————————");

        while(true) {
            String userInput = scanner.nextLine();
            if(userInput.equals("bye")) {
                System.out.println("——————————————————————————————————————————————");
                System.out.println("Bye! Have a nice day and hope to see you again soon!");
                System.out.println("——————————————————————————————————————————————");
                break;
            } else {
                System.out.println("——————————————————————————————————————————————");
                System.out.println(userInput);
                System.out.println("——————————————————————————————————————————————");
            }
        }




    }
}
