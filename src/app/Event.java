package app;

import dto.*;

public class Event {
    enum EventType { NOTE, REST, FILL }

    public EventType type;
    public int note;
    public int velocity;
    public int prob;

    public Event() { }

    public Event(EventDTO eventDTO) {
        note = eventDTO.note;
        velocity = eventDTO.vel;
        prob = eventDTO.prob;

        if (velocity == 0) {
            type = EventType.REST;
        } else {
            type = EventType.NOTE;
        }
    }

    public String toString() { 
        return "[" + note + " " + velocity + "]"; 
    }
}