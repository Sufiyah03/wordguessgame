package wordguessgame;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 🔊 Start background lofi music
        SoundPlayer music = new SoundPlayer();
        music.playLoop("C:/Users/ASUS/Downloads/435/435/massobeats - rose water (royalty free lofi music).wav");

        // 🎮 Launch the game
        List<GameRound> rounds = new ArrayList<>();
        rounds.add(new AppleRound());
        rounds.add(new BananaRound());
        rounds.add(new OrangeRound());

        new GameGUI(rounds);
    }
}
