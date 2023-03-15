package br.com.j38.api.resources.exception;

import br.com.j38.api.service.exceptions.DataIntegretyViolationException;
import br.com.j38.api.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    public static final String EMAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema";
    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("When Object not found then return a Response Entity")
    void whenObjectNotFoundThenReturnAResponseEntity() {
        ResponseEntity<StandardError> response = exceptionHandler
                .objectNotFound(
                        new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(OBJETO_NAO_ENCONTRADO, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
        assertEquals(LocalDateTime.class, response.getBody().getTimestamp().getClass());
        assertNotEquals("http://localhost/user/1", response.getBody().getPath());
    }

    @Test
    @DisplayName("When Data Integrity Violation then return a Response Entity")
    void whenDataIntegrityViolationThenReturnAResponseEntity() {
        ResponseEntity<StandardError> response = exceptionHandler
                .dataIntegretyViolationException(
                        new DataIntegretyViolationException(EMAIL_JA_CADASTRADO_NO_SISTEMA),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(EMAIL_JA_CADASTRADO_NO_SISTEMA, response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
    }
}