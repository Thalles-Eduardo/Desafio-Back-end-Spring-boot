package br.com.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import br.com.demo.controller.TransactionController;
import br.com.demo.exceptions.personal_exceptions.AuthorizationException;
import br.com.demo.exceptions.personal_exceptions.BalanceInsufficientException;
import br.com.demo.exceptions.personal_exceptions.UserNotFoundException;
import br.com.demo.model.TransactionModel;
import br.com.demo.model.UserModel;

import br.com.demo.service.ConsultaService;
import br.com.demo.service.TransactionService;
import br.com.demo.service.UserService;

import java.util.Optional;


@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Mock
    private ConsultaService consultaService;

    @Mock
    private UserService userService;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController userController;

    private UserModel commonUser;
    private UserModel merchantUser;

    @BeforeEach
    public void setup() {
        commonUser = new UserModel(1L, "John Doe", "12345678901", "john.doe@example.com", "password", 1000.0, "comum");
        merchantUser = new UserModel(2L, "Jane Smith", "98765432101", "jane.smith@example.com", "password", 2000.0, "lojista");
    }

    @Test
    public void testSuccessfulTransaction() throws Exception {
        when(consultaService.consultaAltorizacao()).thenReturn("Autorizado");
        when(consultaService.consultaNotify()).thenReturn("Success");

        when(userService.findById(1L)).thenReturn(Optional.of(commonUser));
        when(userService.findById(2L)).thenReturn(Optional.of(merchantUser));

        TransactionModel transactionModel = new TransactionModel(1L, 2L, 500.0);
        ResponseEntity<String> response = userController.transaction(transactionModel);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"message\": \"Transferência autorizada e email enviado com sucesso.\"}", response.getBody());

        verify(userService, times(2)).save(any(UserModel.class));
        verify(transactionService, times(1)).save(any(TransactionModel.class));
    }

    @Test
    public void testInsufficientBalanceTransaction() throws Exception {
        when(consultaService.consultaAltorizacao()).thenReturn("Autorizado");
        when(consultaService.consultaNotify()).thenReturn("Success");

        when(userService.findById(1L)).thenReturn(Optional.of(commonUser));
        when(userService.findById(2L)).thenReturn(Optional.of(merchantUser));

        TransactionModel transactionModel = new TransactionModel(1L, 2L, 1500.0);
        try {
            userController.transaction(transactionModel);
        } catch (BalanceInsufficientException ex) {
        }

        verify(userService, times(0)).save(any(UserModel.class));
        verify(transactionService, times(0)).save(any(TransactionModel.class));
    }

    @Test
    public void testUnauthorizedTransfer() throws Exception {

        when(consultaService.consultaAltorizacao()).thenReturn("Não Autorizado");

        TransactionModel transactionModel = new TransactionModel(1L, 2L, 500.0);
        try {
            userController.transaction(transactionModel);
        } catch (AuthorizationException ex) {
        }

        verify(userService, times(0)).save(any(UserModel.class));
        verify(transactionService, times(0)).save(any(TransactionModel.class));
    }

    @Test
    public void testUserNotFound() throws Exception {
        when(consultaService.consultaAltorizacao()).thenReturn("Autorizado");
        when(consultaService.consultaNotify()).thenReturn("Success");

        when(userService.findById(1L)).thenReturn(Optional.empty());
        when(userService.findById(2L)).thenReturn(Optional.of(merchantUser));

        TransactionModel transactionModel = new TransactionModel(1L, 2L, 500.0);
        try {
            userController.transaction(transactionModel);
        } catch (UserNotFoundException ex) {
        }

        verify(userService, times(0)).save(any(UserModel.class));
        verify(transactionService, times(0)).save(any(TransactionModel.class));
    }

}

