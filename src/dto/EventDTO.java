package dto;

public class EventDTO {
    public int note;
    public int vel;
    public int prob;

    public EventDTO() {
        note = 0;
        vel = 0;
        prob = 10;
    }

    public String toString() {
        return "[" + note + " " + vel + " " + prob + "]";
    }
}