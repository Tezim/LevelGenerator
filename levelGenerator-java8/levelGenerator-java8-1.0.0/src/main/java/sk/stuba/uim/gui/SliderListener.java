package sk.stuba.uim.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderListener implements ChangeListener {
    JLabel label;
    public SliderListener(){}
    public SliderListener(JLabel label){
        this.label = label;
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        label.setText( ((JSlider) (e.getSource())).getValue() + "" );
    }
}
