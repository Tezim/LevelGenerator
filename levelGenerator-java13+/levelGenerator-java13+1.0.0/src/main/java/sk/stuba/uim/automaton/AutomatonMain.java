package sk.stuba.uim.automaton;

import sk.stuba.uim.automaton.exceptions.*;
import sk.stuba.uim.automaton.objects.Grid;
import sk.stuba.uim.automaton.objects.MetaData;
import sk.stuba.uim.automaton.output.OutputHandler;
import sk.stuba.uim.automaton.twoD.Generate;
import sk.stuba.uim.automaton.twoD.TwoDimensionalAutomaton;
import java.io.IOException;

//Set variables in MetaData class!
public class AutomatonMain {

    public static Grid createMaps() throws InvalidArgument {
        //check variable setting
        MetaData.check();
        Generate generate = new Generate();

        //TwoDimensionalAutomaton twoD = new TwoDimensionalAutomaton(generate.generate3state());
          //for (int i = 0; i < 100; i++){        // generations
         TwoDimensionalAutomaton twoD = new TwoDimensionalAutomaton(generate.generateGrids());
         Grid result = twoD.perform();  // final configuration
          // OutputHandler.writeStatistics();
          //}
        return result;
    }

    public static int write(Grid result,boolean png) throws RunException {
        // output writing
        try{
            if (png)
                OutputHandler.writeImg(result);
            if(OutputHandler.writeTxt(result) < 0)
                return -1;
        }catch (IOException e){
            return -1;
        }
        return 0;
    }
}
