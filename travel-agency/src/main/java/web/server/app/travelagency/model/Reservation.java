package web.server.app.travelagency.model;

import javax.persistence.*;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String contactName;
    private String phoneNumber;
    @ManyToOne
    private Holiday holiday;

    public Reservation() {
    }

    public Reservation(String contactName, String phoneNumber, Holiday holiday) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.holiday = holiday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Holiday getHoliday() {
        return holiday;
    }

    public void setHoliday(Holiday holiday) {
        this.holiday = holiday;
    }
}
