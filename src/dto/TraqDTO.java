package dto;

import java.util.ArrayList;

public class TraqDTO {
    public ArrayList<StepDTO> steps;
    String name;

    public TraqDTO() {
        steps = new ArrayList<StepDTO>();
    }

    public String toString() {
        return steps.toString();
    }
}