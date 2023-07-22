package br.com.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.demo.model.UserModel;
import br.com.demo.service.UserService;

public class UserControllerTest {
    private UserService userService;
    private UserController userController;

    @BeforeEach
    public void setup() {
        // Criação do mock para UserService
        userService = mock(UserService.class);

        // Instanciação da classe UserController com o mock de UserService
        userController = new UserController(userService);
    }

    @Test
    public void testCreate() {
        // Dados de entrada
        UserModel user = new UserModel("John Doe", 30);

        // Configuração do comportamento do mock
        List<UserModel> expectedResult = new ArrayList<>();
        expectedResult.add(user);
        when(userService.createAndFindAll(user)).thenReturn(expectedResult);

        // Chamada do método do controlador
        List<UserModel> result = userController.create(user);

        // Verificação dos resultados
        assertEquals(expectedResult, result);
        verify(userService, times(1)).createAndFindAll(user);
    }

    @Test
    public void testUpdate() {
        // Dados de entrada
        UserModel user = new UserModel("Jane Smith", 25);

        // Configuração do comportamento do mock
        List<UserModel> expectedResult = new ArrayList<>();
        expectedResult.add(user);
        when(userService.updateAndFindAll(user)).thenReturn(expectedResult);

        // Chamada do método do controlador
        List<UserModel> result = userController.update(user);

        // Verificação dos resultados
        assertEquals(expectedResult, result);
        verify(userService, times(1)).updateAndFindAll(user);
    }

    @Test
    public void testList() {
        // Configuração do comportamento do mock
        List<UserModel> expectedResult = new ArrayList<>();
        expectedResult.add(new UserModel("John", 30));
        expectedResult.add(new UserModel("Jane", 25));
        when(userService.list()).thenReturn(expectedResult);

        // Chamada do método do controlador
        List<UserModel> result = userController.list();

        // Verificação dos resultados
        assertEquals(expectedResult, result);
        verify(userService, times(1)).list();
    }
}
