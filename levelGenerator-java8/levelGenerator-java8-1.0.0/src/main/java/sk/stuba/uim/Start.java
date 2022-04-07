package sk.stuba.uim;

import sk.stuba.uim.automaton.AutomatonMain;
import sk.stuba.uim.automaton.exceptions.*;
import sk.stuba.uim.automaton.objects.*;
import sk.stuba.uim.gui.Window;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

//Set variables in MetaData class!
public class Start {

    public static void main(String[] args) throws InvalidArgument, RunException, IOException {

        //! without gui
        // Grid map = AutomatonMain.createMaps();
        // AutomatonMain.write(map);
        // map.print();

        JFrame frame = new JFrame("Map generator");
        Window window = new Window(frame);
        frame.setContentPane(window.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(620,550));
        frame.pack();
        frame.setVisible(true);

    }
}
