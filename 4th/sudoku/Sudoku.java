import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;

public class Sudoku {

    // private
    private final HashMap<Integer, HashMap<Integer, String>> board = new HashMap<>();
    
    // public
    public Sudoku() {
        
        for (int y = 0; y < 9; y++) {
            HashMap<Integer, String> x_row = new HashMap<>();
            for (int x = 0; x < 9; x++) {
                x_row.put(y, " ");
            }
            this.board.put(y, x_row);
        }
    }
    
    public void set(int i, int j, char c) {
        
        ArrayList<String> accepted;
        accepted = new ArrayList<>(Arrays.asList(" ", "1", "2", "3", "4", "5",
                "6", "7", "8", "9"));
        
        if (i > 8 | j > 8) {
            System.out.format("Trying to access illegal cell (%d, %d)\n", i, j);
        } else if (!accepted.contains(String.valueOf(c))) {
            System.out.print("Trying to set illegal character ");
            System.out.format("%s to cell (%d, %d)\n", String.valueOf(c), i, j);
        } else {
            for (var row : this.board.get(i).keySet()) {
                if (row == j) {
                    this.board.get(i).put(j, String.valueOf(c));
                }
            }
        }
    }
    
    public boolean check() {
        
        int y = 0;
        int x = 0;
        String duplicate;
        HashSet<String> char_check = new HashSet<>();
        int lowestduplicate = 10;
        int outcome = 0;
        boolean doubles = false;
        
        // Check rows
        while (y < 9) {
            while (x < 9) {
                duplicate = String.valueOf(this.board.get(y).get(x));
                if (!" ".equals(duplicate)) {
                    if (!char_check.contains(duplicate)) {
                        char_check.add(duplicate);
                    } else {
                        doubles = true;
                        outcome = 1;
                        if (Integer.parseInt(duplicate) < lowestduplicate) {
                            lowestduplicate = Integer.parseInt(duplicate);
                        }
                    }
                }
                x++;
            }
            y++;
        }
        
        // Check columns

        // Check small squares
        
        // Act on findings
        if (doubles == true) {
            if (outcome == 1) {
                // A row is fucked
                // System.out.format("Row %d has multiple %d's!", y_location, lowestduplicate);
                return false;
            } else if (outcome == 2) {
                // A column is fucked
                return false;
            } else if (outcome == 3) {
                // A box is fucked
                return false;
            }
        }
        
        return true;
    }
    
    public void print() {
        
        int xctr = 0;
        
        int y = 0;
        int x = 0;
        
        while (y < 9) {
            if (y % 3 == 0) {
                System.out.println("#####################################");
                } else {
                    System.out.println("#---+---+---#---+---+---#---+---+---#");
                }
            while (x < 9) {
                if (xctr % 3 == 0) {
                System.out.print("#");
                } else {
                    System.out.print("|");
                }
                System.out.format("%2s ", this.board.get(y).get(x));
                if (x == 8) {
                    System.out.println("#");
                }
                x++;
                xctr++;
            }
            x = 0;
            y++;
        }
        
        System.out.println("#####################################");
    }
}
