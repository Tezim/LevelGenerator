package sk.stuba.uim.engine.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean build;

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_W : {this.up = false; break;}
            case KeyEvent.VK_A : {this.left = false; break;}
            case KeyEvent.VK_S : {this.down = false; break;}
            case KeyEvent.VK_D : {this.right = false; break;}
            case KeyEvent.VK_X : {this.build = true; break;}
            default: break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_W : {this.up = true; break;}
            case KeyEvent.VK_A : {this.left = true; break;}
            case KeyEvent.VK_S : {this.down = true; break;}
            case KeyEvent.VK_D : {this.right = true; break;}
            default: break;
        }
    }

    public boolean isUp() {
        return up;
    }
    public boolean isDown() {
        return down;
    }
    public boolean isLeft() {
        return left;
    }
    public boolean isRight() {
        return right;
    }
    public boolean isBuild() {
        return build;
    }
    public void setBuild(boolean build) {
        this.build = build;
    }

}
