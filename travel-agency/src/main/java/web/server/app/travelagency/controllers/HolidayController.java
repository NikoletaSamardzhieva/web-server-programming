package web.server.app.travelagency.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.server.app.travelagency.dto.HolidayTransferObject;
import web.server.app.travelagency.model.Holiday;
import web.server.app.travelagency.service.HolidayService;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/holidays")
public class HolidayController {

    private final HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping
    public ResponseEntity<List<Holiday>> findAll() {
        return ResponseEntity.ok(this.holidayService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Holiday> findById(@PathVariable Long id) {
        return this.holidayService.findById(id)
                .map(h -> ResponseEntity.ok().body(h))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(params = {"location", "startDate", "duration"})
    public ResponseEntity<List<Holiday>> getAllByCriteria(@RequestParam String location,
                                                          @RequestParam String startDate,
                                                          @RequestParam Integer duration) {
        List<Holiday> holidays = this.holidayService.findHolidaysByCriteria(location, LocalDate.parse(startDate), duration);
        if (holidays.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(holidays);
    }

    @PostMapping
    public ResponseEntity<Holiday> save(@RequestBody HolidayTransferObject holidayTransferObject) {
        Holiday holiday = this.holidayService.save(holidayTransferObject.getLocation(),
                holidayTransferObject.getTitle(),
                holidayTransferObject.getStartDate(),
                holidayTransferObject.getDuration(),
                holidayTransferObject.getPrice(),
                holidayTransferObject.getFreeSlots()).get();
        return ResponseEntity.ok(holiday);
    }

    @PutMapping
    public ResponseEntity<Holiday> edit(@RequestBody HolidayTransferObject holidayTransferObject) {
        Holiday holiday = this.holidayService.edit(holidayTransferObject.getId(),
                holidayTransferObject.getLocation(),
                holidayTransferObject.getTitle(),
                holidayTransferObject.getStartDate(),
                holidayTransferObject.getDuration(),
                holidayTransferObject.getPrice(),
                holidayTransferObject.getFreeSlots()).get();
        return ResponseEntity.ok().body(holiday);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.holidayService.deleteById(id);
        if (this.holidayService.findById(id).isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
