package com.gcorporation.accounts.service.impl;

import com.gcorporation.accounts.dto.CustomerDto;
import com.gcorporation.accounts.repository.AccountsRepository;
import com.gcorporation.accounts.repository.CustomerRepository;
import com.gcorporation.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {

    }
}
