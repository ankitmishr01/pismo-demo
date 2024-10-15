package com.pismo.demo;

import com.pismo.demo.model.Account;
import com.pismo.demo.model.AccountType;
import com.pismo.demo.model.Transaction;
import com.pismo.demo.repository.TransactionRepository;
import com.pismo.demo.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {


    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    private Account testAccount;
    private Transaction testTransaction;

    @BeforeEach
    void setUp() {
        // Initialize test account
        testAccount = new Account();
        testAccount.setAccountId(1L);
        testAccount.setDocumentNumber("12345678900");

        // Initialize test transaction
        AccountType accountType = new AccountType();
        accountType.setTypeId(1L);
        accountType.setAccountType("Savings");

        testTransaction = new Transaction();
        testTransaction.setTransactionId(1L);
        testTransaction.setAccount(testAccount);
        testTransaction.setOperationType(accountType);
        testTransaction.setAmount(100.0);
    }

    @Test
    void testSaveTransaction_Success() {
        // Arrange: Mock repository behavior
        when(transactionRepository.save(any(Transaction.class))).thenReturn(testTransaction);

        // Act: Call the service method
        Transaction savedTransaction = transactionService.saveTransaction(testTransaction);

        // Assert: Verify the result
        assertNotNull(savedTransaction);
        assertEquals(100.0, savedTransaction.getAmount());

        // Verify repository interaction
        verify(transactionRepository, times(1)).save(testTransaction);
    }
}