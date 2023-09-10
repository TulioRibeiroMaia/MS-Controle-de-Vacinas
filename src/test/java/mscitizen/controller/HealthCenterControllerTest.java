package mscitizen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mscitizen.config.security.JwtService;
import mscitizen.dto.request.EmployeeRequestDTO;
import mscitizen.dto.request.HealthCenterRequestDTO;
import mscitizen.dto.response.EmployeeResponseDTO;
import mscitizen.dto.response.HealthCenterResponseDTO;
import mscitizen.enums.State;
import mscitizen.enums.UserRole;
import mscitizen.repository.TokenRepository;
import mscitizen.service.implementation.EmployeeServiceImplementation;
import mscitizen.service.implementation.HealthCenterImplementation;
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
@WebMvcTest(controllers = HealthCenterController.class)
public class HealthCenterControllerTest {
    @Autowired
    HealthCenterController healthCenterController;

    @MockBean
    private HealthCenterImplementation healthCenterImplementation;

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
        mockMvc = MockMvcBuilders.standaloneSetup(healthCenterController)
                .build();
    }

    @Test
    public void shouldSaveANewHealthCenter() throws Exception{
        URI uri = new URI("/unidades");
        HealthCenterRequestDTO healthCenterRequestDTO = new HealthCenterRequestDTO("2695464","Centro de Saúde Padre Eustáquio","Belo Horizonte", State.MG);

        when(healthCenterImplementation.save(any())).thenReturn(HealthCenterResponseDTO.builder().build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(healthCenterRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void shouldNotFoundTheMethodSaveANewHealthCenter() throws Exception{
        URI uri = new URI("/unidade");
        HealthCenterRequestDTO healthCenterRequestDTO = new HealthCenterRequestDTO("2695464","Centro de Saúde Padre Eustáquio","Belo Horizonte", State.MG);

        when(healthCenterImplementation.save(any())).thenReturn(HealthCenterResponseDTO.builder().build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(healthCenterRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void shouldNotSaveANewHealthCenter() throws Exception{
        URI uri = new URI("/unidades");
        HealthCenterRequestDTO healthCenterRequestDTO = new HealthCenterRequestDTO("2695464","","Belo Horizonte", State.MG);

        when(healthCenterImplementation.save(any())).thenReturn(HealthCenterResponseDTO.builder().build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(healthCenterRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void shouldSearchHealthCenterByCNES() throws Exception{
        URI uri = new URI("/unidades/2695464");

        when(healthCenterImplementation.searchHealthCenter("2695464")).thenReturn(HealthCenterResponseDTO.builder().build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void shouldListHealthCenterByNameByStateByCity() throws Exception{
        URI uri = new URI("/unidades");

        when(healthCenterImplementation.getHealthCenters("Centro de Saúde Padre Eustáquio","MG", "Belo Horizonte"))
                .thenReturn(Collections.singletonList(HealthCenterResponseDTO.builder().build()));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void shouldUpdateAHealthCenter() throws Exception{
        URI uri = new URI("/unidades/2695464");
        HealthCenterRequestDTO healthCenterRequestDTO = new HealthCenterRequestDTO("2695464","Centro de Saúde Padre Eustáquio","Belo Horizonte", State.MG);
        when(healthCenterImplementation.updateHealthCenter("25117428021", healthCenterRequestDTO)).thenReturn(HealthCenterResponseDTO.builder().build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(healthCenterRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void shouldDeleteAHealthCenter() throws Exception{
        URI uri = new URI("/unidades/2695464");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }
}
