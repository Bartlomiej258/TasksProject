package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {

    private final TaskRepository taskRepository;


    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        List<Task> lists = getAllTasks();
        for (Task task : lists) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public Task saveTask(final Task task) {
        return taskRepository.save(task);
    }

}
