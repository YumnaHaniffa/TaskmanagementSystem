package CustomLinkedList;
import TaskObject.Task;
//to perform task processing operations
public class CustomQueue {

    public Node front;         // Index of the front element, starts from the front
    public Node rear;          //Index of the rear element,starts form the end

    //Constructor, initialize the variables
    public CustomQueue() {
        this.front = null;
        this.rear = null;
    }

    public boolean isEmpty() {        //Check if queue empty
        return (front == null);
    }

    public void enqueue(Task task) {
        Node node = new Node(task);
        if (isEmpty()) {
            front = node;
            rear = node;
        } else {
            rear.next = node;
            rear = node;
        }
        System.out.print("\n " + task);


    }

    public Task dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue");
            return null;
        }
        Task removedTask = front.task;
        front = front.next;

        if (front == null) {
            rear = null;
        }
        removedTask.markCompleted();
        return removedTask;
    }

    public Task peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot peek");
            return null;

        }
        return front.task;
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        Node current = front;
        System.out.print("Queue Contents ");
        while (current != null) {
            System.out.print(" " + current.task);
            current = current.next;
            System.out.println(" ");
            System.out.println(" ");
        }
    }

}
