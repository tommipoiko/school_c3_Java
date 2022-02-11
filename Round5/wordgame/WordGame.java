import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.*;

public class WordGame {

    // Private
    private boolean gameActive = false;
    private ArrayList<String> words;
    private WordGameState state;
    
    // Public
    public static class WordGameState {
        
        // Private
        private static String theWord = "";
        private static String sysWord = "";
        private static int mistakes = 0;
        private static int errorLimit = 0;
        private static int missingChars;
        private static ArrayList<Character> foundChars;
        
        private WordGameState() {
            theWord = "";
            sysWord = "";
            mistakes = 0;
            errorLimit = 0;
            foundChars = new ArrayList<>();
        }
        
        // Public
        public String getWord() {
            
            return theWord;
        }
        
        public int getMistakes() {
            
            return mistakes;
        }
        
        public int getMistakeLimit() {
            
            return errorLimit;
        }
        
        public int getMissingChars() {
            
            return missingChars;
        }
    }
    
    public WordGame(String wordFilename) throws IOException {
        
        words = new ArrayList<>();
        
        try(var wordFile = new BufferedReader(new FileReader(wordFilename))) {
            String word;
            while((word = wordFile.readLine()) != null) {
                words.add(word);
            }
        }
    }
    
    public void initGame(int wordIndex, int mistakeLimit) {
        
        state = null;
        state = new WordGameState();
        WordGameState.sysWord = words.get(wordIndex % words.size());
        WordGameState.missingChars = WordGameState.sysWord.length();
        for (int i = 0; i < WordGameState.sysWord.length(); i++) {
            WordGameState.theWord += "_";
        }
        WordGameState.errorLimit = mistakeLimit;
        gameActive = true;
    }
    
    public boolean isGameActive() {
        
        return gameActive;
    }
    
    public WordGameState getGameState() throws GameStateException {
        
        if (!isGameActive()) {
            throw new GameStateException(String.format("There is currently no active word game!"));
        } else {
            return state;
        }
    }
    
    public WordGameState guess(char c) throws GameStateException {
        
        if (!isGameActive()) {
            throw new GameStateException(String.format("There is currently no active word game!"));
        } else {
            if (WordGameState.sysWord.indexOf(Character.toLowerCase(c)) != -1) {
                if (!WordGameState.foundChars.contains(Character.toLowerCase(c))) {
                    WordGameState.missingChars = 0;
                    String line = "";
                    int ix = 0;
                    while (ix < WordGameState.sysWord.length()) {
                        if (WordGameState.theWord.charAt(ix) == '_') {
                            if (WordGameState.sysWord.charAt(ix) == Character.toLowerCase(c)) {
                                line += Character.toLowerCase(c);
                                WordGameState.foundChars.add(Character.toLowerCase(c));
                            } else {
                                line += "_";
                                WordGameState.missingChars++;
                            }
                        } else {
                            line += WordGameState.theWord.charAt(ix);
                        }
                        ix++;
                    }
                    WordGameState.theWord = line;
                    if (WordGameState.missingChars == 0) {
                        gameActive = false;
                    }
                } else {
                    WordGameState.mistakes++;
                    if (WordGameState.mistakes > WordGameState.errorLimit) {
                        WordGameState.theWord = WordGameState.sysWord;
                        gameActive = false;
                    }
                }
            } else {
                WordGameState.mistakes++;
                if (WordGameState.mistakes > WordGameState.errorLimit) {
                    WordGameState.theWord = WordGameState.sysWord;
                    gameActive = false;
                }
            }
            
            return state;
        }
    }
    
    public WordGameState guess(String word) throws GameStateException {
        
        if (!isGameActive()) {
            throw new GameStateException(String.format("There is currently no active word game!"));
        } else {
            if (word.equals(WordGameState.sysWord)) {
                WordGameState.theWord = word;
                WordGameState.missingChars = 0;
                gameActive = false;
            } else {
                WordGameState.mistakes++;
                if (WordGameState.mistakes > WordGameState.errorLimit) {
                    WordGameState.theWord = WordGameState.sysWord;
                    gameActive = false;
                }
            }
            
            return state;
        }
    }
}
