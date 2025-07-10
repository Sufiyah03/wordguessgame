package wordguessgame;

public abstract class GameRound {
    protected String wordToGuess;
    protected int hintCount = 0;
    protected final int maxHints = 3;

    public boolean isCorrectGuess(String guess) {
        return guess.equalsIgnoreCase(wordToGuess);
    }

    public abstract String getNextHint();

    public void resetHints() {
        hintCount = 0;
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public abstract String getImageFilePath(); // now uses full path
}
