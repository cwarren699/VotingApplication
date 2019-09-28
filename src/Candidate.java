public class Candidate {
    String name;
    int votes = 0;

    public Candidate(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
