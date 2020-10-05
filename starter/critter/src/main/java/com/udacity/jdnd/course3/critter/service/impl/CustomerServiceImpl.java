package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer saveCustomer(Customer customer, List<Long> petIds) {
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }
}
