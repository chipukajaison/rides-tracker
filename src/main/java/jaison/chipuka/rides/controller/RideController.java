package jaison.chipuka.rides.controller;

import jaison.chipuka.rides.model.Ride;
import jaison.chipuka.rides.service.RideService;
import jaison.chipuka.rides.util.ServiceError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RideController {

    @Autowired
    private RideService rideService;

    @RequestMapping(path = "/ride", method = RequestMethod.POST)
    public @ResponseBody Ride createRide(@RequestBody Ride ride) {
        return rideService.createRide(ride);
    }

    @RequestMapping(path = "/ride/{id}", method = RequestMethod.GET)
    public @ResponseBody Ride getRide(@PathVariable Integer id) {
        return rideService.getRide(id);
    }

    @RequestMapping(path = "/rides", method = RequestMethod.GET)
    public @ResponseBody List<Ride> getRides() {
        return rideService.getRides();
    }

    @RequestMapping(path = "/ride", method = RequestMethod.PUT)
    public @ResponseBody Ride updateRide(@RequestBody Ride ride) {
        return rideService.updateRide(ride);
    }

    @RequestMapping(path = "/batch", method = RequestMethod.GET)
    public @ResponseBody Object batch() {
        rideService.batch();
        return null;
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Object delete(@PathVariable Integer id) {
        rideService.deleteRide(id);
        return null;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ServiceError> handleException(RuntimeException ex) {
        ServiceError error = new ServiceError(
                HttpStatus.OK.value(), ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

}
