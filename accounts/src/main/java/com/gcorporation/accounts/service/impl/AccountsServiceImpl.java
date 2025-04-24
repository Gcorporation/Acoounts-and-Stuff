package com.gcorporation.accounts.service.impl;

import com.gcorporation.accounts.constants.AccountsConstants;
import com.gcorporation.accounts.dto.CustomerDto;
import com.gcorporation.accounts.entity.Accounts;
import com.gcorporation.accounts.entity.Customer;
import com.gcorporation.accounts.exception.CustomerAlreadyExistsException;
import com.gcorporation.accounts.mapper.CustomerMapper;
import com.gcorporation.accounts.repository.AccountsRepository;
import com.gcorporation.accounts.repository.CustomerRepository;
import com.gcorporation.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

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
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    + customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        System.out.println("get customerID: -------->" + customer.getCreatedAt());
        customer.setCreatedBy("Anonymous");
        System.out.println("get createdBy: ------>" + customer.getCreatedBy());
        // Log customer details before saving
        System.out.println("Creating customer: ------>" + customer);
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);

        return newAccount;
    }
}
