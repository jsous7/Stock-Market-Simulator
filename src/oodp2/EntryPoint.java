package oodp2;

import oodp2.Services.DummyDataGenetator;
import oodp2.Services.Simulation;

/**
 *
 * @author Juliana Costa <juliana.oli.sousa@gmail.com>
 */
public class EntryPoint {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        
        int DummyDataAmount = 30;//<-----------Start Point----------|
        
        DummyDataGenetator.generate(DummyDataAmount, true);
        Simulation.init(DummyDataAmount);
        //TODO:Report with the results
//        Report.generate();
    }
}
