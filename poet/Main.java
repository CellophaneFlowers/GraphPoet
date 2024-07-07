/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import graph.ConcreteEdgesGraph;

import java.io.File;
import java.io.IOException;

/**
 * Example program using GraphPoet.
 * 
 * <p>PS2 instructions: you are free to change this example class.
 */
public class Main {
    
    /**
     * Generate example poetry.
     * 
     * @param args unused
     * @throws IOException if a poet corpus file cannot be found or read
     */
    public static void main(String[] args) throws IOException {
        final String input = "test the system.";
        final String text="This is a test of the Mugar Omni Theater sound system.";
        final GraphPoet nimoy = new GraphPoet(new File("poet/mugar-omni-theater.txt"),input);

    }
    
}
