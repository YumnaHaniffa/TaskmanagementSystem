package CustomLinkedList;

import TaskObject.Task;

public class dependencyNode {
        //to use an instance of the task clas
    public Task task;
    public dependencyNode next;


    public dependencyNode getNext() {return next;}
    public Task getTask() {return task;}

        //constructor to initialize the node

    dependencyNode(Task task) {
        this.task = task;                        // Constructor to initialize declare the variables
        this.next = null;                         // // new nodes always point to null
        }
    }
