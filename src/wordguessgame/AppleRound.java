package wordguessgame;

public class AppleRound extends GameRound {
    public AppleRound() {
        wordToGuess = "apple";
    }

    @Override
    public String getNextHint() {
        hintCount++;
        switch (hintCount) {
            case 1: return " Hint: It's green or red.";
            case 2: return " Hint: Keeps the doctor away.";
            case 3: return " Hint: Starts with 'a'";
            default: return " No more hints!";
        }
    }

    @Override
    public String getImageFilePath() {
        return "C:/Users/ASUS/Downloads/wordguessga/wordguessga/apple.png";
    }
}
