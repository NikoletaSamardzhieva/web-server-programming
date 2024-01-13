package web.server.app.travelagency.dto;

public class FindReservationDTO {
    private String phoneNumber;

    public FindReservationDTO() {
    }

    public FindReservationDTO(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
