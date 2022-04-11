package sk.stuba.uim.automaton.twoD;

import sk.stuba.uim.automaton.enums.Tiles;
import sk.stuba.uim.automaton.exceptions.RunException;
import sk.stuba.uim.automaton.objects.Grid;
import sk.stuba.uim.automaton.objects.MetaData;

import java.util.ArrayList;

public class Merge {

    private static Grid splitMerge(Grid base, Grid top) throws RunException {
        // this should not happen, but to make sure ...
        if (base.getWidth() != top.getWidth())
            throw new RunException("Width of layers is not equal");
        if (base.getHeight() != top.getHeight())
            throw new RunException("Height of layers is not equal");

        // divide in segments ... always 9
        int size_x = (int) Math.floor(top.getWidth() / 3.0);
        int size_y = (int) Math.floor(top.getHeight() / 3.0);
        int x_err = top.getWidth() - (size_x * 3);
        int y_err = top.getHeight() - (size_y * 3);
        // choose 3 random parts to exchange
        ArrayList<Integer> parts = new ArrayList<>();
        while(parts.size() < 3){
            int rnd = MetaData.getSeed().nextInt(9);
            if (!parts.contains(rnd)){
                parts.add(rnd);
            }
        }

        for (Integer segment : parts){
            int x_start = size_x * (segment % 3);
            int y_start = size_y * ((int) Math.floor(segment / 3.0));
            // handle inconsistency caused by floor division
            int x_add = 0;
            int y_add = 0;
            if (segment % 3 == 2){
                x_add = x_err;
            }
            if ((int) Math.floor(segment / 3.0) == 2){
                y_add = y_err;
            }
            // merge

            for (int j = 0; j < size_y + y_add; j++){
                for (int i = 0; i < size_x + x_add; i++){
                    base.getCell(x_start + i,y_start + j).getState().defineState(top.getCell(x_start + i,y_start + j).getState().getTileType());
                }
            }

        }
        base.print();
        // get rid of sharp edges
        // temporary instance of 2D automaton
        // this does not break the edges
        TwoDimensionalAutomaton tmp2D = new TwoDimensionalAutomaton(base);
        for (int i = 0; i < 10; i++){
            base = tmp2D.mergeCall(size_x,size_y);
        }
        return base;
    }
    private static Grid overlayMerge(Grid base, Grid top) throws RunException {

        // this should not happen, but to make sure ...
        if (base.getWidth() != top.getWidth())
            throw new RunException("Width of layers is not equal");
        if (base.getHeight() != top.getHeight())
            throw new RunException("Height of layers is not equal");

        // overlay base with top layer, empty state is considered as an empty pixel
        for (int Y = 0; Y < top.getHeight(); Y++){
            for (int X = 0; X < top.getWidth(); X ++){
                if (top.getCell(X,Y).getState().getTileType() != Tiles.EMPTY){
                    base.getCell(X,Y).getState().defineState(top.getCell(X,Y).getState().getTileType());
                }
            }
        }
        return base;
    }

    public static Grid defaultMerge(ArrayList<Grid> layers) throws RunException {
        // layer has empty as one of its states -> apply overlay
        // otherwise apply split merge
        Grid result = layers.get(0);  // layer 01 is default
        result.print();
        int nlayer = 1;
        while(nlayer < layers.size()){
            layers.get(nlayer).print();
            if (!layers.get(nlayer).isPNG()){
                result = splitMerge(result,layers.get(nlayer));
            } else {
                result = overlayMerge(result,layers.get(nlayer));
            }
            nlayer++;
        }
        return result;
    }


///////////////////////////////////////////////////////////
    public static Grid customMerge(ArrayList<Grid> layers){
        return new Grid();
    }
//////////////////////////////////////////////////////////

}
