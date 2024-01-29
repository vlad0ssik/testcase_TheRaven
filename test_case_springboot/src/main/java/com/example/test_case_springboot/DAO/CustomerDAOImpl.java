package com.example.test_case_springboot.DAO;

import com.example.test_case_springboot.entity.Customer;
import com.example.test_case_springboot.entity.CustomerAdapter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
    @Autowired
    private EntityManager entityManager;


    @Override
    public List<Customer> getAllCustomers() {
        Query<CustomerAdapter> query;
        try (Session session = entityManager.unwrap(Session.class)) {
            query = session.createQuery("SELECT c FROM CustomerAdapter c WHERE c.isActive = true", CustomerAdapter.class);
        }
        List<Customer> allCustomers = new ArrayList<>();
        for (CustomerAdapter customerAdapter:  query.getResultList()){
            allCustomers.add(new Customer(customerAdapter));
        }
        return allCustomers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        try (Session session = entityManager.unwrap(Session.class)) {
            CustomerAdapter customerAdapter = getCustomerAdapterEntity(customer.getId());
            if (customerAdapter == null) {
                System.out.println(customerAdapter);
                CustomerAdapter newCustomerAdapter = new CustomerAdapter(customer);
                newCustomerAdapter.setActive(true);
                newCustomerAdapter.setCreatedManually();
                newCustomerAdapter.setUpdatedManually();
                session.saveOrUpdate(newCustomerAdapter);
            } else {
                customerAdapter.setFullName(customer.getFullName());
                customerAdapter.setPhone(customer.getPhone());
                customerAdapter.setUpdatedManually();
                session.saveOrUpdate(customerAdapter);
            }
        }
    }

    public CustomerAdapter getCustomerAdapterEntity(int id) {
        CustomerAdapter customer;
        try (Session session = entityManager.unwrap(Session.class)) {
            customer = session.get(CustomerAdapter.class, id);
        }
        return customer;
    }

    @Override
    public Customer getCustomer(int id) {
        Customer customer;
        try (Session session = entityManager.unwrap(Session.class)) {
            customer = session.get(Customer.class, id);
        }
        return customer;
    }

    @Override
    public void deleteEmployee(int id) {
        try (Session session = entityManager.unwrap(Session.class)) {
            CustomerAdapter customer = getCustomerAdapterEntity(id);
            customer.setActive(false);
            session.saveOrUpdate(customer);
        }
    }
}
