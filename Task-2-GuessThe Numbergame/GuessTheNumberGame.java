import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GuessTheNumberGame extends JFrame implements ActionListener {

    private int randomNumber;
    private int remainingAttempts;
    private JLabel titleLabel;
    private JLabel instructionLabel;
    private JTextField guessField;
    private JLabel resultLabel;
    private JButton submitButton;
    private JButton restartButton;

    public GuessTheNumberGame() {
        super("Guess the Number Game");
        setLayout(new GridLayout(5, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        titleLabel = new JLabel("Guess the Number ", SwingConstants.CENTER);
        instructionLabel = new JLabel("I am thinking of a number between 1 and 100. You have 10 attempts to guess it.",
                SwingConstants.CENTER);
        guessField = new JTextField(10);
        guessField.addActionListener(this);
        resultLabel = new JLabel(" ", SwingConstants.CENTER);
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        restartButton = new JButton("Restart");
        restartButton.addActionListener(this);

        add(titleLabel);
        add(instructionLabel);
        add(guessField);
        add(resultLabel);
        add(submitButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        startNewGame();
    }

    public void startNewGame() {
        randomNumber = (int) (Math.random() * 100) + 1;
        remainingAttempts = 10;
        guessField.setText("");
        resultLabel.setText(" ");
        guessField.setEditable(true);
        submitButton.setEnabled(true);
        restartButton.setEnabled(false);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == submitButton || event.getSource() == guessField) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                if (guess < 1 || guess > 100) {
                    resultLabel.setText("Please enter a number between 1 and 100.");
                } else {
                    remainingAttempts--;
                    if (guess == randomNumber) {
                        guessField.setEditable(false);
                        submitButton.setEnabled(false);
                        restartButton.setEnabled(true);
                        // resultLabel.setText("Congratulations! You guessed the number in " + (10 -
                        // remainingAttempts)
                        // + " attempts.");
                        resultLabel.setText("Your score: " + (remainingAttempts) * 10);

                    } else if (remainingAttempts == 0) {
                        guessField.setEditable(false);
                        submitButton.setEnabled(false);
                        restartButton.setEnabled(true);
                        resultLabel.setText("Game over. The number was " + randomNumber + ".");
                    } else {
                        String hint = guess < randomNumber ? "higher" : "lower";
                        resultLabel.setText("Try again. The number is " + hint + " than " + guess + ". You have "
                                + remainingAttempts + " attempts left.");
                    }
                }
            } catch (NumberFormatException e) {
                resultLabel.setText("Please enter a valid number.");
            }
        } else if (event.getSource() == restartButton) {
            startNewGame();
        }
    }

    public static void main(String[] args) {
        new GuessTheNumberGame();
    }
}
