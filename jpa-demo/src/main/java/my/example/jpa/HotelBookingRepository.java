package my.example.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelBookingRepository extends JpaRepository<HotelBooking, Integer> {

    List<HotelBooking> findAllByClientName(String clientName);
}
