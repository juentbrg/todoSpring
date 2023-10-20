package com.julien.todo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "TodoList")
@Table(name = "TODO_LIST")
public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_id")
    private int listId;

    @Column(name = "list_name")
    public String listName;

    @OneToMany(mappedBy = "todoList")
    private List<Task> tasks;

    public TodoList() {}

    public TodoList(int listId, String listName, List<Task> tasks) {
        this.listId = listId;
        this.listName = listName;
        this.tasks = tasks;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> task) {
        this.tasks = task;
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "listId=" + listId +
                ", listName='" + listName + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}
