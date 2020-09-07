package ru.job4j.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.job4j.auth.controller.EmployeeController;
import ru.job4j.auth.domain.Employee;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 07.09.2020
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthApp.class)
@AutoConfigureMockMvc
public class AuthControllersTest {

    @MockBean
    private EmployeeController controller;

    @Autowired
    private MockMvc mockMvc;

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    /**
     * Метод проверяет отображение всех существующих работников
     */

    @Test
    public void checkToGetAllEmployees() throws Exception {
        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setName("John");
        employee1.setSurname("Miloshevic");
        Employee employee2 = new Employee();
        employee2.setId(2);
        employee2.setName("Ruben");
        employee2.setSurname("Magomedov");
        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        String inputInJson = this.mapToJson(employees);
        when(controller.findAll()).thenReturn(employees);
        MvcResult result = mockMvc
                .perform(get("/employee/").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson, is(inputInJson));
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    /**
     * Метод проверяет поиск данных по идентификатору работника
     */

    @Test
    public void checkToFindOfEmployeeById() throws Exception {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("John");
        employee.setSurname("Miloshevic");
        String expectedInJson = this.mapToJson(employee);
        when(controller.findById(1)).thenReturn(new ResponseEntity<>(employee, HttpStatus.OK));
        MvcResult result = mockMvc
                .perform(get("/employee/{id}", "1").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson, is(expectedInJson));
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    /**
     * Метод проверяет успешность создания данных нового работника
     */

    @Test
    public void checkToCreateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("John");
        employee.setSurname("Miloshevic");
        String inputInJson = this.mapToJson(employee);
        when(controller.create(any(Employee.class))).thenReturn(new ResponseEntity<>(employee, HttpStatus.CREATED));
        MvcResult result = mockMvc
                .perform(post("/employee/")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(inputInJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson, is(inputInJson));
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    /**
     * Метод проверяет обновление данных существующего работника
     */

    @Test
    public void checkToUpdateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("John");
        employee.setSurname("Miloshevic");
        String expectedInJson = this.mapToJson(employee);
        when(controller.update(employee)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        MvcResult result = mockMvc
                .perform(put("/employee/")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(expectedInJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    /**
     * Метод проверяет удаление данных уволенного работника
     */

    @Test
    public void checkToDeleteEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("John");
        employee.setSurname("Miloshevic");
        when(controller.delete(1)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        MvcResult result = mockMvc
                .perform(delete("/employee/{id}", "1").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
