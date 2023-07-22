package br.com.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.demo.model.TransactionModel;
import br.com.demo.repository.TransactionRepository;

public class TransactionServiceTest {

    private TransactionService transactionService;
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        transactionRepository = mock(TransactionRepository.class);
        transactionService = new TransactionService(transactionRepository);
    }

    @Test
    void testSave() {
        TransactionModel transactionModel = new TransactionModel();
        when(transactionRepository.save(transactionModel)).thenReturn(transactionModel);

        TransactionModel result = transactionService.save(transactionModel);

        assertEquals(transactionModel, result);
        verify(transactionRepository).save(transactionModel);
    }
}
