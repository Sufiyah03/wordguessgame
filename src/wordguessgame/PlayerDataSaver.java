package wordguessgame;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PlayerDataSaver {
    public static void savePlayerData(String playerName, int score) {
        try (FileWriter writer = new FileWriter("player_data.txt", true)) { // append = true
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = LocalDateTime.now().format(dtf);

            writer.write("Player: " + playerName + "\n");
            writer.write("Score: " + score + "\n");
            writer.write("Date: " + timestamp + "\n");
            writer.write("----------------------\n");
        } catch (IOException e) {
            System.err.println("Error saving player data: " + e.getMessage());
        }
    }
}
