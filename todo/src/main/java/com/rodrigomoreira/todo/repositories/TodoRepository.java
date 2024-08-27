package com.rodrigomoreira.todo.repositories;

import com.rodrigomoreira.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}