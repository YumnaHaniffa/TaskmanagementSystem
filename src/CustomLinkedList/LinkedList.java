package CustomLinkedList;

import TaskObject.Task;

//To perform linked list operations
public class LinkedList {

    private Node head;                                                     //The head of the linked list
    private Node tail;                                                    //the last element of the list
    public final String [] priorityOrder = {"High", "Medium", "Low"};     //the  list of priorities

    public void addTaskSorted(Task task,LinkedListOperator listOperator) {

        if (task == null || listOperator == null) {                       //Handle if task or listOperator is null
            System.out.println("Error: task or listOperator is null");
            return;
        }
        Node newNode = new Node(task);

        if (head == null) {
            head = newNode;                                                  //if the there is no node the head becomes the new node
            tail = newNode;
            return;
        }                                                                   //this is the priority of the new task to be compared with the already existing ones
        int newTakPriorityIndex = listOperator.getPriorityIndex(task.getTaskPriority(),priorityOrder);
        if(newTakPriorityIndex < 0 || newTakPriorityIndex >= priorityOrder.length) {
            System.out.println("Error: Invalid task priority");
            return;
        }

        //initialize the pointers
        Node current =  head;
        Node previous = null;

        while (current != null){
            int currentTakPriorityIndex = listOperator.getPriorityIndex(current.task.getTaskPriority(),priorityOrder);
            if (newTakPriorityIndex <= currentTakPriorityIndex) {
                if (previous == null) {
                    newNode.next = head;
                    head = newNode;
                }else {
                    newNode.next = current;
                    previous.next = newNode;
                }
                return;
            }
            previous = current;
            current = current.next;
        }
        tail.next = newNode;
        tail = newNode;

    }

    public int getSize() {
        Node current = head;
        int count = 0;
        while (current!= null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public Task[] toArray() {
        int size = getSize();
        Task[] tasks = new Task[size];
        Node current = head;
        int i = 0;

        while (current != null) {
            tasks[i] = current.task;
            i++;
            current = current.next;
        }
        return tasks;
    }

    public boolean deleteTaskById(int taskId) {
        Node currentNode = head;
        Node previousNode = null;
        if (currentNode == null) {
            return  false;
        }else if (currentNode.task.getTaskId() == taskId){
            head = currentNode.next;
            if (head == null) {
                tail = null;
            }
            return true;

        }
        while( currentNode!= null && currentNode.task.getTaskId()!= taskId){
            previousNode = currentNode;
            currentNode = currentNode.next;
        }

        if (currentNode != null){
            previousNode.next = currentNode.next;
            if (currentNode.next == null) {
                tail = previousNode;

            }

            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            if (current.task != null) {
                sb.append(current.task.getTaskId()).append(": ").append(current.task.toString());
            }else{
                sb.append("null");
            }
            if(current.next != null){
                sb.append(",");
            }
            current = current.next;
        }
        sb.append("");
        return sb.toString();
    }

    public Task getTaskById(int taskId) {
        Node currentNode = head;
        if (currentNode == null) {
           return null;
        }

        while (currentNode != null) {
            if (currentNode.task.getTaskId() == taskId) {
                return currentNode.task;

            }
            currentNode = currentNode.next;
        }
        return null;
    }
    public boolean containsTaskById(int taskId) {
        return  getTaskById(taskId) != null;
    }

    public void deleteAllTasks(LinkedList taskList){
        if (taskList == null || taskList.getSize() == 0){
            System.out.println("Task list is empty");
            return;
        }
        taskList.head = null;
        taskList.tail = null;
        System.out.println("Delete all tasks");
    }



}



