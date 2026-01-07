package CustomLinkedList;

import TaskObject.Task;
//to use an instance of the task class
public class Node {

   public Task task;                              //The task stored in the node
    public Node next;                             //referring to the next node

    //constructor to initialize the node
    Node(Task task) {
        this.task = task;                        // initialize the node with the provided task
        this.next = null;                         // set the next node as null
    }

    public Node getNext() {
        return next;
    }
    public Task getTask() {
        return task;
    }

}
