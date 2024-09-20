package com.rodrigomoreira.todo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigomoreira.todo.services.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static com.rodrigomoreira.todo.constants.TodoConstants.TODO;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TodoService todoService;

    @Test
    void getAllTodos() throws Exception{
        when(todoService.findAll()).thenReturn(Collections.emptyList());

        mockMvc
                .perform(
                        get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getTodoById() throws Exception{
        when(todoService.findById(1L)).thenReturn(Optional.of(TODO));

        mockMvc
                .perform(
                        get("/todos/1"))
                .andExpect(status().isOk());
    }

    @Test
    void createTodo() throws Exception{
        when(todoService.save(TODO)).thenReturn(TODO);

        mockMvc
                .perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TODO)))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteTodoById() throws Exception{
        mockMvc.perform(delete("/todos/1"))
                .andExpect(status().isNotFound());
    }
}