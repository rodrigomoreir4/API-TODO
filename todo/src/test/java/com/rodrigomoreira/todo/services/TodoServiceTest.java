package com.rodrigomoreira.todo.services;

import com.rodrigomoreira.todo.domain.Todo;
import com.rodrigomoreira.todo.repositories.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.rodrigomoreira.todo.constants.TodoConstants.TODO;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    @Test
    void findAll() {
        List<Todo> todos = Arrays.asList(TODO);
        when(todoRepository.findAll()).thenReturn(todos);
        List<Todo> result = todoService.findAll();
        assertThat(result).isEqualTo(todos);
    }

    @Test
    void findById() {
        when(todoRepository.findById(1L)).thenReturn(Optional.of(TODO));
        Optional<Todo> todo = todoService.findById(1L);
        assertThat(todo.get()).isEqualTo(TODO);
    }

    @Test
    void save() {
        when(todoRepository.save(TODO)).thenReturn(TODO);
        Todo todo = todoService.save(TODO);
        assertThat(todo).isEqualTo(TODO);
    }

    @Test
    void deleteById() {
        assertThatCode(() -> todoService.deleteById(1L)).doesNotThrowAnyException();
    }
}