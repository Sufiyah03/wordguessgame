package wordguessgame;

public class BananaRound extends GameRound {
    public BananaRound() {
        wordToGuess = "banana";
    }

    @Override
    public String getNextHint() {
        hintCount++;
        switch (hintCount) {
            case 1: return " Hint: It's yellow and curved.";
            case 2: return " Hint: Monkeys love it.";
            case 3: return " Hint: Starts with 'b'";
            default: return " No more hints!";
        }
    }

    @Override
    public String getImageFilePath() {
        return "C:/Users/ASUS/Downloads/wordguessga/wordguessga/banana.png";
    }
}
