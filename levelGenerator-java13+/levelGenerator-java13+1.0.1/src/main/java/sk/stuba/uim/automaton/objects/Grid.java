package sk.stuba.uim.automaton.objects;

import sk.stuba.uim.automaton.enums.Tiles;
import java.util.ArrayList;
import java.util.Random;

public class Grid {

    private Cell [][] grid;
    private int height;
    private int width;
    private boolean PNG = false;

    public Grid(){}
    public Grid(int height, int width){
        grid = new Cell[height][width];
        for(int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                grid[y][x] = new Cell(x,y);
            }
        }
        this.height = height;
        this.width = width;
        this.PNG = false;
    }
    // two state layered
    public Grid(Random seed, int height, int width, Tiles state_i, Tiles state_ii, Integer density) {

        if (state_i == Tiles.EMPTY || state_ii == Tiles.EMPTY)
            this.PNG = true;

        this.grid = new Cell[height][width];
        for(int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                grid[y][x] = new Cell(x,y,seed,state_i,state_ii,density);
            }
        }
        this.height = height;
        this.width = width;
    }
    // TESTING three state random init config //
    public Grid(Random seed, Integer max_height, Integer max_width, Tiles state_i, Tiles state_ii, Tiles state_iii, Integer d_l01, Integer d_l02) {
        this.grid = new Cell[max_height][max_width];

        for(int y = 0; y < max_height; y++){
            for (int x = 0; x < max_width; x++){
                int prob = seed.nextInt(100);
                if (prob <= d_l01){
                    grid[y][x] = new Cell(x,y,new State(state_i));
                } else if (prob > d_l01 && prob <= d_l01 + d_l02){
                    grid[y][x] = new Cell(x,y,new State(state_ii));
                } else {
                    grid[y][x] = new Cell(x,y,new State(state_iii));
                }
            }
        }
        this.height = max_height;
        this.width = max_width;
    }
    // TESTING four state random init config //
    public Grid(Random seed, Integer max_height, Integer max_width, Tiles state_i, Tiles state_ii, Tiles state_iii, Tiles state_iv, Integer d_l01, Integer d_l02, Integer d_l03) {
        this.grid = new Cell[max_height][max_width];

        for(int y = 0; y < max_height; y++){
            for (int x = 0; x < max_width; x++){
                double prob = seed.nextDouble();
                if (prob <= d_l01){
                    grid[y][x] = new Cell(x,y,new State(state_i));
                } else if (prob > d_l01 && prob <= d_l01 + d_l02){
                    grid[y][x] = new Cell(x,y,new State(state_ii));
                } else if (prob > d_l01 + d_l02 && prob <= d_l01 + d_l02 + d_l03){
                    grid[y][x] = new Cell(x,y,new State(state_iii));
                } else {
                    grid[y][x] = new Cell(x,y,new State(state_iv));
                }
            }
        }
        this.height = max_height;
        this.width = max_width;
    }
    //////////////////////////////////////////////////////////

    // return list of neighbors defined by Moore neighborhood
    public ArrayList<Cell> getNeighboursMoore(int x, int y ){
        ArrayList<Cell> ngbhd = new ArrayList<>();
        for (int row = -1 ; row < 2 ; row++) {
            for (int column = -1; column < 2; column++) {
                if (!(row == y && column == x)) {          // current is ignored
                    ngbhd.add(grid[(y + row + height) % height][(x + column + width) % width]);  // torus condition
                }
            }
        }
        return ngbhd;
    }
    // return list of neighbors defined by Neumann neighborhood
    public ArrayList<Cell> getNeighboursNeumann(int x, int y){
        ArrayList<Cell> ngbhd = new ArrayList<>();
        for (int i = -1 ; i < 2; i++){
            if (i != 0){      // current cell ignored
                ngbhd.add(grid[y][(x + i + this.width) % this.width]);       // right, left
                ngbhd.add(grid[(y + i + this.height) % this.height][x]);     // top, bottom
            }
        }
        return ngbhd;
    }

    public Cell getCell(int x , int y ){
        return grid[y][x];
    }
    public int getHeight() {
        return this.height;
    }
    public int getWidth() {
        return this.width;
    }
    public Cell[][] getGrid() {
        return grid;
    }
    public boolean isPNG() { return PNG; }

    public void copy(Grid newGrid){         // deep copy?
        // pomaleeeeeee
        for(int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                grid[y][x] = newGrid.getGrid()[y][x];
            }
        }
    }
    public void print() {
        for(int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                if (grid[y][x].getState().getCharacter() == '0')
                    System.out.print("0");
                else
                    System.out.print(grid[y][x].getState().getCharacter());
                if (grid[y][x].getCoordX() == width - 1) {
                    System.out.println(" ");
                }
            }
        }
        System.out.println("__________________________________________________________");
    }
}
