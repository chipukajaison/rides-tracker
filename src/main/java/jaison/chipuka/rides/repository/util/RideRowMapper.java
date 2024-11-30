package jaison.chipuka.rides.repository.util;

import jaison.chipuka.rides.model.Ride;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RideRowMapper implements RowMapper<Ride> {
    @Override
    public Ride mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ride ride = new Ride();
        ride.setId(rs.getLong("id"));
        ride.setName(rs.getString("name"));
        ride.setDuration(rs.getInt("duration"));
        return ride;
    }
}
