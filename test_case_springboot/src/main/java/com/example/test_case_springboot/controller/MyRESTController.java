package com.example.test_case_springboot.controller;

import com.example.test_case_springboot.DAO.CustomerDAO;
import com.example.test_case_springboot.DAO.CustomerDAOImpl;
import com.example.test_case_springboot.entity.Customer;
import com.example.test_case_springboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {
    @Autowired
    private CustomerService customerService; // <<<<-------------------------------

    @GetMapping("/customers")
    public List<Customer> showAllCustomers() {
        List<Customer> allCustomers = customerService.getAllCustomers();
        return allCustomers;
    }
    @GetMapping("/customers/{id}")
    public Customer showCustomer(@PathVariable int id) {
        Customer customer = customerService.getCustomer(id);
        return customer;
    }
    @PostMapping("/customers")
    public Customer addNewCustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
        return customer;
    }
    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable int id,@RequestBody Customer customer){
        customer.setId(id);
        customerService.saveCustomer(customer);
        return customer;
    }
    @DeleteMapping("/customers/{id}")
    public String updateCustomer(@PathVariable int id){
        Customer customer = customerService.getCustomer(id);
        if(customer != null ) {
        customerService.deleteEmployee(id);
        return "customer with id " + id + " was deleted";}
        return "No customer with such id";
    }

}
