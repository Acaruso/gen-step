package app;

import java.util.ArrayList;
import java.util.Random;

import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

import dto.StepDTO;
import dto.TraqDTO;

public class Traq {
    public ArrayList<Step> steps;
    String name;
    public Track track;
    public Random random;

    public Traq(Sequence seq) {
        steps = new ArrayList<Step>();
        track = seq.createTrack();
        random = new Random();
    }

    public Traq(TraqDTO trackDTO, Sequence seq) {
        steps = new ArrayList<Step>();
        track = seq.createTrack();
        random = new Random();

        for (StepDTO stepDTO : trackDTO.steps) {
            Step step = new Step(stepDTO);
            steps.add(step);
        }
    }

    public String toString() {
        return steps.toString();
    }
}
