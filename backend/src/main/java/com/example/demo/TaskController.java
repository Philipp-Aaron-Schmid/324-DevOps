package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();
    private final String filePath = "tasks.json";

    public TaskController() {
        loadTasks();
    }

    @CrossOrigin
    @GetMapping(value = "/", params = "version=1")
    public ResponseEntity<List<Task>> getTasksV1() {
        System.out.println("API EP '/' returns task-list of size " + tasks.size() + ".");
        return ResponseEntity.ok(tasks);
    }

    @CrossOrigin
    @GetMapping(value = "/", params = "version=2")
    public ResponseEntity<String> getTasksV2() {
        System.out.println("API EP '/' returns task-list of size " + tasks.size() + ".");
        StringBuilder response = new StringBuilder("Tasks:\n");
        for (Task task : tasks) {
            response.append(task.getTaskdescription()).append("\n");
        }
        return ResponseEntity.ok(response.toString());
    }

    @CrossOrigin
    @PostMapping(value = "/tasks", params = "version=1")
    public ResponseEntity<String> addTaskV1(@RequestBody String taskdescription) {
        System.out.println("API EP '/tasks': '" + taskdescription + "'");
        try {
            Task task = mapper.readValue(taskdescription, Task.class);
            for (Task t : tasks) {
                if (t.getTaskdescription().equals(task.getTaskdescription())) {
                    System.out.println(">>>task: '" + task.getTaskdescription() + "' already exists!");
                    return ResponseEntity.badRequest().body("Task already exists!");
                }
            }
            tasks.add(task);
            saveTasks();
            return ResponseEntity.ok("Task added successfully");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing task");
        }
    }

    @CrossOrigin
    @PostMapping(value = "/tasks", params = "version=2")
    public ResponseEntity<String> addTaskV2(@RequestBody String taskdescription) {
        System.out.println("API EP '/tasks': '" + taskdescription + "'");
        try {
            Task task = mapper.readValue(taskdescription, Task.class);
            for (Task t : tasks) {
                if (t.getTaskdescription().equals(task.getTaskdescription())) {
                    System.out.println(">>>task: '" + task.getTaskdescription() + "' already exists!");
                    return ResponseEntity.badRequest().body("Task already exists!");
                }
            }
            tasks.add(task);
            saveTasks();
            return ResponseEntity.ok("Task added successfully");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing task");
        }
    }

    @CrossOrigin
    @PostMapping(value = "/delete", params = "version=1")
    public ResponseEntity<String> delTaskV1(@RequestBody String taskdescription) {
        System.out.println("API EP '/delete': '" + taskdescription + "'");
        try {
            Task task = mapper.readValue(taskdescription, Task.class);
            Iterator<Task> it = tasks.iterator();
            while (it.hasNext()) {
                Task t = it.next();
                if (t.getTaskdescription().equals(task.getTaskdescription())) {
                    it.remove();
                    saveTasks();
                    return ResponseEntity.ok("Task deleted successfully");
                }
            }
            return ResponseEntity.status(404).body("Task not found!");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing task");
        }
    }

    @CrossOrigin
    @PostMapping(value = "/delete", params = "version=2")
    public ResponseEntity<String> delTaskV2(@RequestBody String taskdescription) {
        System.out.println("API EP '/delete': '" + taskdescription + "'");
        try {
            Task task = mapper.readValue(taskdescription, Task.class);
            Iterator<Task> it = tasks.iterator();
            while (it.hasNext()) {
                Task t = it.next();
                if (t.getTaskdescription().equals(task.getTaskdescription())) {
                    it.remove();
                    saveTasks();
                    return ResponseEntity.ok("Task deleted successfully");
                }
            }
            return ResponseEntity.status(404).body("Task not found!");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing task");
        }
    }

    private void saveTasks() {
        try {
            mapper.writeValue(new File(filePath), tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTasks() {
        try {
            if (Files.exists(Paths.get(filePath))) {
                CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Task.class);
                tasks = mapper.readValue(new File(filePath), listType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
