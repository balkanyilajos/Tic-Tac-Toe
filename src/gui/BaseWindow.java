package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public abstract class BaseWindow extends JFrame {
    protected static int BASIC_TEXT_SIZE = 20;

    public BaseWindow(int width, int height) {
        setTitle("Tic-Tac-Toe");
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        URL url = BaseWindow.class.getResource("picture.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitWindow();
            }
        });
    }

    private void exitWindow() {
        int n = JOptionPane.showConfirmDialog(this, "Are you sure you want to close the window?",
                "Closing tab", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            this.doUponExit();
        }
    }

    protected void doUponExit() {
        this.dispose();
    }

    protected JLabel getJLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Source Sans Pro", Font.PLAIN, BASIC_TEXT_SIZE));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        return label;
    }

}
