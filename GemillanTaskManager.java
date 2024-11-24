import java.util.PriorityQueue;
import java.util.Scanner;

public class GemillanTaskManager {

    static class Task implements Comparable<Task> {
        String name;
        int priority;

        Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        @Override
        public int compareTo(Task other) {
            return Integer.compare(this.priority, other.priority);
        }

        @Override
        public String toString() {
            return "Task: " + name + " (Priority: " + priority + ")";
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Task> taskQueue = new PriorityQueue<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Task Manager ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Next Task");
            System.out.println("3. Complete Next Task");
            System.out.println("4. View All Tasks");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter task priority (lower is more urgent): ");
                    int priority = scanner.nextInt();
                    taskQueue.add(new Task(name, priority));
                    System.out.println("Task added.");
                    break;

                case 2:
                    if (taskQueue.isEmpty()) {
                        System.out.println("No tasks in the queue.");
                    } else {
                        System.out.println("Next task: " + taskQueue.peek());
                    }
                    break;

                case 3:
                    if (taskQueue.isEmpty()) {
                        System.out.println("No tasks to complete.");
                    } else {
                        System.out.println("Completed: " + taskQueue.poll());
                    }
                    break;

                case 4:
                    if (taskQueue.isEmpty()) {
                        System.out.println("No tasks in the queue.");
                    } else {
                        System.out.println("All tasks:");
                        for (Task task : taskQueue) {
                            System.out.println(task);
                        }
                    }
                    break;

                case 5:
                    System.out.println("Exiting Task Manager. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }

        }
    }
}
