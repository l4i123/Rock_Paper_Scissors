package main;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JLabel;
import java.awt.Font;

class GamePanel extends JPanel {
    final int OriginalTileSize = 8;
    final int scale = 3; // 8 * 3 = 24

    public final int tileSize = OriginalTileSize * scale; // 24
    public final int maxCol = 20;
    public final int maxRow = 30;
    public final int screenWidth = tileSize * maxCol;
    public final int screenHeight = tileSize * maxRow;

    private JLabel resultLabel;
    private JLabel resultWin;
    private JLabel scoreLabel;
    private JLabel finalWin;

    private int pcPoint = 0;
    private int playerPoint = 0;

    private JButton button1;  
    private JButton button2;
    private JButton button3;

    // Constructor
    public GamePanel() {
        this.setPreferredSize(new java.awt.Dimension(screenWidth, screenHeight));
        this.setLayout(null); // Setting layout to null to manually position components

        // Initialize buttons
        button1 = new JButton("Камък");
        button1.setBounds(50, 600, 100, 50);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computereChoice("Камък");
            }
        });

        button2 = new JButton("Ножица");
        button2.setBounds(200, 600, 100, 50);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computereChoice("Ножица");
            }
        });

        button3 = new JButton("Хартия");
        button3.setBounds(350, 600, 100, 50);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computereChoice("Хартия");
            }
        });

        // Add the buttons to the panel
        this.add(button1);
        this.add(button2);
        this.add(button3);

        // Create the result label
        resultLabel = new JLabel("");
        resultLabel.setBounds(100, 100, 400, 50);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(resultLabel);

        resultWin = new JLabel("");
        resultWin.setBounds(180, 300, 400, 50);
        resultWin.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(resultWin);

        finalWin = new JLabel("");
        finalWin.setBounds(150, 350, 400, 50);
        finalWin.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(finalWin);

        scoreLabel = new JLabel("Играч: 0 - Компютър: 0");
        scoreLabel.setBounds(100, 50, 400, 50);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size
        this.add(scoreLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // You can use Graphics2D here for more advanced drawing
    }

    public void computereChoice(String playerChoice) {
        String[] option = {"Камък", "Ножица", "Хартия"};
        Random random = new Random();
        String pcChoice = option[random.nextInt(option.length)];

        resultLabel.setText("<html> Компютърът избра " + pcChoice + "</html>");

        winnerLogic(playerChoice, pcChoice);
    }

    public void winnerLogic(String playerChoice, String pcChoice) {
        if (playerChoice.equals(pcChoice)) {
            resultWin.setText("<html>Равно</html>");
        } else if ((playerChoice.equals("Ножица") && pcChoice.equals("Камък")) ||
                   (playerChoice.equals("Камък") && pcChoice.equals("Хартия")) ||
                   (playerChoice.equals("Хартия") && pcChoice.equals("Ножица"))) {
            pcPoint++;
            resultWin.setText("<html>Загуби!</html>");
        } else {
            playerPoint++;
            resultWin.setText("<html>Спечели!</html>");
        }

        scoreLabel.setText("Играч: " + playerPoint + " - Компютър: " + pcPoint);

        // Disable buttons and declare the winner when either player reaches 3 points
        if (playerPoint == 3) {
            finalWin.setText("<html>Играчът печели!</html>");
            disableButtons();
        } else if (pcPoint == 3) {
            finalWin.setText("<html>Компютърът печели!</html>");
            disableButtons();
        }
    }

    // Method to disable buttons
    public void disableButtons() {
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
    }
}