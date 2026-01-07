package CustomMap;

import TaskObject.Task;
//To perform linear search
public class MapOperator {

        public int[] keys;                        //takID that will be the key
        public Task[] values;                    //The tasks are stored in a list
        public int taskCount;
        public int capacity;

        public MapOperator(int capacity) {
            this.capacity = capacity;
            this.keys = new int[capacity];
            this.values = new Task[capacity];
            this.taskCount = 0;
        }

        //add key-value pair
        public void addTaskToMap(Task task){
            if(taskCount < values.length) {
                values[taskCount] = task;
                keys[taskCount] = task.getTaskId();
                taskCount++;
            }else{
                System.out.println("The task list is full");
            }

        }

        public Task getTaskById(int taskId){
                for(int i = 0; i < taskCount; i++){
                    if(keys[i] == taskId){
                        System.out.println("\n");
                        return values[i];

                    }
                }
                return null;
        }
}

