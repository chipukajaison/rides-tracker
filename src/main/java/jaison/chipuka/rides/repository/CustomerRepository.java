package jaison.chipuka.rides.repository;

import jaison.chipuka.rides.model.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer createCustomer(Customer customer);
    Customer getCustomer(Long id);
    List<Customer> getCustomers();
    Customer updateCustomer(Customer customer);
    void updateCustomers(List<Customer> customers);
    void deleteCustomer(Long id);
}
