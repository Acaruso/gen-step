package app;

import javax.sound.midi.*;
import dto.*;
import java.util.*;
import java.util.Random;

public class Traq {
    public Track track;
    public Random random;
    public int loopLen;
    public List<Event> events;

    public Traq(TraqDTO trackDTO, Sequence seq) {
        track = seq.createTrack();
        random = new Random();
        events = new ArrayList<Event>();

        // TODO: make this settable
        loopLen = 2;

        for (EventDTO eventDTO : trackDTO.events) {
            Event event = new Event(eventDTO);
            events.add(event);
        }
    }

    // private void addFill(int note, int velocity, int tick, int duration) {
    //     int x = tick;
    //     for (int y = 0; y < 4; x++, y++) {
    //         MidiUtil.addNote(note, 100, x, 1, track);
    //     }
    // }
}
