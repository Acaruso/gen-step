package dto;

import java.util.ArrayList;

public class TraqDTO {
    public ArrayList<StepDTO> steps;

    public TraqDTO() {
        steps = new ArrayList<StepDTO>();
    }

    public String toString() {
        return steps.toString();
    }
}