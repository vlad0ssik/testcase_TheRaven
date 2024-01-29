package com.example.test_case_springboot.service;

import com.example.test_case_springboot.entity.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> getAllCustomers();
    public void saveCustomer(Customer customer);
    public Customer getCustomer(int id);
    public void deleteEmployee(int id);
}
