package com.springtestjunit.Mockito;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springtestjunit.Mockito.model.Employee;
import com.springtestjunit.Mockito.model.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MockitoApplicationTests {

    ObjectMapper objectMapper = new ObjectMapper();
    //@Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;



    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @org.junit.Test
    public void addEmployeeTest() throws Exception {
        Employee employee = new Employee();
        employee.setDept("IT");
        employee.setName("Pavan");
        String jsonRequest = objectMapper.writeValueAsString(employee);

        MvcResult result = mockMvc.perform(post("/api/addEmployee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk()).andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Response response = objectMapper.readValue(resultContent, Response.class);
        Assert.assertTrue(response.getStatus() == Boolean.TRUE);
    }

    @Test
    public void getEmployeeTest() throws Exception {

        MvcResult result = mockMvc.perform(get("/api/getEmployee"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        Response response = objectMapper.readValue(resultContent, Response.class);
        Assert.assertTrue(response.getStatus() == Boolean.TRUE);
    }
}
