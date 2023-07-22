package br.com.demo.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.demo.exceptions.personal_exceptions.AuthorizationException;
import br.com.demo.exceptions.personal_exceptions.TransferAuthorizationException;
import br.com.demo.exceptions.personal_exceptions.UserNotFoundException;
import br.com.demo.model.TransactionModel;
import br.com.demo.model.UserModel;
import br.com.demo.service.ConsultaService;
import br.com.demo.service.TransactionService;
import br.com.demo.service.UserService;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest  {
    
    private TransactionController transactionController;
    private ConsultaService consultaService;
    private UserService userService;
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        consultaService = mock(ConsultaService.class);
        userService = mock(UserService.class);
        transactionService = mock(TransactionService.class);

        transactionController = new TransactionController(consultaService, userService, transactionService);
    }


    @Test
    void testTransferAuthorizationException() throws Exception {
        // Crie aqui os dados de teste, como transactionModel, payer e payee
        Long payerId = 1L;
        Long payeeId = 2L;
        Double amount = 100.0;
        TransactionModel transactionModel = new TransactionModel(payerId, payeeId, amount);

        // Simule o comportamento do consultaService.consultaAltorizacao()
        when(consultaService.consultaAltorizacao()).thenReturn("Autorizado");

        // Simule o comportamento do userService.findById() para o payer
        UserModel payer = new UserModel();
        payer.setTypeUser("lojista"); // Definindo o tipo do usuário como "lojista" (não comum)
        payer.setValue(500.0); // Definindo o valor do usuário
        when(userService.findById(payerId)).thenReturn(Optional.of(payer));

        // Simule o comportamento do userService.findById() para o payee
        UserModel payee = new UserModel();
        payee.setTypeUser("comum"); // Definindo o tipo do usuário como "comum"
        payee.setValue(200.0); // Definindo o valor do usuário
        when(userService.findById(payeeId)).thenReturn(Optional.of(payee));

        // Execute o teste, e deve lançar a exceção TransferAuthorizationException
        assertThrows(TransferAuthorizationException.class, () -> transactionController.transaction(transactionModel));
    }

    @Test
    void testUserNotFoundException() throws Exception {
        // Arrange
        TransactionModel transactionModel = new TransactionModel(1L, 2L, 100.0);

        when(consultaService.consultaAltorizacao()).thenReturn("Autorizado");
        when(userService.findById(1L)).thenReturn(Optional.empty());
        when(userService.findById(2L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> transactionController.transaction(transactionModel));
    }

    @Test
    void testAuthorizationException() throws Exception {
        // Arrange
        TransactionModel transactionModel = new TransactionModel(1L, 2L, 100.0);

        when(consultaService.consultaAltorizacao()).thenReturn("NaoAutorizado");

        // Act & Assert
        assertThrows(AuthorizationException.class, () -> transactionController.transaction(transactionModel));
    }

}