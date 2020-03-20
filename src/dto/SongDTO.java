package dto;

import java.util.HashMap;
import java.util.Map;

public class SongDTO {
    public HashMap<String, TraqDTO> tracks;
    public int bars;
    public int stepsPerBar;

    public SongDTO() {
        tracks = new HashMap<String, TraqDTO>();
    }

    public String toString() {
        String s = "";
        for (Map.Entry<String, TraqDTO> entry : tracks.entrySet()) {
            s += entry.getKey() + "\n";
            s += entry.getValue().toString() + "\n";
            s += "\n";
        }
        return s;
    }
}