package TaskObject;
import CustomLinkedList.*;
import CustomMap.MapOperator;
import java.util.List;
import java.util.Scanner;
//perform task management operations
public  class TaskOperations {

    LinkedListOperator listOperator = new LinkedListOperator();
    CustomQueue highPriorityQueue = new CustomQueue();
    CustomQueue mediumPriorityQueue = new CustomQueue();
    CustomQueue lowPriorityQueue = new CustomQueue();


    //1.add task
    public Task addTask(Scanner input, LinkedList taskList) {


        int taskId;
        while (true) {
            System.out.println("Enter task ID");
            if (input.hasNextInt()) {
                taskId = input.nextInt();
                input.nextLine();
                if (taskId <= 0) {
                    System.out.println("Invalid task ID, task Id must be an positive integer");
                } else if (taskList.containsTaskById(taskId)) {
                    System.out.println("Task ID already exists, please try again");
                } else {
                    break;
                }
            } else {
                System.out.println("Invalid Input. Please try again");
                input.nextLine();
            }

        }
        //Prompt for Task Title
        String taskTitle;
        while (true) {
            System.out.println("Enter Task Title");
            taskTitle = input.nextLine().trim();
            if (taskTitle.isEmpty()) {
                System.out.println("Task title cannot be empty");
            } else {
                break;
            }

        }
        //Prompt for Task Priority level

        String taskPriority;
        while (true) {
            System.out.println("Enter Task Priority(high/medium/low)");
            taskPriority = input.nextLine();
            if (taskPriority.equalsIgnoreCase("high") || taskPriority.equalsIgnoreCase("medium") || taskPriority.equalsIgnoreCase("low")) {
                break;
            } else {
                System.out.println("Invalid Priority. Please enter a suitable priority");
            }
        }

        Task task = new Task(taskId, taskTitle, taskPriority, false);

        System.out.println("Does this tasks have any dependencies?(yes/no):");
        String hasDependencies = input.nextLine();

        if (hasDependencies.equalsIgnoreCase("yes")) {
            System.out.println("Enter dependency task ID(comma-seperated, or 'none'):");
            String dependencyTask = input.nextLine(); //the task the current task in depended on
            System.out.println("Dependency added : "+"Task "+task.getTaskId() +" depends on " +dependencyTask);

            if (!dependencyTask.equalsIgnoreCase("none")) {
                String[] dependencyIds = dependencyTask.split(",");
                for (String id : dependencyIds) {
                    try {
                        int dependencyId = Integer.parseInt(id.trim());//trim whitespace
                        if (taskList.getTaskById(dependencyId) != null) {
                            task.addDependency(dependencyId);
                        } else {
                            System.out.println("Invalid dependency ID. Please try again");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid dependency Id format. Please try again");
                    }
                }
            }
        }

        System.out.println("\nTask Added");

        return task;

    }

    private void resolveDependencies(Task task, LinkedList taskList) {
        List<Integer> dependencies = task.getDependencies();
        for (int dependencyId : dependencies) {
            Task dependency = taskList.getTaskById(dependencyId); //check if id exist
            if (dependency == null || !dependency.isCompleted()) {
                resolveDependencies(dependency, taskList);
                dependency.markCompleted();
                System.out.println("\n The dependency with task id "+dependencyId+" has been marked completed");
            }
        }
    }

    //Complete dependency
    private boolean areDependenciesCompleted(Task task, LinkedList taskList) {
        List<Integer> dependencies = task.getDependencies();
        if (dependencies.isEmpty()) {
            return true; // No dependencies, so it's ready
        }
        for (int dependencyId : dependencies) {
            Task dependency = taskList.getTaskById(dependencyId);
            if (dependency == null || !dependency.isCompleted()) {

                return false; // Dependency not found or not completed
            }
        }
        return true; // All dependencies are completed
    }


    //adding tasks to linkedList, stack, map, sorted manner
    public void processTask(LinkedList newTaskList, customStack stack, Task task, MapOperator map) {

        stack.push(task);
        map.addTaskToMap(task);
        newTaskList.addTaskSorted(task, listOperator);


    }

    public void addTaskToQueues(Task task) {
        String priority = task.getTaskPriority();
        if (priority.equalsIgnoreCase("high")) {
            highPriorityQueue.enqueue(task);

        } else if (priority.equalsIgnoreCase("medium")) {
            mediumPriorityQueue.enqueue(task);

        } else if (priority.equalsIgnoreCase("low")) {
            lowPriorityQueue.enqueue(task);

        }

    }

    //1. Deleting Task
    public boolean deleteTask(Scanner input, LinkedList taskList) {


        int deleteTaskId;

        while (true) {
            System.out.print("\nDo you want to:\n");
            System.out.println("[1]Delete one task");
            System.out.println("[2]Deleting all the tasks");
            System.out.println("[3]Exit");
            String deleteTaskInput = input.nextLine();


            if (deleteTaskInput.equalsIgnoreCase("1")) {
                while (true) {
                    try {
                        System.out.println("Enter the task Id to be deleted or (0 to cancel)");
                        deleteTaskId = input.nextInt();
                        input.nextLine();

                        if (deleteTaskId == 0) {
                            break;
                        }

                        System.out.println("Attempting to delete task with ID:" + deleteTaskId);

                        if (taskList.deleteTaskById(deleteTaskId)) {
                            System.out.println("Task deleted successfully");
                            if (taskList != null && taskList.getSize() > 0) {
                                System.out.println("Tasks remaining after deletion\n" + taskList);
                            } else {
                                System.out.println("Task list is now empty\n");
                            }
                            return true;
                        } else {
                            System.out.println("Task could not be deleted as it is not found");
                        }
                    } catch (Exception e) {
                        System.out.println("Input invalid please inter a integer");
                        input.nextLine();
                    }
                }
                return false;

            } else if (deleteTaskInput.equalsIgnoreCase("2")) {
                taskList.deleteAllTasks(taskList);
                return true;

            } else if (deleteTaskInput.equalsIgnoreCase("3")) {
                return false;

            } else {
                System.out.println("Invalid Input. Please try again");
            }

        }

    }

    //5.change priority,mark completion
    public Task taskModification(Scanner input, LinkedList taskList) {
        Task task = null;
        int taskId;

        System.out.println("====Task Updating===");
        boolean run = true;
        while (run) {

            System.out.println("\nSelect your choice\n");
            System.out.println("[1]Change Task Priority");
            System.out.println("[2]Marking Task Completion Status");
            System.out.println("[3]Exit");

            if (!input.hasNextInt()) {
                System.out.println("Invalid Input. Please try again");
                input.nextLine();
                continue;

            }
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter task ID");
                    if (!input.hasNextInt()) {
                        System.out.println("Invalid task Id. Please try again");
                        input.nextLine();
                        break;
                    }
                    taskId = input.nextInt();
                    input.nextLine();

                    task = taskList.getTaskById(taskId);

                    if (task == null) {
                        System.out.println("Invalid task Id. Please try again");
                        break;
                    }

                    System.out.print("Increase or decrease priority? (increase/decrease): ");
                    String priorityChange = input.nextLine();
                    if (priorityChange.equalsIgnoreCase("increase")) {
                        increaseTaskPriority(task, input);


                    } else if (priorityChange.equalsIgnoreCase("decrease")) {
                        decreaseTaskPriority(task, input);
                    }
                    System.out.println("\nTask Priority updated: " + task.getTaskPriority());
                    break;

                case 2:
                    System.out.println("Enter task ID");
                    if (!input.hasNextInt()) {
                        System.out.println("Invalid task Id. Please try again");
                        input.nextLine();
                        break;
                    }
                    taskId = input.nextInt();
                    input.nextLine();

                    task = taskList.getTaskById(taskId);

                    if (task == null) {
                        System.out.println("Invalid task Id. Please try again");
                        break;
                    }
                    task.markCompleted();
                    System.out.println("Task completion status updated to complete\n");

                    System.out.println("\nUpdated Task:");
                    System.out.println(task);
                    break;

                case 3:
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
                    break;
            }
        }
        return task;
    }

    //3. increase task priority
    public void increaseTaskPriority(Task task, Scanner input) {
        String currentTaskPriority = task.getTaskPriority();
        if (currentTaskPriority.equalsIgnoreCase("Low")) {
            System.out.println("Do you want to increase the priority from Low to medium");
            String response1 = input.nextLine();
            if (response1.equalsIgnoreCase("yes")) {

                removeTaskFromQueue(lowPriorityQueue, task);
                task.setTaskPriority("Medium");
                mediumPriorityQueue.enqueue(task);


            } else if (response1.equalsIgnoreCase("no")) {
                System.out.println("Priority not updated");
            }

        } else if (currentTaskPriority.equalsIgnoreCase("Medium")) {
            System.out.println("Do you want to increase the priority from Medium to High");
            String response2 = input.nextLine();
            if (response2.equalsIgnoreCase("yes")) {

                removeTaskFromQueue(highPriorityQueue, task);
                task.setTaskPriority("High");
                highPriorityQueue.enqueue(task);


            } else if (response2.equalsIgnoreCase("no")) {
                System.out.println("Priority not updated");
            }

        } else if (currentTaskPriority.equalsIgnoreCase("High")) {
            System.out.println("Priority cannot be higher than this!");
        } else {
            System.out.println("Invalid priority!");
        }
    }

    //4. decrease task priority
    public void decreaseTaskPriority(Task task, Scanner input) {
        String currentTaskPriority = task.getTaskPriority();
        if (currentTaskPriority.equalsIgnoreCase("Medium")) {
            System.out.println("Do you want to decrease the priority from Medium to Low");
            String response3 = input.nextLine();
            if (response3.equalsIgnoreCase("yes")) {

                removeTaskFromQueue(mediumPriorityQueue, task);
                task.setTaskPriority("Low");
                lowPriorityQueue.enqueue(task);


            } else if (response3.equalsIgnoreCase("no")) {
                System.out.println("Priority not updated");
            }
        } else if (currentTaskPriority.equalsIgnoreCase("High")) {
            System.out.println("Do you want to decrease the priority from High to Medium");
            String response4 = input.nextLine();
            if (response4.equalsIgnoreCase("yes")) {

                removeTaskFromQueue(highPriorityQueue, task);
                task.setTaskPriority("Medium");
                mediumPriorityQueue.enqueue(task);

            } else if (response4.equalsIgnoreCase("no")) {
                System.out.println("Priority not updated");
            }

        } else if (currentTaskPriority.equalsIgnoreCase("Low")) {
            System.out.println("Priority cannot be lower than this!");
        } else {
            System.out.println("Invalid priority!");
        }
    }


    //Processing tasks
    public void processNextTask(Scanner input, LinkedList taskList) {
        while (true) {
            System.out.println("Do you want to process a task?(yes/no)");
            String response = input.nextLine();

            if (response.equalsIgnoreCase("yes")) { //checking for empty list,where there ae no tasks to process
                if(highPriorityQueue.isEmpty() && mediumPriorityQueue.isEmpty() && lowPriorityQueue.isEmpty()) {
                    System.out.println("\nNo tasks to process, list is empty\n");
                }else {
                    System.out.println("Processing completed task\n");
                    System.out.println("The remaining tasks are:");
                    displayAllQueues();

                    Task taskToProcess = null;                                             //initializing the task to be processed
                    CustomQueue queueToProcess = null;                                    //initializing the queue to be processed
                                                                                         //this is where the task processing based on priority is determined
                    if (!highPriorityQueue.isEmpty()) {
                        taskToProcess = highPriorityQueue.peek();                       //retrieving the task sat the front of the queue, FIFO principle
                        queueToProcess = highPriorityQueue;                            //the queue from which the task is processed

                    } else if (!mediumPriorityQueue.isEmpty()) {
                        taskToProcess = mediumPriorityQueue.peek();
                        queueToProcess = mediumPriorityQueue;

                    } else if (!lowPriorityQueue.isEmpty()) {
                        taskToProcess = lowPriorityQueue.peek();
                        queueToProcess = lowPriorityQueue;

                    }if (taskToProcess != null) {
                        resolveDependencies(taskToProcess, taskList);                //check the status of dependency
                        if (areDependenciesCompleted(taskToProcess, taskList)) {    //check if the dependencies of the task to be processed is completed, returns boolean
                            removeTaskFromQueue(queueToProcess);
                            taskToProcess.markCompleted();
                        } else {
                            System.out.println("\nCannot process task ,dependencies not met\n");
                        }
                    } else {
                      break;
                    }
                }
            }else if(response.equalsIgnoreCase("no")){
                System.out.println("Task processing stopped\n");
                break;
            }else{
                System.out.println("Invalid choice. Please try again");

            }
        }


    }
    //to process tasks the tasks are removed from the queue
    public void removeTaskFromQueue(CustomQueue queue) {
        if (queue.isEmpty()) {
            System.out.println(" ");
            return;
        }
        Task removedTask = queue.dequeue();

        if (removedTask != null) {
            System.out.println(" ");
            System.out.println("\nRemoved task:\n" + removedTask);
        }
    }

    public void removeTaskFromQueue(CustomQueue queue, Task taskToRemove) {
        if (queue.isEmpty()) {
            return;
        }
        Node current = queue.front;
        Node previous = null;

        while (current != null) {
            if (current.task.getTaskId() == taskToRemove.getTaskId()) {
                if (previous == null) {
                    queue.front = current.next;

                } else {
                    previous.next = current.next;
                }
                if (current == queue.rear) {
                    queue.rear = previous;
                }
                System.out.println(" ");
                return;
            }
            previous = current;
            current = current.next;
        }

    }

    //undo operations
    public void undoTaskDeletion (LinkedList newTaskList, customStack stack){
        if (stack.isEmpty()){
            System.out.println("There is no task to undo");
        }else{
            Task undoneTask = stack.pop();  //most recent operation
            newTaskList.addTaskSorted(undoneTask,listOperator);
            System.out.println("Most recent Task Deletion Undone");

        }
    }

    public void undoTaskAddition (LinkedList newTaskList, customStack stack){
        if (stack.isEmpty()){
            System.out.println("There is no task to undo");
        }else{
            Task undoneTask = stack.pop();  //most recent operation
            newTaskList.deleteTaskById(undoneTask.getTaskId());
            System.out.println("Most recent Task addition Undone");
        }
    }


    public void displayAllQueues() {
        System.out.println("\nHigh Priority Queue tasks\n");
        highPriorityQueue.displayQueue();

        System.out.println("\nMedium Priority Queue tasks\n");
        mediumPriorityQueue.displayQueue();

        System.out.println("\nLow Priority Queue tasks\n");
        lowPriorityQueue.displayQueue();
    }


//task management system

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        LinkedList newTaskList = new LinkedList();
        TaskOperations taskOps = new TaskOperations();
        LinkedListOperator linkedListOp = new LinkedListOperator();
        TaskView view = new TaskView();
        MapOperator map = new MapOperator(100);
        customStack stack = new customStack();


        boolean running = true;
        while (running) {
            System.out.println("\n===================================================================");
            System.out.println("Welcome to Task Management System");
            System.out.println("====================================================================\n");
            System.out.println("Select your choice");
            System.out.println("[1]Add Tasks");
            System.out.println("[2]Delete Tasks");
            System.out.println("[3]Search Tasks");
            System.out.println("[4]Update Tasks");
            System.out.println("[5]Sort tasks by priority");
            System.out.println("[6]Process tasks completed");
            System.out.println("[7]View tasks by priority");
            System.out.println("[8]Undo most recent task addition");
            System.out.println("[9]Undo most recent task deletion");
            System.out.println("[10]Exit");

            if (!input.hasNextInt()) {
                System.out.println("Invalid Input. Please try again");
                input.nextLine();
                continue;

            }
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    Task task = taskOps.addTask(input, newTaskList);
                    taskOps.addTaskToQueues(task);
                    taskOps.processTask(newTaskList, stack, task, map);
                    break;

                case 2:
                    if (taskOps.deleteTask(input, newTaskList)) {

                    } else {
                        System.out.println("\nTask Not Deleted\n");
                    }
                    break;

                case 3:

                    System.out.println("Enter task ID to search");
                    if (input.hasNextInt()) {
                        int searchId = input.nextInt();
                        input.nextLine();
                        Task foundTask = map.getTaskById(searchId);

                        if (foundTask != null) {
                            System.out.println("Task Found\n" + foundTask);

                        } else {
                            System.out.println("\nTask Not Found\n");
                        }

                    } else {
                        System.out.println("Invalid Input. Please try again");
                    }
                    break;

                case 4:

                    Task taskModified = taskOps.taskModification(input, newTaskList);
                    System.out.println("\nTask Modified\n" + taskModified.toString());
                    break;

                case 5:

                    Task[] sortedTasks;
                    linkedListOp.mergesortByPriority(newTaskList.toArray(), newTaskList.priorityOrder);
                    System.out.println("\nThe tasks has been successfully sorted\n");
                    break;

                case 6:
                    taskOps.processNextTask(input,newTaskList);
                    break;

                case 7:
                    System.out.println("\nTask in system are:\n");
                    sortedTasks = linkedListOp.mergesortByPriority(newTaskList.toArray(), newTaskList.priorityOrder);
                    view.displayTasks(sortedTasks, input);
                    break;

                case 8:
                    taskOps.undoTaskAddition(newTaskList, stack);
                    break;

                case 9:
                    taskOps.undoTaskDeletion(newTaskList, stack);
                    break;

                case 10:
                    running = false;
                    break;


                default:
                    System.out.println("Invalid choice. Please try again");
                    break;

            }

        }
    }
}

