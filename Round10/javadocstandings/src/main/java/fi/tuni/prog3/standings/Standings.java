package fi.tuni.prog3.standings;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

/**
 * A class for maintaining team statistics and standings. Team standings are determined by the following rules:
 * <ul><li>Primary rule: points total. Higher points come first.</li>
 * <li>Secondary rule: goal difference (scored minus allowed). Higher difference comes first.</li>
 * <li>Tertiary rule: number of goals scored. Higher number comes first.</li>
 * <li>Last rule: natural String order of team names.</li></ul>
 */
public class Standings {

    private final ArrayList<Team> list_of_teams = new ArrayList();
    
    private void reorderStandings() {

        ArrayList<Team> temporary = new ArrayList<>(this.list_of_teams);
        this.list_of_teams.clear();
        
        while (!temporary.isEmpty()) {
            Team bestTeam = null;
            int highestPoints = -1;
            
            for (Team team : temporary) {
                if (team.getPoints() > highestPoints) {
                    bestTeam = team;
                    highestPoints = team.getPoints();
                } else if (team.getPoints() == highestPoints) {
                    int goalDifferenceA = bestTeam.getScored() - bestTeam.getAllowed();
                    int goalDifferenceB = team.getScored() - team.getAllowed();
                    if (goalDifferenceA < goalDifferenceB) {
                        bestTeam = team;
                    } else if (goalDifferenceA == goalDifferenceB) {
                        if (bestTeam.getScored() < team.getScored()) {
                            bestTeam = team;
                        } else if (bestTeam.getScored() == team.getScored()) {
                            if (bestTeam.getName().compareTo(team.getName()) > 0) {
                                bestTeam = team;
                            }
                        }
                    }
                }
            }
            
            this.list_of_teams.add(bestTeam);
            temporary.remove(bestTeam);
        }
    }
    
    private void addTeamToList(Team team) {

        this.list_of_teams.add(team);
    }

    /**
     * A class for storing statistics of a single team.
     */
    public static class Team {

        private final String team_name;
        private int team_wins = 0;
        private int team_ties = 0;
        private int team_losses = 0;
        private int team_scoredgoals;
        private int team_allowedgoals;
        private int team_points;

        public Team(String name) {
            this.team_name = name;
        }
        
        public String getName() {

            return this.team_name;
        }
        
        public int getWins() {

            return this.team_wins;
        }
        
        public int getTies() {

            return this.team_ties;
        }
        
        public int getLosses() {

            return this.team_losses;
        }
        
        public int getScored() {

            return this.team_scoredgoals;
        }
        
        public int getAllowed() {

            return this.team_allowedgoals;
        }
        
        public int getPoints() {

            return this.team_points;
        }
    }
    
    /**
     * Constructs an empty Standings object.
     */
    public Standings() {
        
        //
    }
    
    /**
     * Constructs a Standings object that is initialized with the game data read
     * from the specified file. The result is identical to first constructing an
     * empty Standing object and then calling {@link #readMatchData(java.lang.String) readMatchData(filename)}.
     * @param filename the name of the game data file to read.
     * @throws IOException if there is some kind of an IO error (e.g. if the specified file does not exist).
     */
    public Standings(String filename) throws IOException {

        try(var file = new BufferedReader(new FileReader(filename))) {
            String row = null;
            while((row = file.readLine()) != null) {
                String[] file_row = row.split("\\t");
                String nameA = file_row[0];
                String nameB = file_row[2];
                String game_score = file_row[1];
                int scoreA = Integer.parseInt(game_score.split("-")[0]);
                int scoreB = Integer.parseInt(game_score.split("-")[1]);
                
                addMatchResult(nameA, scoreA, scoreB, nameB);
            }
        }
    }
    
    /**
     * Reads game data from the specified file and updates the team statistics
     * and standings accordingly.<p>The match data file is expected to contain
     * lines of form "teamNameA\tgoalsA-goalsB\tteamNameB". Note that the '\t'
     * are tabulator characters.<p>E.g. the line "Iceland\t3-2\tFinland" would
     * describe a match between Iceland and Finland where Iceland scored 3 and
     * Finland 2 goals.
     * @param filename the name of the game data file to read.
     * @throws IOException if there is some kind of an IO error (e.g. if the specified file does not exist).
     */
    public final void readMatchData(String filename) throws IOException {

        try(var file = new BufferedReader(new FileReader(filename))) {
            String row = null;
            while((row = file.readLine()) != null) {
                String[] file_row = row.split("\\t");
                String nameA = file_row[0];
                String nameB = file_row[2];
                String game_score = file_row[1];
                int scoreA = Integer.parseInt(game_score.split("-")[0]);
                int scoreB = Integer.parseInt(game_score.split("-")[1]);
                
                addMatchResult(nameA, scoreA, scoreB, nameB);
            }
        }
    }
    
    /**
     * Updates the team statistics and standings according to the match result described by the parameters.
     * @param teamNameA the name of the first ("home") team.
     * @param goalsA the number of goals scored by the first team.
     * @param goalsB the number of goals scored by the second team.
     * @param teamNameB the name of the second ("away") team.
     */
    public void addMatchResult(String teamNameA, int goalsA,
            int goalsB, String teamNameB) {

        boolean teamA_found = false;
        boolean teamB_found = false;
        int winner;
        
        if (goalsA > goalsB) {
            winner = 1;
        } else if (goalsA < goalsB) {
            winner = 2;
        } else {
            winner = 3;
        }
        
        for (Team team : this.getTeams()) {
            if (team.getName().equals(teamNameA)) {
                teamA_found = true;
                team.team_scoredgoals += goalsA;
                team.team_allowedgoals += goalsB;
                
                if (winner == 1) {
                    // A wins
                    team.team_wins += 1;
                    team.team_points += 3;
                } else if (winner == 2) {
                    // B wins
                    team.team_losses += 1;
                } else {
                    // Tie
                    team.team_ties += 1;
                    team.team_points += 1;
                }
            }
            if (team.getName().equals(teamNameB)) {
                teamB_found = true;
                team.team_scoredgoals += goalsB;
                team.team_allowedgoals += goalsA;
                
                if (winner == 1) {
                    // A wins
                    team.team_losses += 1;
                } else if (winner == 2) {
                    // B wins
                    team.team_wins += 1;
                    team.team_points += 3;
                } else {
                    // Tie
                    team.team_ties += 1;
                    team.team_points += 1;
                }
            }
        }
        
        if (teamA_found == false) {
            Team teamA = new Team(teamNameA);
            addTeamToList(teamA);
            teamA.team_scoredgoals = goalsA;
            teamA.team_allowedgoals = goalsB;
            
            if (winner == 1) {
                // A wins
                teamA.team_wins += 1;
                teamA.team_points += 3;
            } else if (winner == 2) {
                // B wins
                teamA.team_losses += 1;
            } else {
                // Tie
                teamA.team_ties += 1;
                teamA.team_points += 1;
            }
        }
        if (teamB_found == false) {
            Team teamB = new Team(teamNameB);
            addTeamToList(teamB);
            teamB.team_scoredgoals = goalsB;
            teamB.team_allowedgoals = goalsA;
            
            if (winner == 1) {
                // A wins
                teamB.team_losses += 1;
            } else if (winner == 2) {
                // B wins
                teamB.team_wins += 1;
                teamB.team_points += 3;
            } else {
                // Tie
                teamB.team_ties += 1;
                teamB.team_points += 1;
            }
        }
        
        this.reorderStandings();
    }
    
    /**
     * Returns a list of the teams in the same order as they would appear in a standings table.
     * @return a list of the teams in the same order as they would appear in a standings table.
     */
    public List<Team> getTeams() {

        return this.list_of_teams;
    }
    
    /**
     * Prints a formatted standings table to the provided output stream.
     * @param out the output stream to use when printing the standings table.
     */
    public void printStandings(PrintStream out) {

        this.reorderStandings();
        
        int nameSpace = 0;
        
        for (Team team : this.getTeams()) {
            if (team.getName().length() > nameSpace) {
                nameSpace = team.getName().length();
            }
        }
        
        for (Team team : this.getTeams()) {
            String name = team.getName();
            System.out.print(name);
            
            int spaces = nameSpace - name.length();
            for (int i = 0; i < spaces; i++) {
                System.out.print(" ");
            }
            
            String goalsAB = "";
            goalsAB += team.getScored() + "-" + team.getAllowed();
            
            System.out.format(" %3d ", team.getWins() + team.getLosses() + team.getTies());
            System.out.format("%3d ", team.getWins());
            System.out.format("%3d ", team.getTies());
            System.out.format("%3d ", team.getLosses());
            System.out.format("%6s ", goalsAB);
            System.out.format("%3d\n", team.getPoints());
        }
    }
}
