/*
 *  (c) 2017 Michael A. Beck, Sebastian Henningsen
 *  		disco | Distributed Computer Systems Lab
 *  		University of Kaiserslautern, Germany
 *  All Rights Reserved.
 *
 * This software is work in progress and is released in the hope that it will
 * be useful to the scientific community. It is provided "as is" without
 * express or implied warranty, including but not limited to the correctness
 * of the code or its suitability for any particular purpose.
 *
 * This software is provided under the MIT License, however, we would 
 * appreciate it if you contacted the respective authors prior to commercial use.
 *
 * If you find our software useful, we would appreciate if you mentioned it
 * in any publication arising from the use of this software or acknowledge
 * our work otherwise. We would also like to hear of any fixes or useful
 */

package org.networkcalculus.snc.commands;

import org.networkcalculus.snc.SNC;

/**
 * Convolve two adjacent vertices ({@link Vertex}) in a given target network.
 * 
 * @author Sebastian Henningsen
 */
public class ConvolveVerticesCommand implements Command {
	private final int vertex1ID;
	private final int vertex2ID;
	private final int flowID;
	private final int networkID;
	private final SNC snc;
    
    /**
     * Creates a new command to convolve two vertices, the action is relayed to the
     * corresponding {@link Network}
     * 
     * @param vertex1ID The ID of the first vertex
     * @param vertex2ID The ID of the second vertex
     * @param flowID The ID of the flow of interest
     * @param networkID The network ID the vertices belong to
     * @param snc The overall controller
     */
    public ConvolveVerticesCommand(int vertex1ID, int vertex2ID, int flowID, int networkID, SNC snc) {
        this.vertex1ID = vertex1ID;
        this.vertex2ID = vertex2ID;
        this.flowID = flowID;
        this.networkID = networkID;
        this.snc = snc;
    }
    
    @Override
    public void execute() {
        snc.getCurrentNetwork().convolve(vertex1ID, vertex2ID, flowID);
    }

    @Override
    public void undo() {
        // TODO
        System.out.println("Undo Action for convolution not implemented yet.");
    }
}
