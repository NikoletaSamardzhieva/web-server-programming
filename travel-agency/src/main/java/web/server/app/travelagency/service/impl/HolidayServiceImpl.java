package web.server.app.travelagency.service.impl;

import org.springframework.stereotype.Service;
import web.server.app.travelagency.model.Holiday;
import web.server.app.travelagency.model.Location;
import web.server.app.travelagency.repository.HolidayRepository;
import web.server.app.travelagency.repository.LocationRepository;
import web.server.app.travelagency.service.HolidayService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class HolidayServiceImpl implements HolidayService {
    private final HolidayRepository holidayRepository;
    private final LocationRepository locationRepository;

    public HolidayServiceImpl(HolidayRepository holidayRepository, LocationRepository locationRepository) {
        this.holidayRepository = holidayRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Holiday> findAll() {
        return this.holidayRepository.findAll();
    }

    @Override
    public List<Holiday> findHolidaysByCriteria(String locationName, LocalDate startDate, Integer duration) {
        Optional<Location> locationByCity = this.locationRepository.findLocationByCity(locationName);
        if (locationByCity.isEmpty()) {
            List<Location> locationByCountry = this.locationRepository.findLocationByCountry(locationName);
            if (locationByCountry.isEmpty()) {
                return Collections.emptyList();
            } else {
                for (Location current : locationByCountry) {
                    List<Holiday> holidays = this.holidayRepository.findHolidayByLocationAndStartDateAndDuration(current,
                            startDate, duration);
                    if (!holidays.isEmpty()) {
                        return holidays;
                    }
                }
                return Collections.emptyList();
            }
        } else {
            return this.holidayRepository.findHolidayByLocationAndStartDateAndDuration(locationByCity.get(),
                    startDate, duration);
        }
    }

    @Override
    public Optional<Holiday> findById(Long id) {
        return this.holidayRepository.findById(id);
    }

    @Override
    public Optional<Holiday> save(String location, String title, LocalDate startDate, Integer duration, Double price, Integer freeSlots) {
        Location existingLocation = locationRepository.findById(Long.valueOf(location)).orElseThrow(RuntimeException::new);
        Holiday holiday = new Holiday(title, startDate, duration, price, freeSlots, existingLocation);
        holidayRepository.save(holiday);
        return Optional.of(holiday);
    }

    @Override
    public Optional<Holiday> edit(Long id, String location, String title, LocalDate startDate, Integer duration, Double price, Integer freeSlots) {
        Location existingLocation = locationRepository.findById(Long.valueOf(location)).orElseThrow(RuntimeException::new);
        Holiday holiday = this.holidayRepository.findById(id).get();
        holiday.setLocation(existingLocation);
        holiday.setTitle(title);
        holiday.setStartDate(startDate);
        holiday.setDuration(duration);
        holiday.setPrice(price);
        holiday.setFreeSlots(freeSlots);

        this.holidayRepository.save(holiday);
        return Optional.of(holiday);
    }

    @Override
    public void deleteById(Long id) {
        this.holidayRepository.deleteById(id);
    }
}
