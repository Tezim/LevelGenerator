package sk.stuba.uim.engine.objects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Object {
    protected int x = 100;
    protected int y = 100;
    protected int move_px = 3;
    protected BufferedImage up;
    protected BufferedImage down;
    protected BufferedImage left;
    protected BufferedImage right;
    protected int diretion = 0;  // 0-down 1-up 2-left 3-right
    protected Rectangle collisinArea;
    protected boolean collision;
    protected int screenX;
    protected int screenY;
    protected boolean build;
}
