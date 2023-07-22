package br.com.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.demo.model.UserModel;
import br.com.demo.repository.UserRepository;


public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void testSave() {
        UserModel user = new UserModel();
        when(userRepository.save(user)).thenReturn(user);

        UserModel result = userService.save(user);

        assertEquals(user, result);
        verify(userRepository).save(user);
    }

    @Test
    void testCreateAndFindAll() {
        UserModel user = new UserModel();
        List<UserModel> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findAll()).thenReturn(userList);

        List<UserModel> result = userService.createAndFindAll(user);

        assertEquals(userList, result);
        verify(userRepository).save(user);
        verify(userRepository).findAll();
    }

    @Test
    void testUpdateAndFindAll() {
        UserModel user = new UserModel();
        List<UserModel> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findAll()).thenReturn(userList);

        List<UserModel> result = userService.updateAndFindAll(user);

        assertEquals(userList, result);
        verify(userRepository).save(user);
        verify(userRepository).findAll();
    }

    @Test
    void testExistsByEmail_EmailExists() {
        when(userRepository.existsByEmail(any())).thenReturn(true);

        boolean result = userService.existsByEmail("test@example.com");

        assertTrue(result);
    }

    @Test
    void testExistsByEmail_EmailDoesNotExist() {
        when(userRepository.existsByEmail(any())).thenReturn(false);

        boolean result = userService.existsByEmail("test@example.com");

        assertFalse(result);
    }

    @Test
    void testExistsByCpf_CpfExists() {
        when(userRepository.existsByCpf(any())).thenReturn(true);

        boolean result = userService.existsByCpf("123456789");

        assertTrue(result);
    }

    @Test
    void testExistsByCpf_CpfDoesNotExist() {
        when(userRepository.existsByCpf(any())).thenReturn(false);

        boolean result = userService.existsByCpf("123456789");

        assertFalse(result);
    }

    @Test
    void testFindById_UserExists() {
        UserModel user = new UserModel();
        user.setId(1L);
        user.setName("John Doe");
        Optional<UserModel> optionalUser = Optional.of(user);
        when(userRepository.findById(1L)).thenReturn(optionalUser);

        Optional<UserModel> result = userService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }

    @Test
    void testFindById_UserDoesNotExist() {
        Optional<UserModel> emptyUser = Optional.empty();
        when(userRepository.findById(1L)).thenReturn(emptyUser);

        Optional<UserModel> result = userService.findById(1L);

        assertFalse(result.isPresent());
    }
    public boolean existsByEmail(String email) {
        return false;
    }

    public boolean existsByCpf(String cpf) {
        return false;
    }

    public Optional<UserModel> findById(long id) {
        return null;
    }
}
