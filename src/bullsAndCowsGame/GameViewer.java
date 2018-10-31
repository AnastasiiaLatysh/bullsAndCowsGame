package bullsAndCowsGame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class GameViewer extends JFrame {

    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu helpMenu;
    JMenuItem aboutGame;
    JMenuItem gameRules;
    JMenuItem newGame;
    JMenuItem resetStatistics;
    JMenuItem exitGame;
    JScrollPane scrollPane;
    JTextField inputLine;
    JButton guess;
    JButton restart;
    JButton clear;
    Container container;
    JLabel startLabel;
    JLabel amountOfWins;
    JLabel amountOfFails;
    JTable table;
    DefaultTableModel tableModel;

    public GameViewer()
    {
        setSize(600,600);
        setLocation(100,100);
        setTitle("Bulls and Cows");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setBackground(Color.white);
        container.setLayout(null);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        createFileMenu();
        createHelpMenu();
        createLabels();

        createTable();
        createInputLine();
        createButtons();
    }

    private void createButtons()
    {
        guess = new JButton("Guess");
        guess.setBounds(10,482,186,40);
        container.add(guess);
        restart = new JButton("Restart");
        restart.setBounds(206,482,186,40);
        container.add(restart);
        clear = new JButton("Clear");
        clear.setBounds(402,482,186,40);
        container.add(clear);

    }

    private void createTable(){
        String[] columnNames = {"Number", "Bulls", "Cows", "Attempts"};
        tableModel = new DefaultTableModel(columnNames,0);
        table = new JTable(tableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer)
                table.getTableHeader().getDefaultRenderer();

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getTableHeader().setFont(setFontStyle(20));


        for (String columnName: columnNames)
            table.getColumn(columnName).setCellRenderer(centerRenderer);


        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20,140,560,280);
        container.add(scrollPane);
    }

    private void createInputLine()
    {
        inputLine = new JTextField();
        inputLine.setBounds(10,432,580,40);
        container.add(inputLine);
    }

    private void createLabels()
    {
        amountOfFails = new JLabel("Fails : 0");
        amountOfFails.setBounds(370,40,100,102);
        amountOfFails.setForeground(Color.red);
        amountOfFails.setFont(setFontStyle(20));
        container.add(amountOfFails);

        amountOfWins = new JLabel("Wins : 0");
        amountOfWins.setBounds(170,40,100,102);
        amountOfWins.setForeground(Color.green);
        amountOfWins.setFont(setFontStyle(20));
        container.add(amountOfWins);

        startLabel = new JLabel("<html><div style='text-align: center;'><u>" +
                "Guess a number of 4 digits which doesn't contain duplicate digits. " +
                "You have 10 attempts to win!" + "</u></div></html>");
        startLabel.setBounds(55,0,500,100);
        startLabel.setForeground(Color.darkGray);
        startLabel.setFont(setFontStyle(17));
        container.add(startLabel);
    }

    private void createFileMenu()
    {
        fileMenu = new JMenu("File");
        newGame = new JMenuItem("New Game");
        resetStatistics = new JMenuItem("Reset statistics");
        exitGame = new JMenuItem("Exit");

        fileMenu.add(newGame);
        fileMenu.addSeparator();

        fileMenu.add(resetStatistics);
        fileMenu.addSeparator();

        fileMenu.add(exitGame);
        menuBar.add(fileMenu);
    }

    private void createHelpMenu()
    {
        helpMenu = new JMenu("Help");
        gameRules = new JMenuItem("Game rules");
        helpMenu.add(gameRules);
        helpMenu.addSeparator();
        aboutGame = new JMenuItem("About");
        helpMenu.add(aboutGame);
        menuBar.add(helpMenu);
    }

    private Font setFontStyle(int fontSize){
        return new Font("Serif", Font.BOLD, fontSize);
    }

    void showDialog(String message){
        JOptionPane.showMessageDialog(null, message);
    }
}
