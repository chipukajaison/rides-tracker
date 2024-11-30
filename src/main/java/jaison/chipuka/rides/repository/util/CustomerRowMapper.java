package jaison.chipuka.rides.repository.util;

import jaison.chipuka.rides.model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getLong("id"));
        customer.setUsername(rs.getString("username"));
        customer.setFirstname(rs.getString("firstname"));
        customer.setLastname(rs.getString("lastname"));
        customer.setEmail(rs.getString("email"));
        customer.setPhone(rs.getString("phone"));
        return customer;
    }
}
