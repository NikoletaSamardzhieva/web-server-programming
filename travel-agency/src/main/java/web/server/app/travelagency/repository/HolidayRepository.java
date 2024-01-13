package web.server.app.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.server.app.travelagency.model.Holiday;
import web.server.app.travelagency.model.Location;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    List<Holiday> findHolidayByLocationAndStartDateAndDuration(Location location, LocalDate startDate, Integer duration);
}
