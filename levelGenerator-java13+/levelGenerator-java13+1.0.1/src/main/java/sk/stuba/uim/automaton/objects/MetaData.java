package sk.stuba.uim.automaton.objects;

import sk.stuba.uim.automaton.enums.Neighborhood;
import sk.stuba.uim.automaton.enums.Tiles;
import sk.stuba.uim.automaton.exceptions.InvalidArgument;

import java.util.Random;

// description is in README file
public class MetaData {
//////////////////// SETTINGS ///////////////////////////

    private static  Integer ITERATION_COUNT   = 5;        // MIN = 0
    private static  Integer MAX_HEIGHT        = 500;        // MIN = 10
    private static  Integer MAX_WIDTH         = 500;       // MIN = 10   max width of IntelliJ run console -> 145
    private static  Neighborhood NEIGHBORHOOD = Neighborhood.MOORE;
    private static  String FILE_NAME          = "level1";  // output file name without .example

/////////////// TWO STATE LAYERED ///////////////////////////////////
    // (comment lines 25 - 41 if you want to use "testing parts below")
    // layer with default state is ignored
    // NOTE: layer 01 should not have EMPTY state

    // layer 01
    private static  Tiles state_I   = Tiles.ALIVE;          // dominant
    private static  Tiles state_II  = Tiles.EMPTY;
    // layer 02
    private static  Tiles state_III = Tiles.DEFAULT;         // dominant
    private static  Tiles state_IV  = Tiles.DEFAULT;
    // layer 03
    private static  Tiles state_V   = Tiles.DEFAULT;       // dominant
    private static  Tiles state_VI  = Tiles.DEFAULT;
    // layer 04
    private static  Tiles state_VII  = Tiles.DEFAULT;      // dominant
    private static  Tiles state_VIII = Tiles.DEFAULT;

    // choose density for dominant state of each layer , leave zero for unused layer
    private static  Integer d_L01 = 44;
    private static  Integer d_L02 = 0;
    private static  Integer d_L03 = 0;
    private static  Integer d_L04 = 0;

    private static final Random seed = new Random(System.currentTimeMillis());

/////////////////////////////////////////////////////////
    // THREE STATE CA // FOR TESTING!!! DO NOT USE

    //private static final Tiles state_I   = Tiles.WATER;
    //private static final Tiles state_II  = Tiles.LAND;
    //private static final Tiles state_III = Tiles.SAND;
    //private static final Integer d_L01 = 50;
    //private static final Integer d_L02 = 40;

/////////////////////////////////////////////////////////
    // FOUR STATE CA // FOR TESTING!!! DO NOT USE

    //private static final Tiles state_I   = Tiles.WATER;
    //private static final Tiles state_II  = Tiles.LAND;
    //private static final Tiles state_III = Tiles.SAND;
    //private static final Tiles state_IV  = Tiles.EMPTY;
    //private static final Integer d_L01 = 50;
    //private static final Integer d_L02 = 40;
    //private static final Integer d_L03 = 0;

/////////////////////////////////////////////////////////

    public MetaData(){}

// access variables only through static methods
    public static Integer getITR_COUNT(){ return ITERATION_COUNT;}
    public static Integer getMAX_HEIGHT() {
        return MAX_HEIGHT;
    }
    public static Integer getMAX_WIDTH() {
        return MAX_WIDTH;
    }
    public static Neighborhood getNEIGHBORHOOD() {
        return NEIGHBORHOOD;
    }
    public static Random getSeed() {
        return seed;
    }
    public static String getFileName() { return FILE_NAME; }

    public static Tiles getState_I() {
        return state_I;
    }
    public static Tiles getState_II() {
        return state_II;
    }
    public static Tiles getState_III() {
        return state_III;
    }
    public static Tiles getState_IV() { return state_IV; }
    public static Tiles getState_V() { return state_V; }
    public static Tiles getState_VI() { return state_VI; }
    public static Tiles getState_VII() { return state_VII; }
    public static Tiles getState_VIII() { return state_VIII; }

    public static Integer getD_L01() {
        return d_L01;
    }
    public static Integer getD_L02() {
        return d_L02;
    }
    public static Integer getD_L03() {
        return d_L03;
    }
    public static Integer getD_L04() { return d_L04; }

    public static void setIterationCount(Integer iterationCount) {
        ITERATION_COUNT = iterationCount;
    }
    public static void setMaxHeight(Integer maxHeight) {
        MAX_HEIGHT = maxHeight;
    }
    public static void setMaxWidth(Integer maxWidth) {
        MAX_WIDTH = maxWidth;
    }
    public static void setFileName(String fileName) {
        FILE_NAME = fileName;
    }

    public static void setState_I(Tiles state_I) {
        MetaData.state_I = state_I;
    }
    public static void setState_II(Tiles state_II) {
        MetaData.state_II = state_II;
    }
    public static void setState_III(Tiles state_III) {
        MetaData.state_III = state_III;
    }
    public static void setState_IV(Tiles state_IV) {
        MetaData.state_IV = state_IV;
    }
    public static void setState_V(Tiles state_V) {
        MetaData.state_V = state_V;
    }
    public static void setState_VI(Tiles state_VI) {
        MetaData.state_VI = state_VI;
    }
    public static void setState_VII(Tiles state_VII) {
        MetaData.state_VII = state_VII;
    }
    public static void setState_VIII(Tiles state_VIII) {
        MetaData.state_VIII = state_VIII;
    }

    public static void setD_L01(Integer d_L01) {
        MetaData.d_L01 = d_L01;
    }
    public static void setD_L02(Integer d_L02) {
        MetaData.d_L02 = d_L02;
    }
    public static void setD_L03(Integer d_L03) {
        MetaData.d_L03 = d_L03;
    }
    public static void setD_L04(Integer d_L04) {
        MetaData.d_L04 = d_L04;
    }

    public static void check() throws InvalidArgument {
        if(NEIGHBORHOOD == Neighborhood.DEFAULT){
            throw new InvalidArgument("Neighborhood");
        }
        if(MAX_HEIGHT < 10 || MAX_WIDTH < 10){
            throw new InvalidArgument("Dimensions");
        }
        if(ITERATION_COUNT < 0){
            throw new InvalidArgument("Iteration");
        }
        if(d_L01 < 0 || d_L02 < 0 || d_L03 < 0 || d_L04 < 0){  //|| (d_one + d_two + d_three) > 100
            throw new InvalidArgument("Density");
        }
       /* if(getState_I() == Tiles.EMPTY || getState_II() == Tiles.EMPTY){
            throw new InvalidArgument("Layer 01");
        }*/
    }
}
