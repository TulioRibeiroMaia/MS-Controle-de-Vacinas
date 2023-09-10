package mscitizen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mscitizen.config.security.JwtService;
import mscitizen.dto.request.EmployeeRequestDTO;
import mscitizen.dto.request.VaccineRequestDTO;
import mscitizen.dto.response.EmployeeResponseDTO;
import mscitizen.dto.response.VaccineResponseDTO;
import mscitizen.enums.UserRole;
import mscitizen.enums.VaccineName;
import mscitizen.repository.TokenRepository;
import mscitizen.service.implementation.EmployeeServiceImplementation;
import mscitizen.service.implementation.VaccineServiceImplementation;
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
@WebMvcTest(controllers = VaccineController.class)
public class VaccineControllerTest {
    @Autowired
    VaccineController vaccineController;

    @MockBean
    private VaccineServiceImplementation vaccineServiceImplementation;

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
        mockMvc = MockMvcBuilders.standaloneSetup(vaccineController)
                .build();
    }

    @Test
    public void shouldSaveANewVaccine() throws Exception{
        URI uri = new URI("/vacinas");
        VaccineRequestDTO vaccineRequestDTO = new VaccineRequestDTO(VaccineName.PFIZER,
                "BioNTech",
                LocalDate.now(),
                LocalDate.now(),
                "PFZ96R1");

        when(vaccineServiceImplementation.save(any())).thenReturn(VaccineResponseDTO.builder().build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vaccineRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void shouldNotFoundTheMethodSaveANewVaccine() throws Exception{
        URI uri = new URI("/vacina");
        VaccineRequestDTO vaccineRequestDTO = new VaccineRequestDTO(VaccineName.PFIZER,
                "BioNTech",
                LocalDate.now(),
                LocalDate.now(),
                "PFZ96R1");

        when(vaccineServiceImplementation.save(any())).thenReturn(VaccineResponseDTO.builder().build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vaccineRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void shouldNotSaveANewVaccine() throws Exception{
        URI uri = new URI("/vacinas");
        VaccineRequestDTO vaccineRequestDTO = new VaccineRequestDTO(VaccineName.PFIZER,
                "BioNTech",
                LocalDate.now(),
                LocalDate.now(),
                "");

        when(vaccineServiceImplementation.save(any())).thenReturn(VaccineResponseDTO.builder().build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vaccineRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void shouldSearchVaccineById() throws Exception{
        URI uri = new URI("/vacinas/1");

        when(vaccineServiceImplementation.searchVaccine(1L)).thenReturn(VaccineResponseDTO.builder().build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void shouldListVaccinesByLotNumberAndValidate() throws Exception{
        URI uri = new URI("/vacinas");

        when(vaccineServiceImplementation.getVaccines("20/10/2022", false)).thenReturn(Collections.singletonList(VaccineResponseDTO.builder().build()));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void shouldUpdateAVaccine() throws Exception{
        URI uri = new URI("/vacinas/1");
        VaccineRequestDTO vaccineRequestDTO = new VaccineRequestDTO(VaccineName.PFIZER,
                "BioNTech",
                LocalDate.now(),
                LocalDate.now(),
                "PFZ96R1");
        when(vaccineServiceImplementation.updateVaccine(1L, vaccineRequestDTO)).thenReturn(VaccineResponseDTO.builder().build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vaccineRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void shouldDeleteAVaccine() throws Exception{
        URI uri = new URI("/vacinas/1");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }
}
