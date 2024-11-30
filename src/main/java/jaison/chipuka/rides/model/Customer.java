package jaison.chipuka.rides.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;

    public Customer(String firstname, String lastname, String email, String phone) {
        this.username = firstname + "." + lastname;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }
}
