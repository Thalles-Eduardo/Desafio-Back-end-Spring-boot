package br.com.demo.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.demo.model.TransactionModel;
import br.com.demo.model.UserModel;

public class RepositoryTests {
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        transactionRepository = mock(TransactionRepository.class);
        userRepository = mock(UserRepository.class);
    }

    @Test
    public void testExistsByCpf() {
        String cpf = "12345678901";
        when(userRepository.existsByCpf(cpf)).thenReturn(true);
        assertTrue(userRepository.existsByCpf(cpf));
    }

    @Test
    public void testExistsByEmail() {
        String email = "test@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(false);
        assertFalse(userRepository.existsByEmail(email));
    }

    @Test
    public void testFindByEmail() {
        String email = "test@example.com";
        UserModel user = new UserModel();
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        Optional<UserModel> foundUser = userRepository.findByEmail(email);
        assertTrue(foundUser.isPresent());
        assertTrue(foundUser.get().getEmail().equals(email));
    }

    @Test
    public void testFindByCpf() {
        String cpf = "12345678901";
        UserModel user = new UserModel();
        user.setCpf(cpf);
        when(userRepository.findByCpf(cpf)).thenReturn(Optional.of(user));
        Optional<UserModel> foundUser = userRepository.findByCpf(cpf);
        assertTrue(foundUser.isPresent());
        assertTrue(foundUser.get().getCpf().equals(cpf));
    }

    @Test
    public void testSaveTransaction() {
        TransactionModel transaction = new TransactionModel();
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        TransactionModel savedTransaction = transactionRepository.save(transaction);
        assertTrue(savedTransaction.equals(transaction));
    }
}
