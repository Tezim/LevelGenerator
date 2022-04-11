package sk.stuba.uim.automaton.objects;

import sk.stuba.uim.automaton.enums.Tiles;
import java.util.Random;

public class Cell {

    private Integer coordX;
    private Integer coordY;
    private State state;

    public Cell(){}
    public Cell(Integer coordX, Integer coordY){
        this.coordX = coordX;
        this.coordY = coordY;
        this.state = new State(Tiles.DEFAULT);
    }
    public Cell(Integer coordX, Integer coordY, Random seed,Tiles state_i, Tiles state_ii, Integer density) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.randState(seed,state_i,state_ii,density);
    }
    // testing //
    public Cell(Integer coordX, Integer coordY,State state){
    this.coordX = coordX;
    this.coordY = coordY;
    this.state = state;
}
    ////
    public Integer getCoordX() {
        return coordX;
    }
    public Integer getCoordY() {
        return coordY;
    }
    public State getState() {
        return state;
    }

    public void randState(Random seed,Tiles state_i, Tiles state_ii, Integer density){
        // random initial seed from chosen tiles
        int number = seed.nextInt(100);
        if (number < density){
            this.state = new State(state_i);
        } else {
            this.state = new State(state_ii);
        }
    }

}
