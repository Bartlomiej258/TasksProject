package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final DbService dbService;
    private final TaskMapper taskMapper;

    @GetMapping
    private List<TaskDto> getTasks() {
        List<Task> allTasks = dbService.getAllTasks();
        return taskMapper.mapToTaskDtoList(allTasks);
    }

    @GetMapping( value = "{taskId}")
    private TaskDto getTask(@PathVariable Long taskId) {
        Task task = dbService.getTaskById(taskId);
        if (task != null) {
            return taskMapper.mapToTaskDto(task);
        } else {
            throw new IllegalArgumentException("not found ID " + taskId);
        }
    }

    @DeleteMapping
    private void deleteTask(Long taskId) {
        System.out.println("deleted");
    }

    @PutMapping
    private TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "Edited test title", "Test content");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        dbService.saveTask(task);
    }
}
