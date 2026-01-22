import java.util.*;

class Task {
    private String title;
    private boolean isCompleted;

    public Task(String title) {
        this.title = title;
        this.isCompleted = false;
    }

    public void markComplete() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        return title + " [" + (isCompleted ? "Done" : "Pending") + "]";
    }
}

public class TaskManager {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void addTask() {
        System.out.println("Enter the title: ");
        String title = sc.nextLine();
        Task t = new Task(title);
        tasks.add(t);
        System.out.println("Task Added Successfully");
    }

    public static void listTask() {
        if (tasks.isEmpty()) {
            System.out.println("No Task Found");
        } else {
            System.out.println("List of Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public static void markTask() {
        listTask();
        if (!tasks.isEmpty()) {
            System.out.println("Enter task number to mark as complete:");
            int index = sc.nextInt() - 1;
            sc.nextLine();
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).markComplete();
                System.out.println("Task marked as completed.");
            } else {
                System.out.println("Invalid Task Number.");
            }
        }
    }

    public static void deleteTask() {
        listTask();
        if (!tasks.isEmpty()) {
            System.out.println("Enter task number to delete:");
            int index = sc.nextInt() - 1;
            sc.nextLine(); 
            if (index >= 0 && index < tasks.size()) {
                tasks.remove(index);
                System.out.println("Task Deleted Successfully.");
            } else {
                System.out.println("Invalid Task Number.");
            }
        }
    }

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\nTask Manager");
            System.out.println("1. Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            try {
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); 
                switch (choice) {
                    case 1 -> addTask();
                    case 2 -> listTask();
                    case 3 -> markTask();
                    case 4 -> deleteTask();
                    case 5 -> {
                        System.out.println("Exited");
                        running = false;
                    }
                    default -> System.out.println("Invalid input");
                }
            } catch (Exception e) {
                System.out.println("Enter an integer input.");
                sc.nextLine(); 
            }
        }
    }
}
