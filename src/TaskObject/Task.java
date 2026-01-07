package TaskObject;

import java.util.ArrayList;
import java.util.List;

//Task class represents a single task
public class Task {


    //1. Attributes of the Tasks(instance variable)
    //private- to avoid direct modification from out of the class
    private final int taskId;
    private String taskTitle;
    private String taskPriority;
    private boolean isCompleted;
    private final List<Integer> dependencies;

    //1. The Constructor
    //To allow multiple initialization values
    // Constructor with default completion status


    // Constructor with user defined completion status
    public Task(int taskId, String taskTitle, String taskPriority, boolean isCompleted) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskPriority = taskPriority;
        this.isCompleted = isCompleted;
        this.dependencies = new ArrayList<>();
    }


    public String getTaskTitle() {
        return taskTitle;

    }

    public void setTaskPriority(String taskPriority) {       //assigning the parameter
        this.taskPriority = taskPriority;
    }

    public String getTaskPriority() {

        return taskPriority;
    }


    //2.Confirming the completion status**
    public void markCompleted() {
        this.isCompleted = true;
    }

    //5.Checking the completion Status
    public boolean isCompleted() {
        return isCompleted;
    }


   //3.Display Task information(readable)

    @Override
    public String toString() {
        return  "Task details:\n" +
                "\nTask Id = " + taskId +
                "\nTask title = " + taskTitle +
                "\nTask Priority = " + taskPriority +
                "\nTask Completion Status = " + isCompleted;
    }


    public int getTaskId() {
        return taskId;
    }

    public void addDependency(int dependencyId) {
        this.dependencies.add(dependencyId);
    }


    public List<Integer> getDependencies() {
        return dependencies;
    }




}
