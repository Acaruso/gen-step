package app;

import com.google.gson.Gson;

import dto.EventDTO;

public class Event {
    enum EventType { NOTE, REST, FILL }

    public EventType type;
    public int note;
    public int velocity;
    public int prob;

    public Event() { }

    public Event(Event e) {
        type = e.type;
        note = e.note;
        velocity = e.velocity;
        prob = e.prob;
    }

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
        Gson gson = new Gson();
        return gson.toJson(this);
        // return "[" + note + " " + velocity + "]"; 
    }
}