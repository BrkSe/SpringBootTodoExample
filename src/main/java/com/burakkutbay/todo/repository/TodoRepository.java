package com.burakkutbay.todo.repository;

import com.burakkutbay.todo.entity.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo,Long> {
}
