package jaison.chipuka.rides.repository.impl;

import jaison.chipuka.rides.model.Customer;
import jaison.chipuka.rides.repository.CustomerRepository;
import jaison.chipuka.rides.repository.util.CustomerRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository("customerRepository")
public class CustomerRepositoryImpl implements CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Customer createCustomer(Customer customer) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement("insert into customer (username, firstname, lastname, email, phone) values (?, ?, ?, ?, ?)", new String[]{"id"});
                ps.setString(1, customer.getUsername());
                ps.setString(2, customer.getFirstname());
                ps.setString(3, customer.getLastname());
                ps.setString(4, customer.getEmail());
                ps.setString(5, customer.getPhone());
                return ps;
            }
        }, keyHolder);

        Number id = keyHolder.getKey();
        assert id != null;
        return getCustomer(id.longValue());
    }

    @Override
    public Customer getCustomer(Long id) {
        return jdbcTemplate.queryForObject("select * from customer where id = ?", new CustomerRowMapper(), id);
    }

    @Override
    public List<Customer> getCustomers() {
        return jdbcTemplate.query("select * from customer", new CustomerRowMapper());
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        jdbcTemplate.update(
            "update customer set username = ?, firstname = ?, lastname = ?, email = ?, phone = ? where id = ?",
                customer.getUsername(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getId()
        );

        return customer;
    }

    @Override
    public void updateCustomers(List<Customer> customers) {
        for (Customer customer : customers) {
            jdbcTemplate.update(
                    "update customer set username = ?, firstname = ?, lastname = ?, email = ?, phone = ? where id = ?",
                    customer.getUsername(),
                    customer.getFirstname(),
                    customer.getLastname(),
                    customer.getEmail(),
                    customer.getPhone(),
                    customer.getId()
            );
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        namedTemplate.update("delete from customer where id = ?", params);
    }
}
