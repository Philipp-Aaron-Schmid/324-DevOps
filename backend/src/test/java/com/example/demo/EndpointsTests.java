package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

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
    public void testGetTasksEndpoint() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetDeleteEndpoint() throws Exception {
        mockMvc.perform(get("/delete"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetEditEndpoint() throws Exception {
        mockMvc.perform(get("/edit"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetHistoryEndpoint() throws Exception {
        mockMvc.perform(get("/history"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPostTasksEndpoint() throws Exception {
        String jsonContent = "{ \"taskName\": \"New Task\" }";

        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isCreated());
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

        mockMvc.perform(post("/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testPostHistoryEndpoint() throws Exception {
        String jsonContent = "{ \"id\": \"123\" }";

        mockMvc.perform(post("/history")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testTasksEndpointResponseContent() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskName", is("expectedTaskName")));
    }

    @Test
    public void testDeleteEndpointResponseContent() throws Exception {
        mockMvc.perform(get("/delete"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("expectedDeleteMessage")));
    }

    @Test
    public void testEditEndpointResponseContent() throws Exception {
        mockMvc.perform(get("/edit"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskName", is("expectedUpdatedTaskName")));
    }

    @Test
    public void testHistoryEndpointResponseContent() throws Exception {
        mockMvc.perform(get("/history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("expectedHistoryId")));
    }
}