package wordguessgame;

public class OrangeRound extends GameRound {
    public OrangeRound() {
        wordToGuess = "orange";
    }

    @Override
    public String getNextHint() {
        hintCount++;
        switch (hintCount) {
            case 1: return " Hint: It's also a color.";
            case 2: return " Hint: Juicy and citrusy.";
            case 3: return " Hint: Starts with 'o'";
            default: return " No more hints!";
        }
    }

    @Override
    public String getImageFilePath() {
        return "C:/Users/ASUS/Downloads/wordguessga/wordguessga/orange.jpeg";
    }
}
