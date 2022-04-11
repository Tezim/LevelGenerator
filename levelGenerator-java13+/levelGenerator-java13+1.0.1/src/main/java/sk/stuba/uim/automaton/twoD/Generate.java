package sk.stuba.uim.automaton.twoD;

import sk.stuba.uim.automaton.enums.Tiles;
import sk.stuba.uim.automaton.objects.Grid;
import sk.stuba.uim.automaton.objects.MetaData;

import java.util.ArrayList;

public class Generate {

    public Generate(){}
    public ArrayList<Grid> generateGrids(){
        ArrayList<Grid> init_layers = new ArrayList<>();
        if (MetaData.getState_I() != Tiles.DEFAULT || MetaData.getState_II() != Tiles.DEFAULT){    // layer 01 active, should not have empty state
            init_layers.add(new Grid(MetaData.getSeed(),MetaData.getMAX_HEIGHT(),MetaData.getMAX_WIDTH(),
                                     MetaData.getState_I(),MetaData.getState_II(),MetaData.getD_L01()));
        }
        if (MetaData.getState_III() != Tiles.DEFAULT || MetaData.getState_IV() != Tiles.DEFAULT){  // layer 02 active
            init_layers.add(new Grid(MetaData.getSeed(),MetaData.getMAX_HEIGHT(),MetaData.getMAX_WIDTH(),
                                     MetaData.getState_III(),MetaData.getState_IV(),MetaData.getD_L02()));
        }
        if (MetaData.getState_V() != Tiles.DEFAULT || MetaData.getState_VI() != Tiles.DEFAULT){  // layer 03 active
            init_layers.add(new Grid(MetaData.getSeed(),MetaData.getMAX_HEIGHT(),MetaData.getMAX_WIDTH(),
                                     MetaData.getState_V(),MetaData.getState_VI(),MetaData.getD_L03()));
        }
        if (MetaData.getState_VII() != Tiles.DEFAULT || MetaData.getState_VIII() != Tiles.DEFAULT){  // layer 04 active
            init_layers.add(new Grid(MetaData.getSeed(),MetaData.getMAX_HEIGHT(),MetaData.getMAX_WIDTH(),
                                     MetaData.getState_VII(),MetaData.getState_VIII(),MetaData.getD_L04()));
        }
        return init_layers;
    }
    public ArrayList<Grid> generate3state(){
        ArrayList<Grid> ret = new ArrayList<>();
        ret.add(new Grid(MetaData.getSeed(),MetaData.getMAX_HEIGHT(),MetaData.getMAX_WIDTH(),
                MetaData.getState_I(),MetaData.getState_II(),MetaData.getState_III(),
                MetaData.getD_L01(),MetaData.getD_L02()));
        return ret;
    }
}
