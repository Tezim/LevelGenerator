package sk.stuba.uim.automaton.exceptions;

public class InvalidArgument extends Exception{
    public InvalidArgument(String message){
        super("Invalid Arguments chosen in MetaData class: " + message);
    }
}
