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

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.networkcalculus.snc.SNC;
import org.networkcalculus.snc.commands.AddVertexCommand;
import org.networkcalculus.snc.commands.Command;
import org.networkcalculus.snc.symbolic_math.ServiceType;

/**
 * A dialog to get input from the user in order to compute the leftover service of a flow.
 * 
 * WARNING: Do not use this class!
 * 
 * @author Sebastian Henningsen
 */
public class SubtractFlowDialog {
    private final JPanel panel;
    private final JLabel alias;
    private final JLabel service;
    private final JLabel rate;
    private final JTextField aliasField;
    private final JTextField rateField;
    private final JComboBox<ServiceType> serviceTypes;
    private final GridLayout layout;

    /**
     * Constructs the dialog and initializes all necessary fields.
     */
    public SubtractFlowDialog() {
        panel = new JPanel();
        
        alias = new JLabel("Alias of the vertex: ");
        service = new JLabel("Service Type: ");
        rate = new JLabel("Rate: ");
        aliasField = new JTextField();
        rateField = new JTextField(10);
       
        serviceTypes = new JComboBox<>(ServiceType.values());
        
        panel.add(alias);
        panel.add(aliasField);
        panel.add(service);
        panel.add(serviceTypes);
        panel.add(rate);
        panel.add(rateField);
        
        layout = new GridLayout(0, 1);
        panel.setLayout(layout);
    }

    /**
     * Displays the dialog
     */
    public void display() {
        int result = JOptionPane.showConfirmDialog(null, panel, "Add Vertex",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            SNC snc = SNC.getInstance();
            double rate = Double.parseDouble(rateField.getText());
            Command cmd = new AddVertexCommand(aliasField.getText(), -1*rate, -1, snc);
            snc.invokeCommand(cmd);

        }
    }
}
