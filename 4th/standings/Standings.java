import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Standings {

    // Private
    private final ArrayList<Team> list_of_teams = new ArrayList<>();
    private void reorderStandings() {
        
        //
    }
    
    // Public
    public class Team {
        
        // Private
        private final String team_name;
        private int team_wins;
        private int team_ties;
        private int team_losses;
        private int team_scoredgoals;
        private int team_allowedgoals;
        private int team_points;
        
        // Public
        public Team(String name) {
            this.team_name = name;
        }
        
        public String getName() {
            
            // Returns the name of the team;
            
            return this.team_name;
        }
        
        public int getWins() {
            
            // Returns the amount of wins a team has
            
            return this.team_wins;
        }
        
        public int getTies() {
            
            // Returns the amount of ties a team has
            
            return this.team_ties;
        }
        
        public int getLosses() {
            
            // Returns the amount of losses a team has
            
            return this.team_losses;
        }
        
        public int getScored() {
            
            // Returns the amount of scored goals a team has
            
            return this.team_scoredgoals;
        }
        
        public int getAllowed() {
            
            // Returns the amount of allowed goals a team has
            
            return this.team_allowedgoals;
        }
        
        public int getPoints() {
            
            // Returns the amount of ladder points a team has
            
            return this.team_points;
        }
    }
    
    public Standings() {
        
        //
    }
    
    public Standings(String filename) throws IOException {
        
        //
        
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
        catch(IOException e) {
            System.out.println("Duba");
        }
    }
    
    public void readMatchData(String filename) {
        
        //
        
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
        catch(IOException e) {
            System.out.println("Duba");
        }
    }
    
    public void addMatchResult(String teamNameA, int goalsA,
            int goalsB, String teamNameB) {
        
        //
    }
    
    public ArrayList<Team> getTeams() {
        
        //
        
        return this.list_of_teams;
    }
    
    public void printStandings() {
        
        //
    }
}
