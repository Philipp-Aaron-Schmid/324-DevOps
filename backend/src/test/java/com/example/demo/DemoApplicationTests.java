package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        assertTrue(true, "alles gut");
    }

	@Test
	public void test_adding_unique_task_increases_list_size() {
		DemoApplication app = new DemoApplication();
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
        DemoApplication app = new DemoApplication();
        String newTaskJson = "{\"taskdescription\":\"New Task\"}";
        app.addTask(newTaskJson);
        int initialSize = app.getTasks().size();
        app.addTask(newTaskJson);
        assertEquals(initialSize, app.getTasks().size());
    }

}
