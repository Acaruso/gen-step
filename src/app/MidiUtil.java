package app;

import javax.sound.midi.*;
import java.io.*;

public class MidiUtil {
    private static int NOTE_ON = 144;
    private static int NOTE_OFF = 128;

    public static void addNote(int note, int velocity, int tick, int duration, Track track) {
        track.add(event(NOTE_ON, 1, note, velocity, tick));
        track.add(event(NOTE_OFF, 1, note, velocity, tick + duration));
    }

    // low level "event", not the same as Event.java
    public static MidiEvent event(int command, int channel, int note, int velocity, int tick) {
        MidiEvent midiEvent = null;

        ShortMessage shortMessage = new ShortMessage();

        try {
            shortMessage.setMessage(command, channel, note, velocity);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }

        midiEvent = new MidiEvent(shortMessage, tick);

        return midiEvent;
    }

    // filename should have .mid extension
    public static void writeMidiFile(Sequence seq, String filename) {
        File file = new File(filename);
        try {
            MidiSystem.write(seq, 1, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
