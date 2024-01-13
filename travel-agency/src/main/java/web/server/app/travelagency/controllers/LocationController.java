package web.server.app.travelagency.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.server.app.travelagency.dto.LocationTransferObject;
import web.server.app.travelagency.model.Location;
import web.server.app.travelagency.service.LocationService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/locations")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<Location>> findAll() {
        return ResponseEntity.ok(this.locationService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Location> findById(@PathVariable Long id) {
        return this.locationService.findById(id)
                .map(l -> ResponseEntity.ok().body(l))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Location> save(@RequestBody LocationTransferObject locationTransferObject) {
        Location location = this.locationService.save(locationTransferObject.getStreet(),
                locationTransferObject.getNumber(),
                locationTransferObject.getCity(),
                locationTransferObject.getCountry()).get();
        return ResponseEntity.ok(location);
    }

    @PutMapping
    public ResponseEntity<Location> edit(@RequestBody LocationTransferObject locationTransferObject) {
        Location location = this.locationService.edit(locationTransferObject.getId(),
                locationTransferObject.getStreet(),
                locationTransferObject.getNumber(),
                locationTransferObject.getCity(),
                locationTransferObject.getCountry()).get();
        return ResponseEntity.ok().body(location);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.locationService.deleteById(id);
        if (this.locationService.findById(id).isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
