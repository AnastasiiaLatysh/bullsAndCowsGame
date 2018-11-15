package bullsAndCowsGame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


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
    JLabel cowIcon;
    JLabel bullIcon;

    public GameViewer()
    {
        setSize(1000,600);
        setLocation(100,100);
        setTitle("Bulls and Cows");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener( new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
                inputLine.requestFocus();
            }});

        container = getContentPane();
        container.setBackground(Color.white);
        container.setLayout(null);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        createFileMenu();
        createHelpMenu();
        createLabels();
        createIcon();

        createTable();
        createInputLine();
        createButtons();
    }

    private void createButtons()
    {
        guess = new JButton("Guess");
        guess.setBounds(220,482,280,40);
        container.add(guess);
        restart = new JButton("Restart");
        restart.setBounds(510,482,280,40);
        container.add(restart);
        clear = new JButton("Clear");
        clear.setBounds(594,432,186,40);
        container.add(clear);

    }

    private void createIcon(){
        ImageIcon imageIconCow = new ImageIcon(System.getProperty("user.dir") + "/src/bullsAndCowsGame/cow.jpg");
        cowIcon = new JLabel(imageIconCow);
        cowIcon.setBounds(0,130,230,300);
        container.add(cowIcon);

        ImageIcon imageIconBull = new ImageIcon(System.getProperty("user.dir") + "/src/bullsAndCowsGame/bull.jpg");
        bullIcon = new JLabel(imageIconBull);
        bullIcon.setBounds(780,130,230,300);
        container.add(bullIcon);

    }

    private void createTable(){
        String[] columnNames = {"Number", "Bulls", "Cows", "Attempts"};
        tableModel = new DefaultTableModel(columnNames,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // or a condition at your choice with row and column
            }
        };
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
        scrollPane.setBounds(220,140,560,280);
        container.add(scrollPane);
    }

    private void createInputLine()
    {
        inputLine = new JTextField();
        inputLine.setBounds(220,432,360,40);
        inputLine.setDocument(new JTextLimit(4));
        container.add(inputLine);
    }

    private void createLabels()
    {
        amountOfFails = new JLabel("Fails : 0");
        amountOfFails.setBounds(570,70,100,102);
        amountOfFails.setForeground(Color.red);
        amountOfFails.setFont(setFontStyle(20));
        container.add(amountOfFails);

        amountOfWins = new JLabel("Wins : 0");
        amountOfWins.setBounds(370,70,100,102);
        amountOfWins.setForeground(Color.green);
        amountOfWins.setFont(setFontStyle(20));
        container.add(amountOfWins);

        startLabel = new JLabel("<html><h2><center>" +
                "Guess a number of 4 digits which doesn't contain duplicate digits.<br></center></h2>" +
                "<h3><center>You have 10 attempts to win!" + "</center></h3></html>");
        startLabel.setBounds(200,0,800,100);
        startLabel.setForeground(Color.DARK_GRAY);
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
