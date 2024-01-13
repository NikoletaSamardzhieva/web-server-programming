package web.server.app.travelagency.service;

import web.server.app.travelagency.model.Holiday;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HolidayService {
    List<Holiday> findAll();
    List<Holiday> findHolidaysByCriteria(String locationName, LocalDate startDate, Integer duration);
    Optional<Holiday> findById(Long id);
    Optional<Holiday> save(String location, String title, LocalDate startDate, Integer duration, Double price, Integer freeSlots);
    Optional<Holiday> edit(Long id, String location, String title, LocalDate startDate, Integer duration, Double price, Integer freeSlots);
    void deleteById(Long id);
}
