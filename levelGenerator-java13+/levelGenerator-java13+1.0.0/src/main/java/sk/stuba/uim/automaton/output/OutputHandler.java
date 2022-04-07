package sk.stuba.uim.automaton.output;

import sk.stuba.uim.automaton.Analysis;
import sk.stuba.uim.automaton.objects.Grid;
import sk.stuba.uim.automaton.objects.MetaData;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutputHandler {

    public static int check(){
        StringBuilder path = new StringBuilder(System.getProperty("user.dir"));
        path.append('\\');
        path.append(MetaData.getFileName());
        path.append(".txt");
        File f = new File(path.toString().trim());
        if (f.exists()){
            return -1;
        }
        return 0;
    }

    public static int writeTxt(Grid txtgrid){
        StringBuilder path = new StringBuilder(System.getProperty("user.dir"));
        path.append('\\');
        path.append(MetaData.getFileName());
        path.append(".txt");

        try{
            File f = new File(path.toString().trim());
            f.createNewFile();
            FileOutputStream fs = new FileOutputStream(f);
            fs.write(MetaData.getMAX_WIDTH().toString().getBytes());
            fs.write('x');
            fs.write(MetaData.getMAX_HEIGHT().toString().getBytes());
            fs.write('\n');
            for(int y = 0; y < MetaData.getMAX_HEIGHT(); y++){
                for (int x = 0; x < MetaData.getMAX_WIDTH(); x++){
                    fs.write(txtgrid.getGrid()[y][x].getState().getCharacter());
                    if (txtgrid.getGrid()[y][x].getCoordX() == MetaData.getMAX_WIDTH() - 1) {
                        fs.write('\n');
                    }
                }
            }
        }catch (IOException ignored){ return -1; }
        return 0;
    }
    public static int writeImg(Grid imggrid) throws IOException {

        StringBuilder path_out = new StringBuilder(System.getProperty("user.dir"));
        path_out.append('\\');

        BufferedImage image = ImageGenerator.createImage(imggrid);
        String name = MetaData.getFileName() + ".png";
        ImageIO.write(image,"PNG",new File(path_out.toString(),name));
        return 0;
    }

    public static void writeStatistics(){
        StringBuilder path_out = new StringBuilder(System.getProperty("user.dir"));
        path_out.append("\\statistics.txt");
        File f = new File(path_out.toString().trim());
        try {
            FileOutputStream fs = new FileOutputStream(f,true);
            for (Integer i : Analysis.getStore()){
                fs.write(i.toString().getBytes());
                fs.write(", ".getBytes());
            }
            fs.write('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
