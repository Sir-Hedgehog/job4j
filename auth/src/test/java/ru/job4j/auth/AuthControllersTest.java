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
import ru.job4j.auth.controller.PersonController;
import ru.job4j.auth.domain.Person;
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
 * @version 1.0
 * @since 05.09.2020
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthApp.class)
@AutoConfigureMockMvc
public class AuthControllersTest {

    @MockBean
    private PersonController controller;

    @Autowired
    private MockMvc mockMvc;

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    /**
     * Метод проверяет отображение всех существующих пользователей
     */

    @Test
    public void checkToGetAllUsers() throws Exception {
        Person person1 = new Person();
        person1.setId(1);
        person1.setLogin("herbivorous");
        person1.setPassword("apple");
        Person person2 = new Person();
        person2.setId(2);
        person2.setLogin("predator");
        person2.setPassword("meal");
        List<Person> persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);
        String inputInJson = this.mapToJson(persons);
        when(controller.findAll()).thenReturn(persons);
        MvcResult result = mockMvc
                .perform(get("/person/").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson, is(inputInJson));
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    /**
     * Метод проверяет поиск данных по идентификатору пользователя
     */

    @Test
    public void checkToFindOfUserById() throws Exception {
        Person person = new Person();
        person.setId(1);
        person.setLogin("herbivorous");
        person.setPassword("apple");
        String expectedInJson = this.mapToJson(person);
        when(controller.findById(1)).thenReturn(new ResponseEntity<>(person, HttpStatus.OK));
        MvcResult result = mockMvc
                .perform(get("/person/{id}", "1").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson, is(expectedInJson));
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    /**
     * Метод проверяет успешность создания данных нового пользователя
     */

    @Test
    public void checkToCreateLoginAndPassword() throws Exception {
        Person person = new Person();
        person.setId(1);
        person.setLogin("herbivorous");
        person.setPassword("apple");
        String inputInJson = this.mapToJson(person);
        when(controller.create(any(Person.class))).thenReturn(new ResponseEntity<>(person, HttpStatus.CREATED));
        MvcResult result = mockMvc
                .perform(post("/person/")
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
     * Метод проверяет обновление пользовательских данных
     */

    @Test
    public void checkToUpdatePerson() throws Exception {
        Person person = new Person();
        person.setId(1);
        person.setLogin("herbivorous");
        person.setPassword("apple");
        String expectedInJson = this.mapToJson(person);
        when(controller.update(person)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        MvcResult result = mockMvc
                .perform(put("/person/")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(expectedInJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    /**
     * Метод проверяет удаление данных пользователя
     */

    @Test
    public void checkToDeletePerson() throws Exception {
        Person person = new Person();
        person.setId(1);
        person.setLogin("herbivorous");
        person.setPassword("apple");
        when(controller.delete(1)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        MvcResult result = mockMvc
                .perform(delete("/person/{id}", "1").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
