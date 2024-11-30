package jaison.chipuka.rides.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private Long id;
    private String address;
    private Double latitude;
    private Double longitude;

    public Location(String address, Double latitude, Double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
