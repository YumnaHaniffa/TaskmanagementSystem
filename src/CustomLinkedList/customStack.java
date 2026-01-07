package CustomLinkedList;

import TaskObject.Task;
//To perform undo operations
public class customStack {

        private Node top;                                        // points to the top of the stack

        public customStack() {
            this.top = null;                                    //initially the stack is empty
        }
        //The method checking whether it is full is not called
        public void push(Task task) {
            Node newNode = new Node(task);                      //newNode object is created
            newNode.next = top;                                 //newNode points to the current top which is null(it is na exception , this is only during the first time we create a node)
            top = newNode;                                      // Move pointer of the newNode to next node

        }
        //Before popping check whether empty
        public boolean isEmpty() {
            if (top == null) {
                return true;
            }
            return false;
        }
        public Task pop() {
            if (isEmpty()) {
                System.out.println("Stack Underflow");
                return null;

            }
            Task poppedData = top.task;                      //If the stack not empty, 1.Locate the top data(as we can only remove from the top)----the data which is to be removed
            top = top.next;                                 //after the top node is removed the node which the previous
            System.out.println("Popped from stack");
            return poppedData;

        }






}

