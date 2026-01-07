package CustomLinkedList;

import TaskObject.Task;
//To perform linked list operations
public class LinkedListOperator {


    public int getPriorityIndex(String priority, String[] priorityOrder){      //("Medium"  ,priorityList)
        for (int i = 0; i < priorityOrder.length; i++) {                       //for 0,1,2
            if (priorityOrder[i].equalsIgnoreCase(priority)) {                 //priorityOrder["High"].equalsIgnoreCase("Medium")....until  priorityOrder["Medium"].equalsIgnoreCase("Medium")
                return i;                                                      //then return 1
            }
        }
        //place not found priority at the end
        return priorityOrder.length;                                          // place the not found priority at the end of a sorted list
    }
    public void merge(Task [] tasksToBeSorted, int l, int m, int r, String[] priorityOrder){
        //calculate the size
        int size1 = m-l+1;
        int size2 = r-m;

        //define the subArrays
        Task [] sub1 = new Task[size1];
        Task [] sub2 = new Task[size2];

        //copy data to the subArrays
        for (int x = 0; x < size1; ++x) {
            sub1[x] = tasksToBeSorted[l+x];
        }
        for (int x = 0; x < size2; ++x) {
            sub2[x] = tasksToBeSorted[m+1+x];
        }

        //merge the subArrays
        int k = l;
        int j = 0;
        int i = 0;

        while(i < size1 && j < size2){
            int priorityOne = getPriorityIndex(sub1[i].getTaskPriority(), priorityOrder); //the priority number is allocated
            int priorityTwo = getPriorityIndex(sub2[j].getTaskPriority(), priorityOrder);
            if (priorityOne <= priorityTwo) {
                tasksToBeSorted[k] = sub1[i];
                i++;
            }else{
                tasksToBeSorted[k] = sub2[j];
                j++;
            }
            k++;

        }
        while (i < size1){
            tasksToBeSorted[k] = sub1[i];
            i++;
            k++;

        }
        while(j < size2){
            tasksToBeSorted[k] = sub2[j];
            j++;
            k++;
        }

    }

    public void mergeSort(Task [] tasksToBeSorted, int l, int r, String[] priorityOrder){
        if (l < r) {
            int m = l+(r-l)/2;
            //Recursively sort the first and second halves
            mergeSort(tasksToBeSorted,l,m,priorityOrder);
            mergeSort(tasksToBeSorted,m+1,r,priorityOrder);

            //merge the sorted halves
            merge(tasksToBeSorted,l,m,r,priorityOrder);

        }
    }
    public Task[] mergesortByPriority(Task[] tasks, String[] priorityOrder){
        mergeSort(tasks,0,tasks.length-1,priorityOrder);
        return tasks;
    }


}
