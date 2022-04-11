package sk.stuba.uim.automaton.objects;

import sk.stuba.uim.automaton.enums.Tiles;

public class State {
    private Tiles state = Tiles.DEFAULT;
    private Character character = ' ';
    // private ArrayList<Property> properties;

    public State(){}
    public State(Tiles type){
      defineState(type);
    }

    public void defineState(Tiles type){
        switch (type){
            case ALIVE :{
                state = Tiles.ALIVE;
                character = '1';
                break;
            }
            case DEAD:{
                state = Tiles.DEAD;
                character = '0';
                break;}
            case LAND:{
                state = Tiles.LAND;
                character = 'L';
                break;
            }
            case SAND:{
                state = Tiles.SAND;
                character = 'S';
                break;
            }
            case STONE:{
                state =Tiles.STONE;
                character = 'X';
                break;
            }
            case WATER:{
                state = Tiles.WATER;
                character = 'W';
                break;
            }
            case EMPTY:{
                state = Tiles.EMPTY;
                character = '.';
                break;
            }
            case LAVA: {
                state = Tiles.LAVA;
                character = '#';
                break;
            }
            // YOUR CODE HERE //
            /*case EXAMPLE:{
                state = Tiles.EXAMPLE;
                character = 'E';
                break;
            } */
            default: break;
        }
    }

    public Tiles getTileType() {
        return state;
    }
    public Character getCharacter() {
        return character;
    }

}
