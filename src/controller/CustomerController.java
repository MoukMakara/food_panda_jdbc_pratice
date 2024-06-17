package controller;

import mapper.Mapper;
import model.dto.CreateCustomerDto;
import model.dto.CustomerDto;
import model.entity.Customer;
import model.service.CustomerService;

import java.util.List;

public class CustomerController {
    private final CustomerService customerService = new CustomerService();

    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public void addNewCustomer(CreateCustomerDto createCustomerDto) {
        customerService.addNewCustomer(createCustomerDto);
    }

    public void updateCustomer(Integer id) {
        customerService.updateCustomer(id);
    }

    public void deleteCustomer(Integer id) {
        customerService.deleteCustomer(id);
    }

    public CustomerDto getCustomerDto(Integer id) {
        Customer customer = customerService.getCustomerById(id);
        return Mapper.fromCustomerToCustomerDto(customer);
    }
}
