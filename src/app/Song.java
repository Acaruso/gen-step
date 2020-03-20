package app;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.sound.midi.*;
import com.google.gson.Gson;
import dto.*;

public class Song {
    public Sequence seq;
    public HashMap<String, Traq> traqs;
    public int bars;
    public int stepsPerBar;
    public int step;

    public Song(SongDTO songDTO) throws Exception {
        bars = songDTO.bars;
        stepsPerBar = songDTO.stepsPerBar;

        if (stepsPerBar == 4) {
            step = Constants.QUARTER;
        } else if (stepsPerBar == 8) {
            step = Constants.EIGHTH;
        } else if (stepsPerBar == 16) {
            step = Constants.SIXTEENTH;
        }

        // resolution = ticks per quarter note
        seq = new Sequence(javax.sound.midi.Sequence.PPQ, Constants.RESOLUTION);
        traqs = new HashMap<String, Traq>();

        // for each track in trackDTO, create traq, add to song.traqs
        songDTO.tracks.forEach((trackName, trackDTO) -> {
            Traq traq = new Traq(trackDTO, seq);
            traqs.put(trackName, traq);
        });
    }

    public static Song read(String filename) throws Exception {
        SongDTO songDTO = getSongDTO(filename);
        Song song = new Song(songDTO);
        return song;
    }

    public static void write(Song song, String filename) {
        Util.writeTracks(song);
        MidiUtil.writeMidiFile(song.seq, filename);
    }

    private static SongDTO getSongDTO(String filename) throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        Gson gson = new Gson();
        SongDTO songDTO = gson.fromJson(bufferedReader, SongDTO.class);
        return songDTO;
    }

    public String toString() {
        String s = "";
        s += "bars: " + bars + "\n";
        for (Map.Entry<String, Traq> entry : traqs.entrySet()) {
            s += entry.getKey() + "\n";
            s += entry.getValue().toString() + "\n";
            s += "\n";
        }
        return s;
    }
}
