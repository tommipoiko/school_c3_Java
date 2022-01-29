import java.util.ArrayList;

public class Standings {

    // Private
    
    // Public
    public class Team {
        
        // Private
        private String team_name;
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
    
    public Standings(String filename) {
        
        //
    }
    
    public void readMatchData(String filename) {
        
        //
    }
    
    public void addMatchResult(String teamNameA, int goalsA,
            int goalsB, String teamNameB) {
        
        //
    }
    
    public ArrayList<Team> getTeams() {
        
        //
        
        ArrayList<Team> teams = new ArrayList<>();
        
        return teams;
    }
    
    public void printStandings() {
        
        //
    }
}
