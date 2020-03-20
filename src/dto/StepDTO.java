package dto;

import java.util.ArrayList;

public class StepDTO {
    public ArrayList<EventDTO> events;

    public StepDTO() {
        events = new ArrayList<EventDTO>();
    }

    public String toString() {
        return events.toString();
    }
}
