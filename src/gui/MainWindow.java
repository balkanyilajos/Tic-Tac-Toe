package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends BaseWindow {
    public static final int[][] mapSizes = { {8, 5}, {10, 6}, {12, 7} };
    private BasePanel panel;

    public MainWindow() {
        super(300, 65 * mapSizes.length + 3 * BASIC_TEXT_SIZE);
        panel = new BasePanel();
        add(panel);
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(getJLabel("Map sizes:"));

        for(int[] map : mapSizes) {
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(getMapButton(map[0], map[1]));
        }
    }

    private JButton getMapButton(int numberOfRows, int numberOfCols) {
        JButton button = new JButton(numberOfRows + "x" + numberOfCols);
        button.setMaximumSize(new Dimension(150, 50));
        button.setFont(new Font("Source Sans Pro", Font.PLAIN, BASIC_TEXT_SIZE));
        button.setBackground(Color.WHITE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMargin(new Insets(5, 5, 5, 5));
        button.setFocusPainted(false);
        button.addActionListener(getActionListener(numberOfRows, numberOfCols));
        return button;
    }

    private ActionListener getActionListener(int numberOfRows, int numberOfCols) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableWindow tableWindow = new TableWindow(numberOfRows, numberOfCols);
                tableWindow.setVisible(true);
            }
        };
    }

    @Override
    protected void doUponExit() {
        System.exit(0);
    }
}
