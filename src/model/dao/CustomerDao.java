package model.dao;

import model.entity.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> queryAllCustomers();
    int deleteCustomerById(int id);
    int updateCustomer(int id);
    int addNewCustomer(Customer customer);
    Customer findCustomerById(Integer id);
}
