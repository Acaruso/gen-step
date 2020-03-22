package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

public class Util {
    public static Song compileSong(Song song) throws Exception {
        Song compiledSong = Song.getEmptySong(song);

        compiledSong.traqs = compileTraqs(song, compiledSong.seq);

        writeTracks(compiledSong);

        return compiledSong;
    }

    public static HashMap<String, Traq> compileTraqs(Song song, Sequence seq) {
        HashMap<String, Traq> compiledTraqs = new HashMap<String, Traq>();

        for (Map.Entry<String, Traq> entry : song.traqs.entrySet()) {
            String traqName = entry.getKey();
            Traq traq = entry.getValue();
            //Traq compiledTraq = compileTraq(traq, seq); // bug is here. compiledTrack.track != track inside compileTraq()
            //compiledTraqs.put(traqName, compiledTraq);

            // begin weird stuff
            Traq newTraq = new Traq(seq);

            ArrayList<Step> newSteps = new ArrayList<Step>();
            for (Step step : traq.steps) {
                Step newStep = new Step();
    
                ArrayList<Event> newEvents = new ArrayList<Event>();
                for (Event event : step.events) {
                    Event newEvent = new Event(event);  // create copy of event
                    newEvents.add(newEvent);
                }
    
                newStep.events = newEvents;
                newSteps.add(newStep);
            }
            newTraq.steps = newSteps;
            compiledTraqs.put(traqName, newTraq);
            // end weird stuff
        }

        return compiledTraqs;
    }

    public static Traq compileTraq(Traq traq, Sequence seq) {
        Traq newTraq = new Traq(seq);

        ArrayList<Step> newSteps = new ArrayList<Step>();
        for (Step step : traq.steps) {
            Step newStep = new Step();

            ArrayList<Event> newEvents = new ArrayList<Event>();
            for (Event event : step.events) {
                Event newEvent = new Event(event);  // create copy of event
                newEvents.add(newEvent);
            }

            newStep.events = newEvents;
            newSteps.add(newStep);
        }

        newTraq.steps = newSteps;

        return traq;
    }

    public static void writeTracks(Song song) {
        // for each traq in song, render notes to traq.track
        for (Map.Entry<String, Traq> entry : song.traqs.entrySet()) {
            Traq traq = entry.getValue();
            writeTrack(traq, song);
        }
    }

    public static void writeTrack(Traq traq, Song song) {
        // TODO: "bars" is not really number of bars, but rather
        // number of times to render full sequence to track
        for (int i = 0; i < song.bars; i++) {
            writeLoop(traq, song.stepSize, i);
        }
    }

    private static void writeLoop(Traq traq, int stepSize, int offset) {
        // offset: 0    1    2    3
        // loops:  ---- ---- ---- ----
        
        int i = offset * traq.steps.size();

        for (Step step : traq.steps) {
            int tick = i * stepSize;
            int duration = stepSize;

            for (Event event : step.events) {
                System.out.println("adding event");
                System.out.println(event);
                System.out.println();
                addEvent(event, tick, duration, traq.track);
            }

            i++;
        }
    }

    private static void addEvent(Event event, int tick, int duration, Track track) {
        if (event.type == Event.EventType.NOTE) {
            addNote(event, tick, duration, track);
        } else if (event.type == Event.EventType.REST) {
            // do nothing
        }
    }

    private static void addNote(Event event, int tick, int duration, Track track) {
        MidiUtil.addNote(event.note, event.velocity, tick, duration, track);
    }
    
    // private void addFill(int note, int velocity, int tick, int duration) {
    //     int x = tick;
    //     for (int y = 0; y < 4; x++, y++) {
    //         MidiUtil.addNote(note, 100, x, 1, track);
    //     }
    // }

}