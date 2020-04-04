package app;

import java.util.Random;

import javax.sound.midi.Track;

public class Util {
    public static void writeTracks(Song song) {
        // for each traq in song, render notes to traq.track
        for (Traq traq : song.traqs) {
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
                handleEvent(event, tick, duration, traq.track);
            }

            i++;
        }
    }

    private static void handleEvent(Event event, int tick, int duration, Track track) {
        Random random = new Random();

        if (event.type == Event.EventType.NOTE) {
            int res = random.nextInt(10);   // get int between 0 - 9
            if (res < event.prob) {
                addNote(event, tick, duration, track);
            }
        } else if (event.type == Event.EventType.REST) {
            // do nothing
        }
    }

    private static void addNote(Event event, int tick, int duration, Track track) {
        MidiUtil.addNote(event.note, event.velocity, tick, duration, track);
    }
}
