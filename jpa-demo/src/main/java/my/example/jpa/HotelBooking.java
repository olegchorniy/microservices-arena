package my.example.jpa;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bookings", schema = "public")
public class HotelBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "hotel_name")
    private String hotelName;
    private Date arrival;
    private Date departure;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    @Override
    public String toString() {
        return "[" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", arrival=" + arrival +
                ", departure=" + departure +
                ']';
    }
}
