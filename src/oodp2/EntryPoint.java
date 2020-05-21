package oodp2;

import oodp2.Services.DummyDataGenetator;

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
        DummyDataGenetator.generate();
    }
}
