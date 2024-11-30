package jaison.chipuka.rides.repository;

import jaison.chipuka.rides.model.Ride;

import java.util.List;

public interface RideRepository {
    Ride createRide(Ride ride);
    Ride getRide(Integer id);
    List<Ride> getRides();
    Ride updateRide(Ride ride);
    void updateRides(List<Object[]> pairs);
    void deleteRide(Integer id);
}
