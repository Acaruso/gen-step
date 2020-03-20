package app;

import java.util.ArrayList;

import dto.EventDTO;
import dto.StepDTO;

public class Step {
    public ArrayList<Event> events;

    public Step(StepDTO stepDTO) {
        events = new ArrayList<Event>();
        
        for (EventDTO eventDTO : stepDTO.events) {
            Event event = new Event(eventDTO);
            events.add(event);
        }
    }

    public String toString() {
        return events.toString();
    }
}
