package web.server.app.travelagency.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.server.app.travelagency.dto.FindReservationDTO;
import web.server.app.travelagency.dto.ReservationTransferObject;
import web.server.app.travelagency.model.Reservation;
import web.server.app.travelagency.service.ReservationService;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getAll() {
        return ResponseEntity.ok(this.reservationService.findAll());
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<Reservation> findById(@PathVariable Long id) {
        return this.reservationService.findById(id)
                .map(r -> ResponseEntity.ok().body(r))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/find-reservation")
    public ResponseEntity<List<Reservation>> findAllByPhoneNum(@RequestBody FindReservationDTO reservationDTO) {
        return ResponseEntity.ok(this.reservationService.findReservationsByPhoneNumber(reservationDTO.getPhoneNumber()));
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> save(@RequestBody ReservationTransferObject reservationTransferObject) {
        Reservation reservation = this.reservationService.save(reservationTransferObject.getContactName(),
                reservationTransferObject.getPhoneNumber(),
                reservationTransferObject.getHoliday()).get();
        return ResponseEntity.ok(reservation);
    }

    @PutMapping("/reservations")
    public ResponseEntity<Reservation> edit(@RequestBody ReservationTransferObject reservationTransferObject) {
        Reservation reservation = this.reservationService.edit(reservationTransferObject.getId(),
                reservationTransferObject.getContactName(),
                reservationTransferObject.getPhoneNumber(),
                reservationTransferObject.getHoliday()).get();
        return ResponseEntity.ok().body(reservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.reservationService.deleteById(id);
        if (this.reservationService.findById(id).isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
