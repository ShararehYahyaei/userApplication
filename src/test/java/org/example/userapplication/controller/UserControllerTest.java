package org.example.userapplication.controller;


import org.example.userapplication.model.User;
import org.example.userapplication.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;




@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void testGelAllUsers() throws Exception {
        User firstUser = User.builder()
                .firstName("amir")
                .lastName("soleimany")
                .email("soleimany@gmail.com")
                .password("123456")
                .build();
        User secondUser = User.builder()
                .firstName("mohammad")
                .lastName("yahyaei")
                .email("yahyaei@gmail.com")
                .password("458")
                .build();

        List<User> users = List.of(firstUser, secondUser);


        given(userService.findAllUsers()).willReturn(users);

        mockMvc.perform(get("/user/getAllUsers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(users.size()))
                .andExpect(jsonPath("$[0].firstName").value("amir"))
                .andExpect(jsonPath("$[1].email").value("yahyaei@gmail.com"));


    }
    @Test
    void testCreateUser() throws Exception {
        User user = User.builder()
                .firstName("Sara")
                .lastName("Ahmadi")
                .email("sara@gmail.com")
                .password("12345")
                .build();

        given(userService.createUser(Mockito.any(User.class))).willReturn(user);

        mockMvc.perform(post("/user/createUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                     {
                       "firstName": "Sara",
                       "lastName": "Ahmadi",
                       "email": "sara@gmail.com",
                       "password": "12345"
                     }
                     """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Sara"))
                .andExpect(jsonPath("$.email").value("sara@gmail.com"));
    }



}
