package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
public class EndpointsTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetRootEndpoint() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPostTasksEndpoint() throws Exception {
        String jsonContent = "{ \"taskName\": \"New Task\" }";

        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testPostDeleteEndpoint() throws Exception {
        String jsonContent = "{ \"id\": \"123\" }";

        mockMvc.perform(post("/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testPostEditEndpoint() throws Exception {
        String jsonContent = "{ \"id\": \"123\", \"taskName\": \"Updated Task\" }";

        mockMvc.perform(get("/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testPostHistoryEndpoint() throws Exception {
        String jsonContent = "{ \"id\": \"123\" }";

        mockMvc.perform(get("/history")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}