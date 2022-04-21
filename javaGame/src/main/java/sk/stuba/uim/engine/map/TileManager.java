package sk.stuba.uim.engine.map;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import sk.stuba.uim.engine.core.MainPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TileManager {
    private MainPanel panel;
    public Tile[] tiles;
    public String[][] tilemap;
    int heigth;
    int width;

    public TileManager(){}
    public TileManager(MainPanel panel){
        this.panel = panel;
        this.tiles = new Tile[7];
        readImages();
        readMap();
    }

    private void readImages(){
        for (int i = 0; i < 7; i++){
            this.tiles[i] = new Tile();
        }
        try{
            tiles[0].sprite = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tiles[0].collision = true;
            tiles[1].sprite = ImageIO.read(getClass().getResourceAsStream("/tiles/land.png"));
            tiles[2].sprite = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
            tiles[3].sprite = ImageIO.read(getClass().getResourceAsStream("/tiles/stone.png"));
            tiles[4].sprite = ImageIO.read(getClass().getResourceAsStream("/tiles/lava.png"));
            tiles[5].sprite = ImageIO.read(getClass().getResourceAsStream("/tiles/castle.png"));
            tiles[5].collision = true;
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private void readMap(){
        try {
            File map = new File(getClass().getResource("/maps/level1.txt").getPath());
            Scanner sc = new Scanner(map);
            int i = 0;
            while (sc.hasNextLine()) {
                if (i == 0){
                    String[] row = sc.nextLine().split("x");
                    width = Integer.parseInt(row[0] + 1);
                    heigth = Integer.parseInt(row[1] + 1);
                    this.tilemap = new String[heigth][width];
                } else {
                    String[] row = sc.nextLine().split("");
                    for(int j = 0; j < row.length - 1; j++){
                        this.tilemap[i - 1][j] = row[j];
                    }
                }
                i++;
            }
            sc.close();
        } catch (FileNotFoundException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D graphics2D){

        int worldX = 0;
        int worldY = 0;

        for(int i = worldY; i < 64;i++){
            for (int j = worldX ; j < 48; j++){
                int drawX = (i * 48) - (panel.getPlayer().getX() - 2 * panel.getPlayer().getScreenX());
                int drawY = (j * 48) - (panel.getPlayer().getY() - panel.getPlayer().getScreenY());
                    /*if ((i * 48) >= ( panel.getPlayer().getX() - panel.getPlayer().getScreenX()) &&
                            (i * 48) <= (panel.getPlayer().getX() + panel.getPlayer().getScreenX()) &&
                            (j * 48) >= (panel.getPlayer().getY() - panel.getPlayer().getScreenY()) &&
                            (j * 48) <= (panel.getPlayer().getY() + panel.getPlayer().getScreenY())) {
                        break;
                    }*/
                    if (tilemap[i][j] == null)
                        break;
                switch (tilemap[i][j]) {
                    case "W": {
                        graphics2D.drawImage(tiles[0].sprite,drawX,drawY,48,48,null);
                        break;
                    }
                    case "L": {
                        graphics2D.drawImage(tiles[1].sprite,drawX,drawY,48,48,null);
                        break;
                    }
                    case "S": {
                        graphics2D.drawImage(tiles[2].sprite,drawX,drawY,48,48,null);
                        break;
                    }
                    case "X": {
                        graphics2D.drawImage(tiles[3].sprite,drawX,drawY,48,48,null);
                        break;
                    }
                    case "#": {
                        graphics2D.drawImage(tiles[4].sprite,drawX,drawY,48,48,null);
                        break;
                    }
                    case "C": {
                        graphics2D.drawImage(tiles[5].sprite,drawX,drawY,48,48,null);
                        break;
                    }
                    default: break;

                }
                worldX++;
            }
            worldY++;
            worldX = 0;
        }
    }
    public Tile getTile(String name){
        switch (name){
            case "W": {
                return tiles[0];
            }
            case "L": {
                return tiles[1];
            }
            case "S": {
                return tiles[2];
            }
            case "X": {
                return tiles[3];
            }
            case "#": {
                return tiles[4];
            }
            case "C": {
                return tiles[5];
            }
            default: break;
        }
        return null;
    }

}
