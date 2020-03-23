package dto;

import java.util.ArrayList;

public class SongDTO {
    public ArrayList<TraqDTO> tracks;
    public int bars;
    public int stepsPerBar;

    public SongDTO() {
        tracks = new ArrayList<TraqDTO>();
    }

    public String toString() {
        String s = "";

        for (TraqDTO traqDTO : tracks) {
            s += traqDTO.name + "\n";
            s += traqDTO.toString() + "\n";
            s += "\n";
        }

        return s;
    }
}