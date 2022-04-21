package sk.stuba.uim.engine.core;

import sk.stuba.uim.engine.core.KeyboardInput;
import sk.stuba.uim.engine.map.TileManager;
import sk.stuba.uim.engine.objects.CollisionHandler;
import sk.stuba.uim.engine.objects.Player;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel implements Runnable{
    private final int tileSize = 48;
    private final int screenWidth = 16 * tileSize;
    private final int screeHeight = 12 * tileSize;
    private final double FPS = 60d;

    private final int worldSizeX = tileSize * 64;
    private final int worldSizeY = tileSize * 48;

    private Thread gameThread;
    private KeyboardInput inputListener = new KeyboardInput();
    private Player player = new Player(this,inputListener,tileSize);
    private TileManager tileManager = new TileManager(this);
    private CollisionHandler handler = new CollisionHandler(this);

    public MainPanel(){
        this.setPreferredSize(new Dimension(screenWidth,screeHeight));
        this.setDoubleBuffered(true);
        this.setBackground(new Color(250,179,61));
        this.addKeyListener(this.inputListener);
        this.setFocusable(true);
    }

    public void start(){
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    // game loop
    @Override
    public void run() {
        double interval = 1000000000 / FPS;
        long lastTime = System.nanoTime();
        long currentTime;
        double deltaTime = 0;
        while (gameThread != null){
            currentTime = System.nanoTime();
            deltaTime += (currentTime - lastTime) / interval;
            lastTime = currentTime;
            if(deltaTime >= 1){
                player.update();
                this.repaint();
                deltaTime--;
            }
        }
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        this.tileManager.draw(g2);
        this.player.draw(g2);
        g2.dispose();
    }

    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreeHeight() {
        return screeHeight;
    }
    public Player getPlayer() {
        return player;
    }
    public int getWorldSizeX() {
        return worldSizeX;
    }
    public int getWorldSizeY() {
        return worldSizeY;
    }
    public CollisionHandler getHandler() {
        return handler;
    }

    public TileManager getTileManager() {
        return tileManager;
    }
}
