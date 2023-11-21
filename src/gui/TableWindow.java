package gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import model.*;

public class TableWindow extends BaseWindow {
    private Model game;
    private int numberOfRows;
    private int numberOfCols;
    private JLabel actualPlayerLabel;
    private JButton[][] buttons;
    private boolean blockCellButtons = false;
    
    public TableWindow(int row, int col) {
        super(80 * col, 80 * row + BASIC_TEXT_SIZE);
        BasePanel headerPanel = new BasePanel();
        BasePanel gridPanel = new BasePanel();
        game = new Model(row, col);
        buttons = new JButton[row][col];
        numberOfRows = row;
        numberOfCols = col;
        actualPlayerLabel = getJLabel(getActualPlayerLabelText());

        headerPanel.add(actualPlayerLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        headerPanel.add(getNormalButton());
        gridPanel.setLayout(new GridLayout(row, col));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                buttons[i][j] = getTableCell(j);
                gridPanel.add(buttons[i][j]);
            }
        }

        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(gridPanel, BorderLayout.CENTER);
    }

    private JButton getTableCell(int col) {
        JButton button = new JButton();
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Source Sans Pro", Font.PLAIN, BASIC_TEXT_SIZE));

        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!blockCellButtons && game.isInsertable(col)) {
                        GameStates player = game.getActualPlayer();
                        int row = game.insertIntoTable(col, game.getActualPlayer());
                        actualPlayerLabel.setText(getActualPlayerLabelText());
                        GameStates winner = game.getWinner(row, col);

                        buttons[numberOfRows - row - 1][col].setText(player.getValue());
                        if(winner != GameStates.Nobody) {
                            actualPlayerLabel.setText("Winner: " + winner);
                            showGameOverMessage(winner);
                            blockCellButtons = true;
                        }
                    }
                }
            }
        );

        return button;
    }

    private JButton getNormalButton() {
        JButton button = new JButton("New game");
        button.setFont(new Font("Source Sans Pro", Font.PLAIN, BASIC_TEXT_SIZE));
        button.setBackground(Color.WHITE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMargin(new Insets(5, 5, 5, 5));
        button.setFocusPainted(false);

        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TableWindow tableWindow = new TableWindow(numberOfRows, numberOfCols);
                    tableWindow.setVisible(true);
                    TableWindow.this.doUponExit();
                }
            }
        );

        return button;
    }

    private void showGameOverMessage(GameStates winner) {
        JOptionPane.showMessageDialog(this, "Game over, winner: " + winner.getValue());
    }

    private String getActualPlayerLabelText() {
        return "Current player: " + game.getActualPlayer();
    }

}
