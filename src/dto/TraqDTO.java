package dto;

import java.util.ArrayList;

public class TraqDTO {
    public ArrayList<EventDTO> events;

    public TraqDTO() {
        events = new ArrayList<EventDTO>();
    }

    public String toString() {
        return events.toString();
    }
}