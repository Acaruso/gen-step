package dto;

import com.google.gson.Gson;

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
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}