package br.com.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.demo.repository.UserRepository;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@ExtendWith(MockitoExtension.class)
public class UserModelTest {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Mock
    private UserRepository userRepositoryMock;
    

    @Test
    public void testValidationAttributes() {
        UserModel userModel = new UserModel(null, "", "", "", "", null, null);

        // Validar as anotações de validação diretamente nos atributos
        assertFalse(validator.validateProperty(userModel, "name").isEmpty());
        assertFalse(validator.validateProperty(userModel, "cpf").isEmpty());
        assertFalse(validator.validateProperty(userModel, "email").isEmpty());
        assertFalse(validator.validateProperty(userModel, "password").isEmpty());
        assertFalse(validator.validateProperty(userModel, "value").isEmpty());
        assertFalse(validator.validateProperty(userModel, "typeUser").isEmpty());

        userModel.setName("Thalles");
        userModel.setCpf("999-999-999.99");
        userModel.setEmail("thalles@gmail.com");
        userModel.setPassword("password");
        userModel.setValue(100.00);
        userModel.setTypeUser("comum");

        // Validar novamente após definir valores válidos, se retorna vazio é por que os valores foram definidos corretamente
        assertTrue(validator.validate(userModel).isEmpty());
    }


    @Test
    public void testGettersAndSetters() {
        UserModel userModel = new UserModel(null, "John Doe", "12345678900", "john@example.com", "password", 100.0, "comum");
        
        assertEquals("John Doe", userModel.getName());
        assertEquals("12345678900", userModel.getCpf());
        assertEquals("john@example.com", userModel.getEmail());
        assertEquals("password", userModel.getPassword());
        assertEquals(100.0, userModel.getValue());
        assertEquals("comum", userModel.getTypeUser());
        
        userModel.setName("Jane Doe");
        userModel.setCpf("98765432100");
        userModel.setEmail("jane@example.com");
        userModel.setPassword("newpassword");
        userModel.setValue(200.0);
        userModel.setTypeUser("comum");
        
        assertEquals("Jane Doe", userModel.getName());
        assertEquals("98765432100", userModel.getCpf());
        assertEquals("jane@example.com", userModel.getEmail());
        assertEquals("newpassword", userModel.getPassword());
        assertEquals(200.0, userModel.getValue());
        assertEquals("comum", userModel.getTypeUser());
    }


    @Test
    public void testUniqueEmailAndCpf() {
        // Cenário
        String existingEmail = "existing@example.com";
        String existingCpf = "12345678900";

        // Simulando o comportamento do repositório
        when(userRepositoryMock.existsByEmail(existingEmail)).thenReturn(true);
        when(userRepositoryMock.existsByCpf(existingCpf)).thenReturn(true);

        // Ação
        boolean emailExists = userRepositoryMock.existsByEmail(existingEmail);
        boolean cpfExists = userRepositoryMock.existsByCpf(existingCpf);

        // Verificação
        assertTrue(emailExists); // Deve retornar true, pois o email já existe no banco de dados
        assertTrue(cpfExists); // Deve retornar true, pois o cpf já existe no banco de dados
    }

    @Test
    public void testUniqueEmailAndCpf_NonExisting() {
        // Cenário
        String nonExistingEmail = "nonexisting@example.com";
        String nonExistingCpf = "98765432100";

        // Simulando o comportamento do repositório
        when(userRepositoryMock.existsByEmail(nonExistingEmail)).thenReturn(false);
        when(userRepositoryMock.existsByCpf(nonExistingCpf)).thenReturn(false);

        // Ação
        boolean emailExists = userRepositoryMock.existsByEmail(nonExistingEmail);
        boolean cpfExists = userRepositoryMock.existsByCpf(nonExistingCpf);

        // Verificação
        assertFalse(emailExists); // Deve retornar false, pois o email não existe no banco de dados
        assertFalse(cpfExists); // Deve retornar false, pois o cpf não existe no banco de dados
    }
}

