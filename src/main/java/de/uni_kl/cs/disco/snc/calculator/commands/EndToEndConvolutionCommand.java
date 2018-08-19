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

package de.uni_kl.cs.disco.snc.calculator.commands;

import de.uni_kl.cs.disco.snc.calculator.SNC;
import de.uni_kl.cs.disco.snc.calculator.analysis.ConvolutionState;
import de.uni_kl.cs.disco.snc.calculator.analysis.SimpleEndToEndConvolution;
import de.uni_kl.cs.disco.snc.calculator.network.Flow;
import de.uni_kl.cs.disco.snc.calculator.network.Network;

import java.util.List;

/**
 * Convolute two vertices ({@link Vertex}) in a given target network
 * with an arbitrary network in between those nodes.
 * 
 * @author Sebastian Henningsen
 */
public class EndToEndConvolutionCommand implements Command {
	private final int vertex1ID, vertex2ID;
	private final Flow flowOfInterest;
	private final Network nw;
	private final SNC snc;
    
    public EndToEndConvolutionCommand(int vertex1ID, int vertex2ID, int flowID, int networkID, SNC snc) {
        this.vertex1ID = vertex1ID;
        this.vertex2ID = vertex2ID;
        this.nw = snc.getCurrentNetwork();
        this.flowOfInterest = nw.getFlow(flowID);
        this.snc = snc;
    }
    
    @Override
    public void execute() {
        SimpleEndToEndConvolution convolution = new SimpleEndToEndConvolution(flowOfInterest);
        List<ConvolutionState> results = convolution.computeAllConvolutions("", vertex1ID, vertex2ID, nw);
        for (ConvolutionState res : results) {
            System.out.println(res.getOperations());
        }
    }

    @Override
    public void undo() {
        // TODO
        System.out.println("Undo Action for End-To-End-Convolution not implemented yet.");
    }
}
