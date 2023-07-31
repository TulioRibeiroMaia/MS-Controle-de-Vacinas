package mscitizen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mscitizen.config.security.JwtService;
import mscitizen.dto.request.CitizenRequestDTO;
import mscitizen.dto.response.CitizenResponseDTO;
import mscitizen.repository.TokenRepository;
import mscitizen.service.CitizenService;
import mscitizen.service.implementation.CitizenServiceImplementation;
import mscitizen.utils.Utils;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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
@WebMvcTest(controllers = CitizenController.class)
public class CitizenControllerTest {

    @InjectMocks
    CitizenController citizenController;

    @MockBean
    private CitizenServiceImplementation citizenServiceImplementation;

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
        mockMvc = MockMvcBuilders.standaloneSetup(citizenController)
                .build();
    }

    @Test
    public void shouldSaveANewCitizen() throws Exception{
        URI uri = new URI("/cidadaos");
        CitizenRequestDTO citizenRequestDTO = new CitizenRequestDTO("40040998037", "Francisco Vicente Zappa", LocalDate.now(), "318596810001714");

        when(citizenServiceImplementation.save(any())).thenReturn(CitizenResponseDTO.builder().build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(citizenRequestDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void shouldNotFoundTheMethodSaveANewCitizen() throws Exception{
        URI uri = new URI("/cidadao");
        CitizenRequestDTO citizenRequestDTO = new CitizenRequestDTO("40040998037", "Francisco Vicente Zappa", LocalDate.now(), "318596810001714");

        when(citizenServiceImplementation.save(any())).thenReturn(CitizenResponseDTO.builder().build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(citizenRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void shouldNotSaveANewCitizen() throws Exception{
        URI uri = new URI("/cidadaos");
        CitizenRequestDTO citizenRequestDTO = new CitizenRequestDTO("40040998037", "", LocalDate.now(), "318596810001714");

        when(citizenServiceImplementation.save(any())).thenReturn(CitizenResponseDTO.builder().build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(citizenRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void shouldSearchCitizenByCPF() throws Exception{
        URI uri = new URI("/cidadaos/40040998037");

        when(citizenServiceImplementation.searchCitizen("40040998037")).thenReturn(CitizenResponseDTO.builder().build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void shouldListAllCitizens() throws Exception{
        URI uri = new URI("/cidadaos");

        when(citizenServiceImplementation.getCitizens("Francisco Vicente Zappa", LocalDate.now(), LocalDate.now())).thenReturn(Collections.singletonList(CitizenResponseDTO.builder().build()));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void shouldUpdateACitizen() throws Exception{
        URI uri = new URI("/cidadaos/40040998037");
        CitizenRequestDTO citizenRequestDTO = new CitizenRequestDTO("40040998037", "Francisco Vicente Zappa", LocalDate.now(), "318596810001714");
        when(citizenServiceImplementation.updateCitizen("40040998037", citizenRequestDTO)).thenReturn(CitizenResponseDTO.builder().build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(citizenRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void shouldDeleteACitizen() throws Exception{
        URI uri = new URI("/cidadaos/40040998037");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }
}
