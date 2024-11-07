package com.pismo.demo;

import com.pismo.demo.exception.CustomException;
import com.pismo.demo.entity.Account;
import com.pismo.demo.entity.AccountType;
import com.pismo.demo.model.AccountModel;
import com.pismo.demo.repository.AccountRepository;
import com.pismo.demo.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountService accountService;


    private Account testAccount;

    @BeforeEach
    void setUp() {
        // Initialize test account
        testAccount = new Account();
        testAccount.setAccountId(1L);
        testAccount.setDocumentNumber("12345678900");

        // Initialize test
        AccountType accountType = new AccountType();
        accountType.setTypeId(1L);
        accountType.setAccountType("Savings");
    }

    @Test
    void testCreateAccount_Success() {
        // Arrange: Mock repository behavior
        when(accountRepository.save(any(Account.class))).thenReturn(testAccount);
//
        // Act: Call the service method
        AccountModel savedAccount = new AccountModel();
        savedAccount.setAccountId(testAccount.getAccountId());
        savedAccount.setDocumentNumber(testAccount.getDocumentNumber());

        savedAccount = accountService.saveAccountDetails(savedAccount);

        // Assert: Verify the result
        assertNotNull(savedAccount);
        assertEquals("12345678900", savedAccount.getDocumentNumber());

        // Verify repository interaction
        verify(accountRepository, times(1)).save(testAccount);
    }

    @Test
    void testGetAccount_AccountNotFound() {
        // Arrange: Mock repository to return empty optional
        when(accountRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert: Expect CustomException to be thrown
        CustomException exception = assertThrows(CustomException.class,
                () -> accountService.getAcccountDetails(1L));

        assertEquals("Account not found", exception.getMessage());

        // Verify repository interaction
        verify(accountRepository, times(1)).findById(1L);
    }

}