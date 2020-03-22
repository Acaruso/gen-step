package app;

import java.util.ArrayList;
import java.util.Random;

import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

import dto.StepDTO;
import dto.TraqDTO;

public class Traq {
    public ArrayList<Step> steps;
    public Track track;
    public Random random;
    public int loopLen;

    public Traq(Sequence seq) {
        steps = new ArrayList<Step>();
        track = seq.createTrack();
        random = new Random();

        // TODO: make this settable
        loopLen = 2;
    }

    public Traq(TraqDTO trackDTO, Sequence seq) {
        steps = new ArrayList<Step>();
        track = seq.createTrack();
        random = new Random();

        // TODO: make this settable
        loopLen = 2;

        for (StepDTO stepDTO : trackDTO.steps) {
            Step step = new Step(stepDTO);
            steps.add(step);
        }
    }

    public String toString() {
        return steps.toString();
    }

    // private void addFill(int note, int velocity, int tick, int duration) {
    //     int x = tick;
    //     for (int y = 0; y < 4; x++, y++) {
    //         MidiUtil.addNote(note, 100, x, 1, track);
    //     }
    // }
}
