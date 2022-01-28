// import here

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
            
            //
            
            return this.team_wins;
        }
        
        public int getTies() {
            
            //
            
            return this.team_ties;
        }
        
        public int getLosses() {
            
            //
            
            return this.team_losses;
        }
        
        public int getScored() {
            
            //
            
            return this.team_scoredgoals;
        }
        
        public int getAllowed() {
            
            //
            
            return this.team_allowedgoals;
        }
        
        public int getPoints() {
            
            //
            
            return this.team_points;
        }
    }
    
    public Standings() {
        
        //
    }
}
