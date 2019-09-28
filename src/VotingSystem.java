import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.Scanner;

public class VotingSystem {

    private String name;
    private Candidate[] candidates;
    private int style;

    public VotingSystem(String name, Candidate[] candidates, int style) {
        this.name = name;
        this.candidates = candidates;
        this.style = style;
    }

    public String getName() {
        return name;
    }

    public Candidate[] getCandidates() {
        return candidates;
    }

    public int getStyle() {
        return style;
    }
}
