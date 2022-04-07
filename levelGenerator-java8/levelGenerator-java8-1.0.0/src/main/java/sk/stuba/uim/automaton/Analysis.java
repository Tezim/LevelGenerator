package sk.stuba.uim.automaton;

import java.util.ArrayList;

public class Analysis {

    private int limit;
    private final int percentage = 1;

    private int changeCounter = 0;
    private static ArrayList<Integer >store = new ArrayList<>();

    public Analysis(){}
    public Analysis(int width, int height) {
        int cellCount = width * height;
        this.limit = (cellCount / 100) * percentage;
        store = new ArrayList<>();
    }

    public int getChangeCounter() {
        return changeCounter;
    }

    public void nullCounter(){
        this.changeCounter = 0;
    }

    public void increase(){
        this.changeCounter += 1;
    }
    public boolean evaluate(){
        return this.changeCounter < this.limit;
    }

    public void push(int i) {
        store.add(i);
    }
    public static ArrayList<Integer> getStore(){
        return store;
    }
}
