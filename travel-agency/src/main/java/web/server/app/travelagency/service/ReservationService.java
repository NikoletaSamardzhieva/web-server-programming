package web.server.app.travelagency.service;

import web.server.app.travelagency.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Reservation> findAll();
    Optional<Reservation> findById(Long id);
    Optional<Reservation> save(String contactName, String phoneNumber, String holiday);
    Optional<Reservation> edit(Long id, String contactName, String phoneNumber, String holiday);
    void deleteById(Long id);
    List<Reservation> findReservationsByPhoneNumber(String phoneNumber);
}
