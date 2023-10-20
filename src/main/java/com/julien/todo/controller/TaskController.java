package com.julien.todo.controller;

import com.julien.todo.DTO.TaskDTO;
import com.julien.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDTO> findAllTasks() {
        return taskService.findAll();
    }

    //    @GetMapping("/{id}")
//    public ResponseEntity<?> findTaskById(@PathVariable Integer id) {
//        TaskDTO taskDTO = taskService.findById(id);
//        if (null != taskDTO) {
//            return ResponseEntity.ok(taskDTO);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findTaskById(@PathVariable Integer id) {
        TaskDTO taskDTO = taskService.findByTaskId(id);
        if (null == taskDTO) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(taskDTO);
        }
    }

    @PostMapping
    public void saveTask(@RequestBody TaskDTO taskDTO) {
        taskService.save(taskDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable Integer id, @RequestBody TaskDTO taskDTO) {
        boolean updated = taskService.updateById(id, taskDTO);
        System.out.println(updated);
        if (updated) {
            return ResponseEntity.ok("Tasks successfully updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable Integer id) {
        taskService.deleteById(id);
        return ResponseEntity.ok("Tasks deleted successfully");
    }
}
