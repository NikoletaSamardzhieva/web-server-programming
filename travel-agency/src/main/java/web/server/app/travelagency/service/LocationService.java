package web.server.app.travelagency.service;

import web.server.app.travelagency.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> findAll();
    Optional<Location> findById(Long id);
    Optional<Location> save(String street, Integer number, String city, String country);
    Optional<Location> edit(Long id, String street, Integer number, String city, String country);
    void deleteById(Long id);
}
