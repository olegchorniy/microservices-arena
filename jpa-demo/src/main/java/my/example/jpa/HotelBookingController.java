package my.example.jpa;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class HotelBookingController {

    private final HotelBookingRepository hotelBookingRepository;

    public HotelBookingController(HotelBookingRepository hotelBookingRepository) {
        this.hotelBookingRepository = hotelBookingRepository;
    }

    @RequestMapping(path = "/create", method = POST)
    public HotelBooking createHotelBooking(@RequestBody HotelBooking hotelBooking) {
        return hotelBookingRepository.save(hotelBooking);
    }

    @RequestMapping(path = "/getByName/{clientName}", method = GET)
    public List<HotelBooking> getByName(@PathVariable("clientName") String clientName) {
        return hotelBookingRepository.findAllByClientName(clientName);
    }

    @RequestMapping(path = "/getAll", method = GET)
    public List<HotelBooking> getAll() {
        return hotelBookingRepository.findAll();
    }
}
