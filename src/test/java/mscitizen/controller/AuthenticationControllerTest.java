package mscitizen.controller;

import mscitizen.config.security.AuthenticationService;
import mscitizen.config.security.JwtService;
import mscitizen.dto.request.AuthenticationRequestDTO;
import mscitizen.dto.request.RegisterRequestDTO;
import mscitizen.dto.response.AuthenticationResponseDTO;
import mscitizen.repository.TokenRepository;
import mscitizen.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = AuthenticationController.class)
public class AuthenticationControllerTest {

    @InjectMocks
    AuthenticationController authenticationController;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private TokenRepository tokenRepository;

    @MockBean
    private JwtService jwtService;
    @Autowired
    MockMvc mockMvc;



    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController)
                .alwaysDo(print())
                .build();
    }

    @Test
    public void shouldRegisterUser() throws Exception {
        URI uri = new URI("/login/register");
        RegisterRequestDTO requestDTO = new RegisterRequestDTO();

        when(authenticationService.register(any())).thenReturn(AuthenticationResponseDTO.builder().build());

        String input = Utils.mapToString(requestDTO);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void shouldNotRegisterUser_NotFound() throws Exception {
        URI uri = new URI("/register");
        RegisterRequestDTO requestDTO = new RegisterRequestDTO();

        when(authenticationService.register(any())).thenReturn(AuthenticationResponseDTO.builder().build());

        String input = Utils.mapToString(requestDTO);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void shouldAuthenticateUser() throws Exception {
        URI uri = new URI("/login/authenticate");
        AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO();

        when(authenticationService.register(any())).thenReturn(AuthenticationResponseDTO.builder().build());

        String input = Utils.mapToString(requestDTO);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void shouldNotAuthenticateUser_NotFound() throws Exception {
        URI uri = new URI("/authenticate");
        AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO();

        when(authenticationService.register(any())).thenReturn(AuthenticationResponseDTO.builder().build());

        String input = Utils.mapToString(requestDTO);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void shouldRefreshToken() throws Exception {
        URI uri = new URI("/login/refresh-token");
        AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO();

        String input = Utils.mapToString(requestDTO);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }
}
