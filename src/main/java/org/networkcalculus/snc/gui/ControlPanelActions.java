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

package org.networkcalculus.snc.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.networkcalculus.snc.SNC;
import org.networkcalculus.snc.commands.Command;
import org.networkcalculus.snc.commands.RemoveFlowCommand;
import org.networkcalculus.snc.commands.RemoveVertexCommand;
import org.networkcalculus.snc.commands.SubtractFlowCommand;
import org.networkcalculus.snc.network.Network;
import org.networkcalculus.snc.network.NetworkActionException;

/**
 * This static factory serves as collection of all actions corresponding to the {@link ControlPanel}.
 * 
 * @author Sebastian Henningsen
 * @author Michael Beck
 */
public class ControlPanelActions {
    static class AddNodeAction extends AbstractAction {
		private static final long serialVersionUID = -1455158874979173165L;

		public AddNodeAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            AddVertexDialog dialog = new AddVertexDialog();
            dialog.display();
        }
    }

    static class RemoveNodeAction extends AbstractAction {
		private static final long serialVersionUID = 9034466386639762182L;

		public RemoveNodeAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            Displayable[] selectables = MainWindow.convertDisplayables(SNC.getInstance().getCurrentNetwork().getVertices());
            if (selectables.length != 0) {
                Displayable d = (Displayable) JOptionPane.showInputDialog(
                        null,
                        "Please Choose a vertex: ",
                        "Customized Dialog",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        selectables,
                        selectables[0]);
                if (d != null) {
                    Command cmd = new RemoveVertexCommand(d.getID(), -1, SNC.getInstance());
                    try {
                    SNC.getInstance().invokeCommand(cmd);
                    System.out.println(d.getID() + " " + d.getAlias());
                    } catch (NetworkActionException ex) {
                        System.out.println(ex.getMessage());
                    }
                    
                } else {
                    System.out.println("No vertex selected");
                }
            } else {
                JOptionPane.showMessageDialog(null, "There are no vertices in the network!");
            }
        }
    }

    static class RemoveFlowAction extends AbstractAction {
		private static final long serialVersionUID = 1765962746248789312L;

		public RemoveFlowAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            // Show a dialog where the user can select a flow that will be removed
            // Box from a Map of IDs and Flows to an Array of Displayables which is needed for
            // the JComboBox
            Displayable[] selectables = MainWindow.convertDisplayables(SNC.getInstance().getCurrentNetwork().getFlows());
            if (selectables.length != 0) {
                Displayable d = (Displayable) JOptionPane.showInputDialog(
                        null,
                        "Please choose a flow: ",
                        "Customized Dialog",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        selectables,
                        selectables[0]);
                if (d != null) {
                    Command cmd = new RemoveFlowCommand(d.getID(), -1, SNC.getInstance());
                    try {
                    SNC.getInstance().invokeCommand(cmd);
                    System.out.println(d.getID() + " " + d.getAlias());
                    } catch (NetworkActionException ex) {
                        System.out.println(ex.getMessage());
                    }
                } else {
                    System.out.println("No flow selected.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "There are no flows in the network!");
            }
        }
    }

    static class AddFlowAction extends AbstractAction {
		private static final long serialVersionUID = -4318326828473374522L;

		public AddFlowAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            Network nw = SNC.getInstance().getCurrentNetwork();
            FlowEditor dialog = new FlowEditor("Add Flow", nw.getVertices(), nw, SNC.getInstance());
            dialog.showFlowEditor();
            //AddFlowDialog dialog = new AddFlowDialog();
            //dialog.display();
        }
    }

    static class AnalyzeNetworkAction extends AbstractAction {
		private static final long serialVersionUID = -1640400027656095696L;

		public AnalyzeNetworkAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            AnalyzeDialog dialog = new AnalyzeDialog();
            dialog.display();
        }
    }

    static class OptimizationAction extends AbstractAction {
		private static final long serialVersionUID = -7734362994973365550L;

		public OptimizationAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            OptimizationDialog dialog = new OptimizationDialog();
            dialog.display();
        }
    }
    
    static class SubtractAction extends AbstractAction {
		private static final long serialVersionUID = 963126323990960089L;

		public SubtractAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            // Choose vertex and compute left over service
            // Box from a Map of IDs and Vertices to an Array of Displayables which is needed for
            // the JComboBox
            Displayable[] selectables = MainWindow.convertDisplayables(SNC.getInstance().getCurrentNetwork().getVertices());
            if (selectables.length != 0) {
                Displayable d = (Displayable) JOptionPane.showInputDialog(
                        null,
                        "Please choose a vertex: ",
                        "Customized Dialog",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        selectables,
                        selectables[0]);
                if (d != null) {
                    Command cmd = new SubtractFlowCommand(d.getID(), -1, SNC.getInstance());
                    try {
                    SNC.getInstance().invokeCommand(cmd);
                    } catch(NetworkActionException ex) {
                        System.out.println(ex.getMessage());
                    }
                } else {
                    System.out.println("No vertex selected.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "There are no flows in the network!");
            }
        }
    }
    
    static class ConvolveAction extends AbstractAction {
		private static final long serialVersionUID = -6107536974274199642L;

		public ConvolveAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            // Choose vertices and flow of interest
            ConvolveVerticesDialog dialog = new ConvolveVerticesDialog();
            dialog.display();
        }
    }
}
