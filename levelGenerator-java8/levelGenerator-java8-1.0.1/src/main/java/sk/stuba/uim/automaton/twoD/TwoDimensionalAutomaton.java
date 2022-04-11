package sk.stuba.uim.automaton.twoD;

import sk.stuba.uim.automaton.enums.Neighborhood;
import sk.stuba.uim.automaton.enums.Tiles;
import sk.stuba.uim.automaton.exceptions.RunException;
import sk.stuba.uim.automaton.objects.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TwoDimensionalAutomaton {

    private ArrayList<Grid> init_layers;

    private Grid oldState;
    private Grid newState;
    //private Analysis analysis;

    public TwoDimensionalAutomaton(){}
    public TwoDimensionalAutomaton(ArrayList<Grid> layers){
        this.init_layers = layers;
        this.oldState = new Grid(MetaData.getMAX_HEIGHT(),MetaData.getMAX_WIDTH());
        this.newState = new Grid(oldState.getHeight(),oldState.getWidth());
    }
    public TwoDimensionalAutomaton(Grid base) {
        this.oldState = base;
        this.newState = base;
    }

    private void performMoore(){

        for(int y = 0; y < oldState.getHeight(); y++){
            for (int x = 0; x < oldState.getWidth(); x++){
                ArrayList<Cell> neighbours = oldState.getNeighboursMoore(x,y);          // list of cells
                Tiles type = changeState(neighbours, oldState.getCell(x,y));            // returns state based on rule
                //if (type != oldState.getCell(x,y).getState().getTileType())
                //    analysis.increase();
                newState.getCell(x,y).getState().defineState(type);
            }
        }
        oldState.copy(newState);

    }
    private void performNeumann(){
        for(int y = 0; y < oldState.getHeight(); y++){
            for (int x = 0; x < oldState.getWidth(); x++){
                ArrayList<Cell> neighbours = oldState.getNeighboursNeumann(x,y);        // list of cells
                Tiles type = changeState(neighbours, oldState.getCell(x,y));           // returns state based on rule
                newState.getCell(x,y).getState().defineState(type);
            }
        }
        oldState.copy(newState);
    }

    // returns state based on rule specified
    private Tiles changeState(ArrayList<Cell> neighborhood, Cell current){
        // current cell is not included in neighborhood list

        //return majorityDefault(neighborhood,current);
        return majorityModified(neighborhood,current);
        //return CGoL(neighborhood,current);
        //return lowDeath(neighborhood,current);
        //return mazentric(neighborhood,current);
        //return gems(neighborhood,current);

        //return Tiles.DEFAULT;
    }

////////////////////// RULE methods ///////////////////////////////////////////////////
    // described in README file
    private Tiles majorityDefault(ArrayList<Cell> neighborhood, Cell current){
        if (current.getState().getTileType() != getMajority(neighborhood))
            return getMajority(neighborhood);
        else
            return current.getState().getTileType();
    }
    private Tiles majorityModified(ArrayList<Cell> neighborhood, Cell current){
        if (current.getState().getTileType() != getMajority(neighborhood))
            return getMajority(neighborhood);
        else
            return chanceToSurvive(neighborhood,current);
    }

  // 2 STATE ONLY automata rules
  // defined on moore neighbourhood
  // state i  -> alive
  // state ii -> dead

    private Tiles CGoL(ArrayList<Cell> neighborhood, Cell current){
        // rulestring B3/S23
        int alive = countAlive(neighborhood);
        if (current.getState().getTileType() == MetaData.getState_I()){  // cell is alive or state i
            if (alive < 2 || alive > 3)
                return MetaData.getState_II();
            else
                return current.getState().getTileType();
        } else {                                                         // cell is dead or state ii
            if (alive == 3)
                return MetaData.getState_I();
            else
                return current.getState().getTileType();
        }
    }
    private Tiles lowDeath(ArrayList<Cell> neighborhood, Cell current){
        // rulestring B368/S238
        int alive = countAlive(neighborhood);
        if (current.getState().getTileType() == MetaData.getState_I()){  // cell is alive or state i
            if (alive < 2 || (alive > 3 && alive < 8))
                return MetaData.getState_II();
            else
                return current.getState().getTileType();
        } else {                                                         // cell is dead or state ii
            if (alive == 3 || alive == 6 || alive == 8)
                return MetaData.getState_I();
            else
                return current.getState().getTileType();
        }
    }
    private Tiles mazentric(ArrayList<Cell> neighborhood, Cell current){
        // rulestring B3/S12345
        int alive = countAlive(neighborhood);
        if (current.getState().getTileType() == MetaData.getState_I()){  // cell is alive or state i
            if (alive < 1 || alive > 5)
                return MetaData.getState_II();
            else
                return current.getState().getTileType();
        } else {                                                         // cell is dead or state ii
            if (alive == 3)
                return MetaData.getState_I();
            else
                return current.getState().getTileType();
        }
    }
    private Tiles gems(ArrayList<Cell> neighborhood, Cell current){
        // rulestring B3457/S4568
        int alive = countAlive(neighborhood);
        if (current.getState().getTileType() == MetaData.getState_I()){  // cell is alive or state i
            if (alive < 4)
                return MetaData.getState_II();
            else
                return current.getState().getTileType();
        } else {                                                         // cell is dead or state ii
            if (alive == 3 || alive == 4 || alive == 5 || alive == 7)
                return MetaData.getState_I();
            else
                return current.getState().getTileType();
        }
    }

///////////////////////////////////////////////////////////////////////////////////////
    private Tiles chanceToSurvive(ArrayList<Cell> cells, Cell current){
        double curr = 0;
        for(Cell c : cells){
            if (c.getState().getTileType() == current.getState().getTileType())
                curr++;
        }

        double probability = curr / cells.size();    // C(s) / C
        if(MetaData.getSeed().nextDouble() > probability)  // reversed logic
            return current.getState().getTileType();
        else
            return getMajority(cells);

    }
    private Tiles getMajority(ArrayList<Cell> cells){
        Map<Tiles, Integer> cache = makeNeighbourMap(cells);
        Tiles majority = Tiles.DEFAULT;
        int value = 0;
        for (Map.Entry<Tiles,Integer> entry : cache.entrySet()){
            if (entry.getValue() > value){
                value = entry.getValue();
                majority = entry.getKey();
            }
        }
        return majority;
    }
    private HashMap<Tiles,Integer> makeNeighbourMap(ArrayList<Cell> cells){
        HashMap<Tiles, Integer> cache = new HashMap<>();
        for (Cell c : cells){
            if (!cache.containsKey(c.getState().getTileType())){
                cache.put(c.getState().getTileType(),1);
            } else {
                Integer value = cache.get(c.getState().getTileType());
                value++;
                cache.replace(c.getState().getTileType(),value);          // rewrite old entry
            }
        }
        return cache;
    }
    private int countAlive (ArrayList<Cell> neighborhood){
        int counter = 0;
        for (Cell cell : neighborhood){
            if (cell.getState().getTileType() == MetaData.getState_I())
                counter++;
        }
        return counter;
    }

    public Grid mergeCall(int x_size, int y_size) {   // size of each segment
        // disrupt edges, for optimisation run this only on segment borders
        // size of parts determine middle of line, run this function on 5X
        // horizontal
        this.disrupt(0, y_size - 1,this.oldState.getWidth(),y_size + 1);
        this.disrupt(0, (y_size * 2) - 1,this.oldState.getWidth(),(y_size * 2) + 1);
        // vertical
        this.disrupt(x_size - 1,0,x_size + 1,this.oldState.getHeight());
        this.disrupt((x_size * 2) - 1, 0, (x_size * 2) + 1,this.oldState.getHeight());
        // NOTE there is no need to exchange grids since this happens only once -> newstate will contain disrupted borders oldstate holds initial input

        // run CA again on new state, to make it work copy it to oldstate
        oldState.copy(newState);
        for (int i = 0; i < 1; i++){
            performMoore();
        }
       return this.oldState;
    }
    private void disrupt(int x1, int y1, int x2 , int y2){
        for(int y = y1; y < y2; y++){
            for(int x = x1; x < x2; x++){
                // find all neighbours and choose new state randomly from ngbhd
                ArrayList<Cell> neighbours = oldState.getNeighboursMoore(x,y);
                Tiles rand_state = neighbours.get(MetaData.getSeed().nextInt(neighbours.size())).getState().getTileType();  // TODO random from neighbours
                this.newState.getCell(x,y).getState().defineState(rand_state);
            }
        }
    }
    // main called from Start
    public Grid perform() {
        //this.analysis = new Analysis(oldState.getWidth(),oldState.getHeight());

        ArrayList<Grid> result_layers = new ArrayList<>();
        // for each active layer in list perform automaton
        for (Grid grid : init_layers){
            this.oldState = grid;
            //this.oldState.print();       // init config

            if(MetaData.getNEIGHBORHOOD() == Neighborhood.MOORE){
                for (int i = 0; i < MetaData.getITR_COUNT(); i++){
                    performMoore();           // counter increases inside
                    ////////////////////////////analysis/////////////////////////////////////
                    //analysis.push(analysis.getChangeCounter());
                    //if (analysis.evaluate())
                    //    break;
                    //this.analysis.nullCounter();
                    ////////////////////////////////////////////////////////////////////////
                }
            } else if (MetaData.getNEIGHBORHOOD() == Neighborhood.NEUMANN){
                for (int i = 0; i < MetaData.getITR_COUNT(); i++){
                    performNeumann();
                }
            }
            result_layers.add(this.oldState);
            this.newState = new Grid(oldState.getHeight(),oldState.getWidth());
        }
        // at this point in list are saved final configurations of each layer
        if (result_layers.size() > 1){
            try{
                return Merge.defaultMerge(result_layers);
            } catch (RunException ignored){}
        }
        return result_layers.get(0);
    }
}
