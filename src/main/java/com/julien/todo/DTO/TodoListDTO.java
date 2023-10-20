package com.julien.todo.DTO;

import com.julien.todo.model.Task;
import com.julien.todo.model.TodoList;

import java.util.ArrayList;
import java.util.List;

public class TodoListDTO {

    private String listName;

    private List<TaskDTO> taskDTOS;

    public TodoListDTO() {}

    public TodoListDTO(TodoList todoList) {
        List<TaskDTO> taskDTOSbis = new ArrayList<>();
        for (Task task : todoList.getTasks()) {
            TaskDTO taskDTO = new TaskDTO(task);
            taskDTOSbis.add(taskDTO);
        }

        this.listName = todoList.getListName();
        this.taskDTOS = taskDTOSbis;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public List<TaskDTO> getTaskDTOS() {
        return taskDTOS;
    }

    public void setTaskDTOS(List<TaskDTO> taskDTOS) {
        this.taskDTOS = taskDTOS;
    }

    @Override
    public String toString() {
        return "TodoListDTO{" +
                "listName='" + listName + '\'' +
                ", taskDTOS=" + taskDTOS +
                '}';
    }
}
