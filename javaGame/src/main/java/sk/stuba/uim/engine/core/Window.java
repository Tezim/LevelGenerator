package sk.stuba.uim.engine.core;

import sk.stuba.uim.engine.core.MainPanel;

import javax.swing.*;

public class Window {
    private JFrame frame;
    private MainPanel panel;

    public Window(){
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setTitle("Knight adventure demo");
        this.frame.setResizable(false);
        this.panel = new MainPanel();
        this.frame.add(panel);
        this.frame.pack();
        this.panel.start();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }
}
