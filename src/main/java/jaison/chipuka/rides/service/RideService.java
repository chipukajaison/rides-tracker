package jaison.chipuka.rides.service;

import jaison.chipuka.rides.model.Ride;

import java.util.List;

public interface RideService {
    Ride createRide(Ride ride);
    Ride getRide(Integer id);
    List<Ride> getRides();
    Ride updateRide(Ride ride);
    void batch();
    void deleteRide(Integer id);
}
