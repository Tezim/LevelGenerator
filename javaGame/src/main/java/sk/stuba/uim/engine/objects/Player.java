package sk.stuba.uim.engine.objects;

import sk.stuba.uim.engine.core.KeyboardInput;
import sk.stuba.uim.engine.core.MainPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Object {
    private MainPanel panel;
    private KeyboardInput input;
    private int tileSize;

    public int screenX;
    public int screenY;

    public Player(){}
    public Player(MainPanel panel, KeyboardInput input, int size){
        this.input = input;
        this.panel = panel;
        this.tileSize = size;
        diretion = 0;
        x = tileSize * 32;
        y = tileSize * 24;
        screenX = panel.getScreenWidth()/2 - (tileSize/2);
        screenY = panel.getScreeHeight()/2 - (tileSize/2);
        //x = screenX;
        //y = (screenY) - (3 * tileSize);
        super.screenX = this.screenX;
        super.screenY = this.screenY;
        loadImages();
        collisinArea = new Rectangle(8,16,32,32);
    }
    private void loadImages(){
        try{
            up = ImageIO.read(getClass().getResourceAsStream("/res/up.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/res/down.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/res/left.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/res/right.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2D){
        BufferedImage image = null;
        switch (diretion){
            case 0:{ image = down; break;}
            case 1:{ image = up; break;}
            case 2:{ image = left; break;}
            case 3:{ image = right; break;}
            default: break;
        }
        graphics2D.drawImage(image,screenX,screenY,tileSize,tileSize,null);
    }
    public void update(){
        collision = false;
        try{
            panel.getHandler().check(this);
        } catch (IndexOutOfBoundsException e){
            collision = true;
        }
        if (input.isUp()){
            diretion = 1;
        } else if (input.isDown()){
            diretion = 0;
        } else if (input.isLeft()){
            diretion = 2;
        } else if (input.isRight()){
            diretion = 3;
        }

        if (!collision){
            if (input.isUp()){
                y -= move_px;
            } else if (input.isDown()){
                y += move_px;
            } else if (input.isLeft()){
                x -= move_px;
            } else if (input.isRight()){
                x += move_px;
            }
        }
        if(input.isBuild()) {
            build = true;
            this.panel.getHandler().check(this);
            build = false;
            input.setBuild(false);
        }

    }

    public int getX(){
        return super.x;
    }
    public int getY(){
        return super.y;
    }
    public int getMove(){
        return super.move_px;
    }
    public void setX(int x){
        super.x = x;
    }
    public void setY(int y){
        super.y = y;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }
}
