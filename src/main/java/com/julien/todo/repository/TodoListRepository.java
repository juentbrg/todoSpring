package com.julien.todo.repository;

import com.julien.todo.model.TodoList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoListRepository extends CrudRepository<TodoList, Integer> {

    @Query("select tl from TodoList tl join fetch tl.tasks")
    List<TodoList> findAllWithTasks();

    @Query("select tl from TodoList tl join fetch tl.tasks where tl.listId = ?1")
    TodoList findListById(int id);
}
