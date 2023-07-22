package br.com.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class TransactionModelTest {

     @Test
    public void testAttributeValidation() {
        Long payer = 100L;
        Long payee = 200L;
        Double value = 50.0;

        TransactionModel transaction = new TransactionModel(payer, payee, value);

        assertNotNull(transaction.getPayer());
        assertNotNull(transaction.getPayee());
        assertNotNull(transaction.getValue());

        assertEquals(payer, transaction.getPayer());
        assertEquals(payee, transaction.getPayee());
        assertEquals(value, transaction.getValue(), 0.001);
    }

    @Test
    public void testGettersAndSetters() {
        TransactionModel transaction = new TransactionModel(100L, 200L, 50.0);

        Long id = 1L;
        Long payer = 300L;
        Long payee = 400L;
        Double value = 75.0;

        transaction.setId(id);
        transaction.setPayer(payer);
        transaction.setPayee(payee);
        transaction.setValue(value);

        assertEquals(id, transaction.getId());
        assertEquals(payer, transaction.getPayer());
        assertEquals(payee, transaction.getPayee());
        assertEquals(value, transaction.getValue(), 0.001);
    }
}
