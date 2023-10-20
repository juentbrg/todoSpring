package com.julien.todo.DTO;

import com.julien.todo.model.Task;
import com.julien.todo.model.TodoList;

public class TaskDTO {

    private String taskTitle;

    private String taskDescription;

    private Boolean taskIsDone;

    private Integer listId;

    public TaskDTO() {}

    public TaskDTO(Task task) {
        TodoList todoList = task.getTodoList();

        this.taskTitle = task.getTaskTitle();
        this.taskDescription = task.getTaskDescription();
        this.taskIsDone = task.getTaskIsDone();
        this.listId = todoList.getListId();
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Boolean getTaskIsDone() {
        return taskIsDone;
    }

    public void setTaskIsDone(Boolean taskIsDone) {
        this.taskIsDone = taskIsDone;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "taskTitle='" + taskTitle + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskIsDone=" + taskIsDone +
                ", listId=" + listId +
                '}';
    }
}
