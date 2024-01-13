package web.server.app.travelagency.service.impl;

import org.springframework.stereotype.Service;
import web.server.app.travelagency.model.Holiday;
import web.server.app.travelagency.model.Reservation;
import web.server.app.travelagency.repository.HolidayRepository;
import web.server.app.travelagency.repository.ReservationRepository;
import web.server.app.travelagency.service.ReservationService;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final HolidayRepository holidayRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, HolidayRepository holidayRepository) {
        this.reservationRepository = reservationRepository;
        this.holidayRepository = holidayRepository;
    }

    @Override
    public List<Reservation> findAll() {
        return this.reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return this.reservationRepository.findById(id);
    }

    @Override
    public Optional<Reservation> save(String contactName, String phoneNumber, String holiday) {
        Holiday existingHoliday = this.holidayRepository.findById(Long.valueOf(holiday)).orElseThrow(RuntimeException::new);
        Reservation reservation = new Reservation(contactName, phoneNumber, existingHoliday);
        this.reservationRepository.save(reservation);
        return Optional.of(reservation);
    }

    @Override
    public Optional<Reservation> edit(Long id, String contactName, String phoneNumber, String holiday) {
        Holiday existingHoliday = this.holidayRepository.findById(Long.valueOf(holiday)).orElseThrow(RuntimeException::new);
        Optional<Reservation> optReservation = this.reservationRepository.findById(id);
        if (optReservation.isEmpty()) {
            return Optional.empty();
        }
        Reservation reservation = optReservation.get();
        reservation.setContactName(contactName);
        reservation.setPhoneNumber(phoneNumber);
        reservation.setHoliday(existingHoliday);
        this.reservationRepository.save(reservation);
        return Optional.of(reservation);
    }

    @Override
    public void deleteById(Long id) {
        this.reservationRepository.deleteById(id);
    }

    @Override
    public List<Reservation> findReservationsByPhoneNumber(String phoneNumber) {
        return this.reservationRepository.findAllByPhoneNumber(phoneNumber);
    }
}
