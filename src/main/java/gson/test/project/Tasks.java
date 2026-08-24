package gson.test.project;

import java.util.List;

public class Tasks {
    private int taskID;
    private String description;
    private Status status;
    private String createdAt;
    private String updatedAt;

    public Tasks(){

    }

    public Tasks(int taskID, String description, Status status, String createdAt, String updatedAt) {
        this.taskID = taskID;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Tasks(List<Tasks> tasks) {
    }

    public int getTaskID(){
        return taskID;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public String getCreatedAt(){
        return this.createdAt;
    }

    public String getUpdatedAt(){
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt){
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s | %s | %s | %s",
                taskID, description, status, createdAt, updatedAt);
    }
}
