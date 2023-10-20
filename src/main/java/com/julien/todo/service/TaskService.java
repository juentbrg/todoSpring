package com.julien.todo.service;

import com.julien.todo.DTO.TaskDTO;
import com.julien.todo.model.Task;
import com.julien.todo.model.TodoList;
import com.julien.todo.repository.TaskRepository;
import com.julien.todo.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TodoListRepository todoListRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, TodoListRepository todoListRepository) {
        this.taskRepository = taskRepository;
        this.todoListRepository = todoListRepository;
    }

    public List<TaskDTO> findAll() {
        List<Task> taskList = (List<Task>) taskRepository.findAll();
        List<TaskDTO> taskDTOS = new ArrayList<>();

        for (Task task : taskList) {
            TaskDTO taskDTO = new TaskDTO(task);
            taskDTOS.add(taskDTO);
        }
        return taskDTOS;
    }

//    public TaskDTO findById(int id) {
//        Optional<Task> taskOpt = taskRepository.findById(id);
//        if (taskOpt.isPresent()) {
//            Task task = taskOpt.get();
//            TaskDTO taskDTO = new TaskDTO(task);
//            return taskDTO;
//        } else {
//            return null;
//        }
//    }

    public TaskDTO findByTaskId(int id) {
        Task task = taskRepository.findByTaskId(id);
        if (task == null) {
            return null;
        } else {
            return new TaskDTO(task);
        }
    }

    public void save(TaskDTO taskDTO) {
        Task task = new Task();
        Optional<TodoList> todoListOpt = todoListRepository.findById(taskDTO.getListId());

        task.setTaskTitle(taskDTO.getTaskTitle());
        task.setTaskDescription(taskDTO.getTaskDescription());
        task.setTaskIsDone(taskDTO.getTaskIsDone());
        if (todoListOpt.isPresent()) {
            TodoList todoList = todoListOpt.get();
            task.setTodoList(todoList);
        }
        taskRepository.save(task);
    }

    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public boolean updateById(int id, TaskDTO taskDTO) {
        Optional<Task> taskOpt = taskRepository.findById(id);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            if (taskDTO.getTaskTitle() != null) {
                task.setTaskTitle(taskDTO.getTaskTitle());
            }
            if (taskDTO.getTaskDescription() != null) {
                task.setTaskDescription(taskDTO.getTaskDescription());
            }
            if (taskDTO.getTaskIsDone() != null) {
                task.setTaskIsDone(taskDTO.getTaskIsDone());
            }
            return true;
        } else {
            return false;
        }
    }
}
