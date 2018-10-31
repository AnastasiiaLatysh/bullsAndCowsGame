package bullsAndCowsGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class GameController implements ActionListener {

    private int attemptToGuess = 1;
    private int numberToGuess = generateRandomNumberWithFourDistinctDigits();
    private int amountOfWins = 0;
    private int amountOfFails = 0;

    private GameViewer gameViewer;
    private GameModel gameModel;

    public GameController(GameViewer gameViewer, GameModel gameModel){
        this.gameViewer = gameViewer;
        this.gameModel = gameModel;
        // add actionListener viewers object
        this.gameViewer.guess.addActionListener(this);
        this.gameViewer.clear.addActionListener(this);
        this.gameViewer.restart.addActionListener(this);
        this.gameViewer.inputLine.addActionListener(this);
        this.gameViewer.aboutGame.addActionListener(this);
        this.gameViewer.gameRules.addActionListener(this);
        this.gameViewer.newGame.addActionListener(this);
        this.gameViewer.resetStatistics.addActionListener(this);
        this.gameViewer.exitGame.addActionListener(this);
    }

    private void guess(){
        String sUserPotentialNumber = getEnteredUserNumber();

        if (isCorrectNumberEntered(sUserPotentialNumber)){
            int userPotentialNumber = Integer.parseInt(sUserPotentialNumber);
            if (attemptToGuess <= 10 && this.gameModel.isNumberGuessed(numberToGuess, userPotentialNumber))
                setWonResult();
            else if (attemptToGuess < 10 && !this.gameModel.isNumberGuessed(numberToGuess, userPotentialNumber))
            {
                setCowsAndBulls(userPotentialNumber);
                attemptToGuess++;
            }
            else {
                setCowsAndBulls(userPotentialNumber);
                setFailedResult();
            }
        }
    }

    private void setFailedResult(){
        gameViewer.showDialog("Sorry, you failed. Number to guess was " + numberToGuess + ". Try again!");
        gameViewer.tableModel.setRowCount(0);
        gameViewer.amountOfFails.setText("Fails: " + ++amountOfFails);
        resetNumberAndAttempts();
    }

    private void setWonResult(){
        gameViewer.showDialog("Congratulations! You won :) Number to guess was " + numberToGuess);
        gameViewer.tableModel.setRowCount(0);
        gameViewer.amountOfWins.setText("Wins: " + ++amountOfWins);
        resetNumberAndAttempts();
    }

    private void resetNumberAndAttempts(){
        numberToGuess = generateRandomNumberWithFourDistinctDigits();
        attemptToGuess = 1;
    }

    private String getEnteredUserNumber() {
        return gameViewer.inputLine.getText();
    }

    private void setCowsAndBulls(int userPotentialNumber){
        gameViewer.tableModel.addRow(new Object[]{userPotentialNumber, gameModel.getBulls(), gameModel.getCows(), attemptToGuess});
    }

    private static boolean isNumberContainsDuplicateDigits(int numberToGuess){
        // Ми маємо 10 цифер у 10-ній системі счислення, тому створюю масив на 10 елементів, які мають значення false
        boolean[] digits = new boolean[10];

        while(numberToGuess > 0){
            // якщо значення цифри вже існує, тобто дорівню true, повертаємо True --> число має повторювані числа
            if(digits[numberToGuess%10])
                return true;
            // якщо значення цифри не існує, то змінюємо значення відповідного числа на true
            digits[numberToGuess%10] = true;
            // ділимо число на 10, щоб відкинути останню цифру, яку вже записали на попердньому кроці
            numberToGuess= numberToGuess / 10;
        }
        return false;
    }

    private int generateRandomNumberWithFourDistinctDigits(){
        int numberToGuess;
        do {
            /* генеруємо довільне чотиризначне число від 0 до 8999 та доаємо до нього 1000,
        щоб число завжди було у проміжку від 1000 до 9999 */
            numberToGuess = new Random().nextInt(9000) + 1000;
        }
        // поки число має цифри-дублікати, генеруємо нове чотирьох значене число
        while (isNumberContainsDuplicateDigits(numberToGuess));
        return numberToGuess;
    }

    private boolean isCorrectNumberEntered(String sUserPotentialNumber){
        try{
            int userPotentialNumber = Integer.parseInt(sUserPotentialNumber);
            if (userPotentialNumber < 1000 || userPotentialNumber > 9999) {
                gameViewer.showDialog("Do you remember that number contains 4 digits?)\n Not more and not less!" +
                        "\nNote number can not start from 0.");
                return false;
            } else if (isNumberContainsDuplicateDigits(userPotentialNumber)) {
                gameViewer.showDialog("Do you remember that number doesn't contain duplicate digits?)");
                return false;
            }
            return true;
        }
        catch (NumberFormatException ex){
            gameViewer.showDialog("Please, enter correct number. Note number can not start from 0.\n" +
                    "Only number in interval from 1000 till 999 are allowed");
            return false;
        }
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() instanceof JButton)
            actionForButton(event);
        if(event.getSource() instanceof JMenuItem)
            actionForMenu(event);
    }

    private void actionForButton(ActionEvent event){
        JButton eventButton = (JButton) event.getSource();
        if(eventButton.getText().equals("Guess"))
            guess();
        else if(eventButton.getText().equals("Clear"))
            clearInputField();
        else if(eventButton.getText().equals("Restart"))
            restart();
    }

    private void actionForMenu(ActionEvent event){
        String command = event.getActionCommand();
        switch (command){
            case "New Game":
                restart();
                break;
            case "Reset statistics":
                resetStatistics();
                break;
            case "Exit":
                System.exit(0);
                break;
            case "Game rules":
                JOptionPane.showMessageDialog(null, getInstructionsText());
                break;
            case "About":
                JOptionPane.showMessageDialog(null, "This is game 'Bulls and Cows' designed" +
                        " by Anastasiia Latysh");
                break;
        }
    }

    private void clearInputField()
    {
        gameViewer.inputLine.setText("");
    }

    private void restart()
    {
        resetNumberAndAttempts();
        gameViewer.tableModel.setRowCount(0);
        gameViewer.inputLine.setText("");
    }

    private void resetStatistics()
    {
        gameViewer.amountOfFails.setText("Fails: " + 0);
        gameViewer.amountOfWins.setText("Wins: " + 0);
    }

    private String getInstructionsText(){
        return"Instructions:\n1. You need to guess 4 digit random number " +
                "which doesn't contain duplicate digits.\n2. You have ten attempts to guess number.\n" +
                "3. After each step you can see amount of bulls and amount of cows.\n" +
                "Bulls mean that digit is at the correct place.\n" +
                "Cows mean that digit is present in number but is placed on the wrong place.";
    }

}
