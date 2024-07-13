package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    private DemoApplication app = new DemoApplication();
    

    @AfterEach
    void cleanUp() {
        app.getTasks().clear();
        app.saveTasks();
    }

    @Test
    void contextLoads() {
        assertTrue(true, "alles gut");
    }

    @Test
    public void test_adding_unique_task_increases_list_size() {
        int initialSize = app.getTasks().size();
        System.out.println("Initial size: " + initialSize);

        String uniqueTaskDescription = "New Task " + System.currentTimeMillis();
        String newTaskJson = "{\"taskdescription\":\"" + uniqueTaskDescription + "\"}";
        app.addTask(newTaskJson);

        int newSize = app.getTasks().size();
        System.out.println("New size: " + newSize);

        assertEquals(initialSize + 1, newSize);
    }

    @Test
    public void test_adding_duplicate_task_does_not_increase_list_size() {
        String newTaskJson = "{\"taskdescription\":\"New Task\"}";
        app.addTask(newTaskJson);
        int initialSize = app.getTasks().size();
        app.addTask(newTaskJson);
        assertEquals(initialSize, app.getTasks().size());
    }

    @Test
    public void test_loads_tasks_from_existing_file_successfully() {
        // Setup
        DemoApplication app = new DemoApplication();
        String newTaskJson = "{\"taskdescription\":\"New Task\"}";
        app.addTask(newTaskJson);
        app.saveTasks();

        DemoApplication app2 = new DemoApplication();

        // Act
        app2.loadTasks();

        // Assert
        assertFalse(app2.getTasks().isEmpty());
    }

    // File exists but is empty
    @Test
    public void test_file_exists_but_is_empty() {
        // Setup
        DemoApplication app = new DemoApplication();
        app.saveTasks();

        // Clear the file content to simulate an empty file
        try {
            Files.write(Paths.get(app.filePath), new byte[0]);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to clear the file content");
        }

        // Act
        app.loadTasks();

        // Assert
        assertTrue(app.getTasks().isEmpty());
    }
    @Test
    public void test_saves_non_empty_list_of_tasks_to_file() {
        DemoApplication app = new DemoApplication();
        String newTaskJson = "{\"taskdescription\":\"New Task\"}";
        app.addTask(newTaskJson);
    
        app.saveTasks();
    
        File file = new File(app.filePath);
        assertTrue(file.exists() && file.length() > 3);
    }
}


