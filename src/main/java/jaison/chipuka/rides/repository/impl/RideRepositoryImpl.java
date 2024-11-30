package jaison.chipuka.rides.repository.impl;

import jaison.chipuka.rides.model.Ride;
import jaison.chipuka.rides.repository.RideRepository;
import jaison.chipuka.rides.repository.util.RideRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Ride createRide(Ride ride) {
        // jdbcTemplate.update("insert into ride (name, duration) values (?, ?)", ride.getName(), ride.getDuration());

        // SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

        // List<String> columns = new ArrayList<>();
        // columns.add("name");
        // columns.add("duration");

        // insert.setTableName("ride");
        // insert.setColumnNames(columns);

        // Map<String, Object> data = new HashMap<>();
        // data.put("name", ride.getName());
        // data.put("duration", ride.getDuration());

        // insert.setGeneratedKeyName("id");

        // Number key = insert.executeAndReturnKey(data);

        // System.out.println(key);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement("insert into ride (name, duration) values (?, ?)", new String[]{"id"});
                ps.setString(1, ride.getName());
                ps.setInt(2, ride.getDuration());
                return ps;
            }
        }, keyHolder);

        Number id = keyHolder.getKey();
        return getRide(id.intValue());
    }

    @Override
    public Ride getRide(Integer id) {
        return jdbcTemplate.queryForObject("select * from ride where id = ?", new RideRowMapper(), id);
    }

    @Override
    public List<Ride> getRides() {

        /* return jdbcTemplate.query("select * from ride", new RowMapper<Ride>() {

            @Override
            public Ride mapRow(ResultSet rs, int rowNum) throws SQLException {
                Ride ride = new Ride();
                ride.setId(rs.getInt("id"));
                ride.setName(rs.getString("name"));
                ride.setDuration(rs.getInt("duration"));
                return ride;
            }
        }); */

        /* return jdbcTemplate.query("select * from ride", (rs, rowNum) -> {
            Ride ride = new Ride();
            ride.setId(rs.getInt("id"));
            ride.setName(rs.getString("name"));
            ride.setDuration(rs.getInt("duration"));
            return ride;
        }); */

        return jdbcTemplate.query("select * from ride", new RideRowMapper());
    }

    @Override
    public Ride updateRide(Ride ride) {

        jdbcTemplate.update("update ride set name = ?, duration = ? where id = ?", ride.getName(), ride.getDuration(), ride.getId());

        return ride;
    }

    @Override
    public void updateRides(List<Object[]> pairs) {
        jdbcTemplate.batchUpdate("update ride set ride_date = ? where id = ?", pairs);
    }

    @Override
    public void deleteRide(Integer id) {
        // jdbcTemplate.update("delete from ride where id = ?", id);
        NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        namedTemplate.update("delete from ride where id = :id", params);
    }

}
