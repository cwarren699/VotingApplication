import java.util.*;
import org.apache.commons.lang3.StringUtils;

public class Main {

    private static ArrayList<VotingSystem> elections = new ArrayList<>();

    public static void main(String[] args) {
        while (true) { //HomeScreen
            System.out.println("Welcome to the voting application. What would you like to do?");
            System.out.println("0: Create new election");
            System.out.println("1: Vote in election");
            System.out.println("2: View election statistics");
            Scanner input = new Scanner(System.in); //records user response
            String choice = input.nextLine();
            if (choice.equals("0")) { //Create
                elections.add(newElection());
            } else if (choice.equals("1")) { //Vote
                vote();
            } else if(choice.equals("2")) { //View
                results();
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    private static VotingSystem newElection() {
        System.out.println("What is the name of this election?");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();

        int amount;
        while (true) {
            System.out.println("How many candidates?");
            String value = input.next();
            if (StringUtils.isNumeric(value) && Integer.parseInt(value) > 1) {
                amount = Integer.parseInt(value);
                break; //valid number of candidates
            } else {
                System.out.println("Invalid input. Make sure input is integer greater than 1.");
            }
        }
        Candidate[] candidates = new Candidate[amount];
        System.out.println("What are the names of the candidates?");
        for (int i = 0; i < amount; i++) { //gets names of candidates
            String candidate = input.next();
            candidates[i] = new Candidate(candidate);
        }

        int style;
        while (true) { //gets selection of election style
            Scanner styleInput = new Scanner(System.in);
            System.out.println("What style of election is this?");
            System.out.println("0: Popular vote");
            if (styleInput.nextLine().equals("0")) {
                style = 0;
                break; //valid style
            }
            System.out.println("Invalid input.");
        }
        System.out.println("Election created.");
        return new VotingSystem(name, candidates, style);
    }

    private static void vote() {
        if (elections.isEmpty()) {
            System.out.println("No current elections");
        } else {
            Scanner input = new Scanner(System.in);
            while (true) { //gets election user wants to vote in
                System.out.println("Which election would you like to vote on?");
                for (int i = 0; i < elections.size(); i++) {
                    System.out.println(i + ": " + elections.get(i).getName());
                }
                String choice = input.nextLine();
                if (StringUtils.isNumeric(choice)) {
                    int election = Integer.parseInt(choice);
                    if (election < elections.size()) {
                        while (true) { //gets candidate user wants to vote for
                            System.out.println("Select the candidate you would like to vote for.");
                            Candidate[] listOfCandidates = elections.get(election).getCandidates();
                            for (int i = 0; i < listOfCandidates.length; i++) {
                                System.out.println(i + ": " + listOfCandidates[i].getName());
                            }
                            choice = input.nextLine();
                            if (StringUtils.isNumeric(choice)) {
                                int vote = Integer.parseInt(choice);
                                if (vote < listOfCandidates.length) {
                                    int currentVotes = elections.get(election).getCandidates()[vote].getVotes();
                                    elections.get(election).getCandidates()[vote].setVotes(++currentVotes);
                                    break; //valid candidate
                                }
                            } else {
                                System.out.println("Invalid option.");
                            }
                        }
                        break; //valid election
                    }
                }
                System.out.println("Invalid option.");
            }
        }
    }

    private static void results() {
        if (elections.isEmpty()) {
            System.out.println("No current elections");
        } else {
            for (VotingSystem election : elections) {
                System.out.println(election.getName());
                for (int i = 0; i < election.getCandidates().length; i++) {
                    Candidate current = election.getCandidates()[i];
                    System.out.println("    " + current.getName() + ": " + current.getVotes());
                }
                System.out.println();
            }
        }
    }
}

