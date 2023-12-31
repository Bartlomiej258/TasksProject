package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbService {

    @Autowired
    private final TaskRepository taskRepository;


    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task saveTask(final Task task) {
        return taskRepository.save(task);
    }

    public Task getTask(final long taskId) throws TaskNotFoundException {
        return taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
    }

    public void deleteTask(final long taskId) throws TaskNotFoundException{
        taskRepository.deleteById(taskId);
    }

}
