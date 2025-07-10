package wordguessgame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameGUI extends JFrame {
    private final List<GameRound> rounds;
    private int currentRoundIndex = 0;
    private GameRound gameRound;

    private int score = 0;

    private JTextField guessField;
    private JLabel messageLabel;
    private JLabel imageLabel;
    private JLabel roundLabel;
    private JRadioButton hintToggle;

    public GameGUI(List<GameRound> roundList) {
        this.rounds = roundList;
        if (rounds.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No rounds found.");
            dispose();
            return;
        }
        this.gameRound = rounds.get(0);

        setTitle("GUESS THE WORD!!");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setContentPane(new JLabel(new ImageIcon("C:/Users/ASUS/Downloads/pastel.jpg"))); // Adjust path as needed
        setLayout(new BorderLayout());

        JLabel title = new JLabel("GUESS THE WORD!!", JLabel.CENTER);
        title.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 48));
        title.setForeground(new Color(255, 105, 180));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        roundLabel = new JLabel("ROUND 1", JLabel.CENTER);
        roundLabel.setFont(new Font("Arial Black", Font.BOLD, 30));
        roundLabel.setForeground(Color.BLUE);
        roundLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(roundLabel);

        imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateImage();
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(imageLabel);

        JLabel inputLabel = new JLabel("Enter Your Guess:");
        inputLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
        inputLabel.setForeground(new Color(255, 105, 180));
        inputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(inputLabel);

        guessField = new JTextField(15);
        guessField.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 18));
        guessField.setMaximumSize(new Dimension(250, 35));
        guessField.setHorizontalAlignment(JTextField.CENTER);
        guessField.setBackground(new Color(255, 255, 255, 220));
        guessField.setForeground(Color.DARK_GRAY);
        guessField.setBorder(BorderFactory.createLineBorder(new Color(255, 182, 193), 2));
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(guessField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));

        JButton guessButton = new JButton("SUBMIT");
        guessButton.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
        guessButton.setBackground(new Color(255, 239, 245));
        guessButton.setForeground(new Color(255, 105, 180));
        guessButton.setFocusPainted(false);

        JButton restartButton = new JButton("RESTART");
        restartButton.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
        restartButton.setBackground(new Color(255, 239, 245));
        restartButton.setForeground(new Color(255, 105, 180));
        restartButton.setFocusPainted(false);

        buttonPanel.add(guessButton);
        buttonPanel.add(restartButton);
        centerPanel.add(buttonPanel);

        hintToggle = new JRadioButton("\u2022 hint");
        hintToggle.setSelected(true);
        hintToggle.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        hintToggle.setForeground(new Color(153, 50, 204));
        hintToggle.setOpaque(false);
        hintToggle.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(hintToggle);

        messageLabel = new JLabel(" ", JLabel.CENTER);
        messageLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        messageLabel.setForeground(new Color(75, 0, 130));
        messageLabel.setOpaque(false);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setMaximumSize(new Dimension(600, 40));
        messageLabel.setPreferredSize(new Dimension(600, 40));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(Box.createVerticalStrut(25));
        centerPanel.add(messageLabel);

        add(centerPanel, BorderLayout.CENTER);

        guessButton.addActionListener(e -> checkGuessWithFeedback(guessField.getText().trim()));
        restartButton.addActionListener(e -> restartGame());

        setVisible(true);
    }

    private void checkGuessWithFeedback(String guess) {
        if (gameRound.isCorrectGuess(guess)) {
            score += 10;
            messageLabel.setText("\u2705 Correct! Loading next round...");
            Timer timer = new Timer(1500, e -> {
                if (currentRoundIndex + 1 < rounds.size()) {
                    currentRoundIndex++;
                    gameRound = rounds.get(currentRoundIndex);
                    guessField.setText("");
                    messageLabel.setText("\uD83C\uDF89 New round!");
                    updateImage();
                } else {
                    endGame();
                }
            });
            timer.setRepeats(false);
            timer.start();
        } else {
            if (hintToggle.isSelected()) {
                messageLabel.setText("\u274C " + gameRound.getNextHint());
            } else {
                messageLabel.setText("\u274C Incorrect.");
            }
        }
    }

    private void updateImage() {
        try {
            ImageIcon icon = new ImageIcon(gameRound.getImageFilePath());
            Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
            roundLabel.setText("ROUND " + (currentRoundIndex + 1));
        } catch (Exception e) {
            imageLabel.setText("[Image not found]");
        }
    }

    private void restartGame() {
        for (GameRound round : rounds) round.resetHints();
        currentRoundIndex = 0;
        gameRound = rounds.get(0);
        guessField.setText("");
        guessField.setEnabled(true);
        messageLabel.setText("");
        updateImage();
        score = 0;
    }

    private void endGame() {
        String playerName = JOptionPane.showInputDialog(this, "Enter your name:");
        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Unknown Player";
        }

        PlayerDataSaver.savePlayerData(playerName, score);  // Must be implemented in your project
        JOptionPane.showMessageDialog(this, "🎉 Game Over!\nScore: " + score);
        dispose();
    }
}
