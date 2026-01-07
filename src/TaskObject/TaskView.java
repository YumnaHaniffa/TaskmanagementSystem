package TaskObject;

import java.util.Scanner;
//To display task details.
public class TaskView {

    public void displayTaskDetails(Task task) {
        System.out.println("The Task Title is: " + task.getTaskTitle() );
        System.out.println("The Task Priority is: " +task.getTaskPriority() );
        System.out.println("The Task is completed : " +task.isCompleted() +"\n");
    }

    private void displayTasksByPriority(Task[] tasks, String priority) {
        boolean found = false;
        for (Task task : tasks) {
            String taskPriority = task.getTaskPriority();
            if (taskPriority.equalsIgnoreCase(priority)) {
                displayTaskDetails(task);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No priority tasks to display");
        }
    }

    public void displayTasks(Task[] tasks, Scanner input) {

        boolean go = true;
        while (go) {
            System.out.println("View Task By Priority\n");
            System.out.println("[1]High Priority");
            System.out.println("[2]Medium Priority");
            System.out.println("[3]Low Priority");
            System.out.println("[4]Exit");

            if (!input.hasNextInt()) {
                System.out.println("Invalid Input. Please try again");
                input.nextLine();
                continue;

            }
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    displayTasksByPriority(tasks, "high");
                    break;
                case 2:
                    displayTasksByPriority(tasks, "medium");
                    break;

                case 3:
                    displayTasksByPriority(tasks, "low");
                    break;

                case 4:
                    go = false;
                    break;
            }

        }
    }

}
