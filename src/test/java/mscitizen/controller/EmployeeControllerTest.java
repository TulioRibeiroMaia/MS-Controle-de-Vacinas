package mscitizen.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import mscitizen.config.security.JwtService;
import mscitizen.dto.request.EmployeeRequestDTO;
import mscitizen.dto.response.EmployeeResponseDTO;
import mscitizen.enums.UserRole;
import mscitizen.repository.TokenRepository;
import mscitizen.service.implementation.EmployeeServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URI;
import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    EmployeeController employeeController;

    @MockBean
    private EmployeeServiceImplementation employeeServiceImplementation;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TokenRepository tokenRepository;

    @MockBean
    private JwtService jwtService;

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
                .build();
    }

    @Test
    public void shouldSaveANewEmployee() throws Exception{
        URI uri = new URI("/funcionarios");
        EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO("25117428021", "Carlos Justiniano Ribeiro Chagas", "cchagas@email.com", "12345678", LocalDate.now(), UserRole.ADMIN);

        when(employeeServiceImplementation.save(any())).thenReturn(EmployeeResponseDTO.builder().build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void shouldNotFoundTheMethodSaveANewEmployee() throws Exception{
        URI uri = new URI("/funcionanrio");
        EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO("25117428021", "Carlos Justiniano Ribeiro Chagas", "cchagas@email.com", "12345678", LocalDate.now(), UserRole.ADMIN);

        when(employeeServiceImplementation.save(any())).thenReturn(EmployeeResponseDTO.builder().build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void shouldNotSaveANewEmployee() throws Exception{
        URI uri = new URI("/funcionarios");
        EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO("25117428021", "", "cchagas@email.com", "12345678", LocalDate.now(), UserRole.ADMIN);

        when(employeeServiceImplementation.save(any())).thenReturn(EmployeeResponseDTO.builder().build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void shouldSearchEmployeeByCPF() throws Exception{
        URI uri = new URI("/funcionarios/25117428021");

        when(employeeServiceImplementation.searchEmployee("25117428021")).thenReturn(EmployeeResponseDTO.builder().build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void shouldListEmployeesByFullname() throws Exception{
        URI uri = new URI("/funcionarios");

        when(employeeServiceImplementation.getEmployees("Francisco Vicente Zappa")).thenReturn(Collections.singletonList(EmployeeResponseDTO.builder().build()));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void shouldUpdateAEmployee() throws Exception{
        URI uri = new URI("/funcionarios/25117428021");
        EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO("25117428021", "Carlos Justiniano Ribeiro Chagas", "cchagas@email.com", "12345678", LocalDate.now(), UserRole.ADMIN);
        when(employeeServiceImplementation.updateEmployee("25117428021", employeeRequestDTO)).thenReturn(EmployeeResponseDTO.builder().build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void shouldDeleteAEmployee() throws Exception{
        URI uri = new URI("/funcionarios/25117428021");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }
}
