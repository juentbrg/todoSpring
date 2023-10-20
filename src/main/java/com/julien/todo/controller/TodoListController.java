package com.julien.todo.controller;

import com.julien.todo.DTO.TodoListDTO;
import com.julien.todo.service.TodoListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/todolist")
public class TodoListController {

    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

//    @GetMapping
//    public List<TodoListDTO> findAllTodoList() {
//        return todoListService.findAll();
//    }

    @GetMapping
    public List<TodoListDTO> findAllTodoListWithTasks() {
        return todoListService.findAllWithTasks();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<TodoListDTO> findTodoListById(@PathVariable int id) {
//        TodoListDTO todoListDTO = todoListService.findById(id);
//        if (null != todoListDTO) {
//            return ResponseEntity.ok(todoListDTO);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoListDTO> findTodoListById(@PathVariable int id) {
        TodoListDTO todoListDTO = todoListService.findById(id);
        if (null != todoListDTO) {
            return ResponseEntity.ok(todoListDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TodoListDTO> createTodoList(@RequestBody TodoListDTO todoListDTO) {
        todoListService.save(todoListDTO);
        return ResponseEntity.ok(todoListDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoListDTO> updateTodoList(@PathVariable int id, @RequestBody TodoListDTO todoListDTO) {
        boolean updateById = todoListService.updateById(id, todoListDTO);
        if (updateById) {
            return ResponseEntity.ok(todoListDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTodoListById(@PathVariable int id) {
        todoListService.deleteById(id);
    }
}
