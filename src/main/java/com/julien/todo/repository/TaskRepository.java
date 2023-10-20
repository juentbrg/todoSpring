package com.julien.todo.repository;

import com.julien.todo.model.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {

    @Query("select t from Task t where t.taskId = ?1")
    Task findByTaskId(int id);
}
