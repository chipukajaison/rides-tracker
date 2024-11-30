package jaison.chipuka.rides;

import jaison.chipuka.rides.model.Ride;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RestControllerTest {

    private final static String BASE_URL = "http://localhost:8080/rides_tracker";

    @Test(timeout = 3000)
    public void testCreateRide() {
        RestTemplate restTemplate = new RestTemplate();

        Ride ride = new Ride();
        ride.setName("Bobsled Trail Ride");
        ride.setDuration(35);

        // restTemplate.put("http://localhost:8080/rides_tracker/ride", ride);

        ride = restTemplate.postForObject(BASE_URL + "/ride", ride, Ride.class);
    }

    @Test(timeout = 3000)
    public void testGetRide() {
        RestTemplate restTemplate = new RestTemplate();

        Ride ride = restTemplate.getForObject(BASE_URL + "/ride/1", Ride.class);
        System.out.println("Ride name: " + ride.getName());
    }

    @Test(timeout = 3000)
    public void testGetRides() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
                BASE_URL + "/rides", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Ride>>() {}
        );
        List<Ride> rides = ridesResponse.getBody();

        for (Ride ride : rides) {
            System.out.println("Ride name: " + ride.getName());
        }
    }

    @Test(timeout = 3000)
    public void testUpdateRide() {
        RestTemplate restTemplate = new RestTemplate();

        Ride ride = restTemplate.getForObject(BASE_URL + "/ride/1", Ride.class);

        ride.setDuration(ride.getDuration() + 1);

        restTemplate.put(BASE_URL + "/ride/" + ride.getId(), ride);

        System.out.println("Ride name: " + ride.getName());
    }

    @Test(timeout = 3000)
    public void testBatchUpdate() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getForObject(BASE_URL + "/batch", Object.class);
    }

    @Test(timeout = 3000)
    public void testDelete() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.delete(BASE_URL + "/delete/1");
    }

}
