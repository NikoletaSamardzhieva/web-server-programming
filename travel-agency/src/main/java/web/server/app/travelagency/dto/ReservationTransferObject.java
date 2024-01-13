package web.server.app.travelagency.dto;

public class ReservationTransferObject {
    private Long id;
    private String holiday;
    private String contactName;
    private String phoneNumber;

    public ReservationTransferObject() {
    }

    public ReservationTransferObject(Long id, String holiday, String contactName, String phoneNumber) {
        this.id = id;
        this.holiday = holiday;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
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
}
