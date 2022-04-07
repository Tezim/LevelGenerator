package sk.stuba.uim.automaton.output;

import sk.stuba.uim.automaton.objects.Grid;
import sk.stuba.uim.automaton.objects.MetaData;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ImageGenerator {
    public static BufferedImage createImage(Grid grid) throws IOException {
        // load resource spites
        StringBuilder path = new StringBuilder(System.getProperty("user.dir"));
        path.append('\\');
        path.append("Textures");
        path.append('\\');

        BufferedImage land  = ImageIO.read(new File(path.toString(),"land_tile.png"));
        BufferedImage sand  = ImageIO.read(new File(path.toString(),"sand_tile.png"));
        BufferedImage stone = ImageIO.read(new File(path.toString(),"stone_tile.png"));
        BufferedImage water = ImageIO.read(new File(path.toString(),"water_tile.png"));
        BufferedImage alive = ImageIO.read(new File(path.toString(),"alive_tile.png"));
        BufferedImage dead  = ImageIO.read(new File(path.toString(),"dead_tile.png"));
        BufferedImage lava  = ImageIO.read(new File(path.toString(),"lava_tile.png"));


        // YOUR IMAGE HERE // + switch on line 44
        // BufferedImage example  = ImageIO.read(new File(path.toString(),"example.png"));

        // basic size of hd picture
        int width = 1280;
        int height = 720;

        // TESTING SQUARE IMAGE //
        //int width = 720;
        //int height = 720;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);


        // create layout based on grid
        // calculate size of spite based on chosen h and w
        int scale_x = width / MetaData.getMAX_WIDTH() + 1;
        int scale_y = height / MetaData.getMAX_HEIGHT() + 1;

        Graphics2D g = (Graphics2D) image.getGraphics();

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
        g.setColor(new Color(0,0,0,0));
        g.fillRect(0,0,width,height);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

        for (int i = 0 ; i < grid.getWidth(); i++){
            for (int j = 0; j < grid.getHeight(); j++){
                switch(grid.getGrid()[j][i].getState().getTileType()){
                    case WATER -> g.drawImage(water,i * scale_x,j * scale_y, scale_x, scale_y,null);
                    case LAND  -> g.drawImage(land,i * scale_x,j * scale_y, scale_x, scale_y,null);
                    case SAND  -> g.drawImage(sand,i * scale_x,j * scale_y, scale_x, scale_y,null);
                    case STONE -> g.drawImage(stone,i * scale_x,j * scale_y, scale_x, scale_y,null);
                    case ALIVE -> g.drawImage(alive,i * scale_x,j * scale_y, scale_x, scale_y,null);
                    case DEAD  -> g.drawImage(dead,i * scale_x,j * scale_y, scale_x, scale_y,null);
                    case LAVA  -> g.drawImage(lava,i * scale_x,j * scale_y, scale_x, scale_y,null);
                    case EMPTY -> { }
                    case DEFAULT -> { }
                    // case EXAMPLE -> g.drawImage(example,i * scale_x,j * scale_y, scale_x, scale_y,null);
                }
            }
        }
        // return final image that is written to file
        return image;
    }
}
