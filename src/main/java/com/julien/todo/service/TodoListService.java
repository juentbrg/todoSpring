package com.julien.todo.service;

import com.julien.todo.DTO.TaskDTO;
import com.julien.todo.DTO.TodoListDTO;
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
public class TodoListService {

    private final TodoListRepository todoListRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public TodoListService(TodoListRepository todoListRepository, TaskRepository taskRepository) {
        this.todoListRepository = todoListRepository;
        this.taskRepository = taskRepository;
    }

//    @Transactional(readOnly=true)
//    public List<TodoListDTO> findAll() {
//        List<TodoList> todoLists = (List<TodoList>) todoListRepository.findAll();
//        List<TodoListDTO> todoListDTOS = new ArrayList<>();
//
//        for (TodoList todos : todoLists) {
//            TodoListDTO todoListDTO = new TodoListDTO(todos);
//            todoListDTOS.add(todoListDTO);
//        }
//        return todoListDTOS;
//    }

    public List<TodoListDTO> findAllWithTasks() {
        List<TodoList> todoLists = todoListRepository.findAllWithTasks();
        List<TodoListDTO> todoListDTOS = new ArrayList<>();

        for (TodoList todos : todoLists) {
            TodoListDTO todoListDTO = new TodoListDTO(todos);
            todoListDTOS.add(todoListDTO);
        }
        return todoListDTOS;
    }

//    public TodoListDTO findById(int id) {
//        Optional<TodoList> todoListOpt = todoListRepository.findById(id);
//        if (todoListOpt.isPresent()) {
//            TodoList todoList = todoListOpt.get();
//            return new TodoListDTO(todoList);
//        }
//        return null;
//    }

    public TodoListDTO findById(int id) {
        TodoList todoList = todoListRepository.findListById(id);
        if (todoList == null) {
            return null;
        } else {
            return new TodoListDTO(todoList);
        }
    }

    public void save(TodoListDTO todoListDTO) {
        TodoList todoList = new TodoList();
        todoList.setListName(todoListDTO.getListName());

        todoListRepository.save(todoList);

        for (TaskDTO task : todoListDTO.getTaskDTOS()) {
            Task tasks = new Task();
            tasks.setTaskTitle(task.getTaskTitle());
            tasks.setTaskDescription(task.getTaskDescription());
            tasks.setTaskIsDone(task.getTaskIsDone());
            tasks.setTodoList(todoList);
            taskRepository.save(tasks);
        }
    }

    @Transactional
    public boolean updateById(int id, TodoListDTO todoListDTO) {
        Optional<TodoList> todoListOpt = todoListRepository.findById(id);
        if (todoListOpt.isPresent()) {
            TodoList todoList = todoListOpt.get();
            if (todoListDTO.getListName() != null) {
                todoList.setListName(todoListDTO.getListName());
            }
            return true;
        } else {
            return false;
        }
    }

    public void deleteById(int id) {
        todoListRepository.deleteById(id);
    }
}
