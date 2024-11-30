package jaison.chipuka.rides.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ride {
    private Long id;
    private String name;
    private int duration;

    private Location origin;
    private Location destination;

    public Ride(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

}
