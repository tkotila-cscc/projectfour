package cscc.tkotila;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in).useDelimiter("\n");

    public static void main(String[] args) {
        printChoices();
        TodoItemIterator tasks = new TodoItemIterator();
        String choice = SCANNER.next();

        while (!Objects.equals(choice, "0")) {
            switch (choice) {
                case "1" -> addTask(tasks);
                case "2" -> removeTask(tasks);
                case "3" -> editTask(tasks);
                case "4" -> listTasks(tasks);
                case "5" -> saveTasks(tasks);
                case "6" -> loadTasks(tasks);
                default -> System.out.println("Invalid option.");
            }
            printChoices();
            choice = SCANNER.next();
        }
    }

    private static void addTask(TodoItemIterator tasks) {
        System.out.println("What should the title be?");
        String title = SCANNER.next();
        System.out.println("What should the description be?");
        String description = SCANNER.next();
        System.out.println("What should the priority be?");
        int priority = queryInt();
        tasks.add(new TodoItem(title, description, priority));
    }

    private static void removeTask(TodoItemIterator tasks) {
        System.out.println("What is the title of the task you wish to delete?");
        String title = SCANNER.next();

        if (tasks.getTask(title).isEmpty()) {
            System.out.println("A task with that title doesn't exist.");
            return;
        }

        tasks.deleteByTitle(title);
    }

    private static void editTask(TodoItemIterator tasks) {
        System.out.println("What is the title of the task you wish to edit?");
        String title = SCANNER.next();
        Optional<TodoItem> item = tasks.getTask(title);

        if (item.isEmpty()) {
            System.out.println("A task with that title doesn't exist.");
            return;
        }

        System.out.println("What should the new title be?");
        item.get().setTitle(SCANNER.next());
        System.out.println("What should the new description be?");
        item.get().setDescription(SCANNER.next());
        System.out.println("What should the new priority be?");
        item.get().setPriority(queryInt());
    }

    private static void listTasks(TodoItemIterator tasks) {
        if (tasks.size() == 0) {
            System.out.println("There are currently no tasks in the collection.");
            return;
        }

        for (TodoItem todoItem: tasks) {
            System.out.println(todoItem);
        }
    }

    private static void saveTasks(TodoItemIterator tasks) {
        System.out.println("What should the name of the file be?");
        tasks.saveTasks(SCANNER.next());
    }

    private static void loadTasks(TodoItemIterator tasks) {
        System.out.println("What is the name of the file?");
        tasks.loadTasks(SCANNER.next());
    }

    private static int queryInt() {
        while (true) {
            try {
                return SCANNER.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    private static void printChoices() {
        System.out.println("(0) Exit.\n(1) Add a task.\n(2) Remove a task.\n(3) Edit a task.\n(4) List all tasks.\n(5) Save tasks to file.\n(6) Load tasks from file.");
    }
}
