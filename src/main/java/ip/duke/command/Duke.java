package ip.duke.command;

import ip.duke.task.Deadline;
import ip.duke.task.Event;
import ip.duke.task.Task;
import ip.duke.task.Todo;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        printGreeting();

        Task[] list = new Task[100];
        int index = 0;
        int slashPosition = 0;
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        while (!command.equals("bye")) {
            try {
                if (command.equals("list")) {
                    printTasks(list, index);
                } else if (command.startsWith("todo")) {
                    if (command.length() <= 5) {
                        throw new DukeException("todo");
                    }
                    recordTasks(list, index, command, "todo");
                    index++;
                } else {
                    int Position = Integer.parseInt(String.valueOf(command.indexOf('/')));
                    if (command.startsWith("deadline")) {
                        if (command.length() <= 9) {
                            throw new DukeException("deadline");
                        }
                        slashPosition = Position;
                        if (slashPosition != -1) {
                            recordTasks(list, index, command, "deadline");
                            index++;
                        } else {
                            printLine();
                            System.out.println("🙁 OOPS!!! The deadline time is missing.");
                            System.out.println("Please fill in the time! :)");
                            printLine();
                        }
                    } else if (command.startsWith("event")) {
                        if (command.length() <= 6) {
                            throw new DukeException("event");
                        }
                        slashPosition = Position;
                        if (slashPosition != -1) {
                            recordTasks(list, index, command, "event");
                            index++;
                        } else {
                            printLine();
                            System.out.println("🙁 OOPS!!! The event time is missing.");
                            System.out.println("Please fill in the time! :)");
                        }
                    } else if (command.startsWith("done")) {
                        if (command.length() <= 5) {
                            throw new DukeException("done");
                        }
                        markDone(list, command);
                    } else {
                        printLine();
                        System.out.println("🙁 OOPS!!! I'm sorry, but I don't know what that means :-(");
                        System.out.println("Please input again! :)");
                        printLine();
                    }
                }
            } catch (DukeException e) {
                printLine();
                if (e.category.equals("event")) {
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                } else {
                    System.out.println("☹ OOPS!!! The description of a " + e.category + " cannot be empty.");
                }
                System.out.println("Please complete the description! :)");
                printLine();
            }
            command = in.nextLine();

        }
        printBye();
    }

    public static void printGreeting() {
        System.out.println("Hello! I'm ip.taskmaster.Duke");
        System.out.println("What can I do for you?\n");
        printLine();
    }

    public static void recordTasks(Task[] list, int index, String command, String category) {
        printLine();
        System.out.println("Got it. I've added this task:");
        if (category.equals("todo")) {
            list[index] = new Todo(command.substring(5));
        } else {
            String Time = command.substring(command.indexOf("/") + 4);
            if (category.equals("deadline")) {
                String content = command.substring(9, command.indexOf("/"));
                list[index] = new Deadline(content, Time);
            } else if (category.equals("event")) {
                String content = command.substring(6, command.indexOf("/"));
                list[index] = new Event(content, Time);
            }
        }
        System.out.println(list[index].toString());
        int count = index + 1;
        System.out.println("Now you have " + count + " tasks in the list.");
        printLine();
    }

    public static void markDone(Task[] list, String command) {
        int i;
        i = Integer.parseInt(command.substring(5));
        list[i - 1].setDone(true);
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + " " + list[i - 1].toString());
        printLine();

    }

    public static void printTasks(Task[] list, int index) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= index; i++) {
            System.out.println(i + "." + list[i - 1].toString());
        }
        printLine();
    }

    public static void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine() {
        System.out.println("-----------------------------------------");
    }
}

