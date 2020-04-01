package com.perisic.peripherals;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.perisic.beds.recycling.CustomerPanel;
import com.perisic.beds.recycling.PrinterInterface;
import com.perisic.peripherals.Display;




/**
 * A Simple Graphical User Interface for the Recycling Machine.
 * This class combines three different aspects: 
 * 1) Starting the software
 * 2) Implementing the GUI
 * 3) Handling events
 *  
 * @author Marc Conrad
 *
 */
@SuppressWarnings("unused")
public class RecyclingGUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -1077856539035586635L;
	/**
	 * Method that is called when an item has been entered into the machine or a receipt has been requested.
	 */
	int i; //to increment the progressBar
	static JLabel label = new JLabel();
	JProgressBar progressBar;
	
	public void actionPerformed(ActionEvent e) {
		if( e.getSource().equals(slot1)) { 
			myPanel.itemReceived(1);
			output.setText("Can inserted!");
			i++;
			progressBar.setValue(i);
			System.out.println(i);
		} else if( e.getSource().equals(slot2)) { 
			myPanel.itemReceived(2);
			output.setText("Bottle inserted!"); 
			i++;
			progressBar.setValue(i);
			System.out.println(i);
		} else if( e.getSource().equals(slot3)) { 
			myPanel.itemReceived(3);
			output.setText("Crate inserted!");
			i++;
			progressBar.setValue(i);
			System.out.println(i);
		} else if (e.getSource().equals(slot4)) {
			myPanel.itemReceived(4);
			output.setText("Tin inserted!");
			i++;
			progressBar.setValue(i);
			System.out.println(i);
		}else if (e.getSource().equals(reset)) {
			output.setText("Items cleared");
			label.setText(null);
			progressBar.setValue(0);
			i = 0;	
			System.out.println(i);
		}else if(e.getSource().equals(receipt)) { 
			myPanel.printReceipt();
		} 
		else {
		
		}
	}	
		
	public static void setLabel(String y) {
		label.setText(y);
		label.setBackground(Color.WHITE);
		label.setForeground(Color.WHITE);
		label.repaint();
	}
	
	JButton slot1 = new JButton("Can"); 
	//Giving the CAN button an image
	Image canImage = new ImageIcon(this.getClass().getResource("/Can.png")).getImage();
	
	//Giving the BOTTLE button an image
	JButton slot2 = new JButton("Bottle");
	Image bottleImage = new ImageIcon(this.getClass().getResource("/Bottle.png")).getImage();
	
	//Giving the CRATE button an image
	JButton slot3 = new JButton("Crate"); 
	Image crateImage = new ImageIcon(this.getClass().getResource("/Crate.png")).getImage();
	
	//Giving the TIN button an image
	JButton slot4 = new JButton("Tin");
	Image tinImage = new ImageIcon(this.getClass().getResource("/Tin.png")).getImage();
	
	//Giving the RESET button an image
	JButton reset = new JButton("Reset");
	Image resetImage = new ImageIcon(this.getClass().getResource("/Reset.png")).getImage();
	
	//Giving the RECEIPT button an image
	JButton receipt = new JButton("Receipt"); 
	Image receiptImage = new ImageIcon(this.getClass().getResource("/Receipt.png")).getImage();
	
	//Customer Panel
	CustomerPanel myPanel = null;

	//Timer for ProgressBar
	Timer t = new Timer(1000, this);
	
	JTextArea output = new JTextArea(10, 56);
	
	//Scroll pane
	//JScrollPane pane = new JScrollPane();
	
	public RecyclingGUI() {
		super();
		//setLayout(new FlowLayout());
		setSize(700, 400);
		setBackground(new Color(255,0,0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setTitle("Recycling Machine"); //gives the GUI a title
		
		JPanel panel = new JPanel(); 
		panel.setLayout(new BorderLayout(0,1));
		panel.setSize(500,800);
		panel.setBackground(new Color(0,0,255)); //this adds the background colour to the UI
		panel.add(slot1); 
		panel.add(slot2);
		panel.add(slot3); 
		panel.add(slot4);
		panel.add(reset);
		
		
		panel.add(receipt); 
		receipt.addActionListener(this); 
		
		//ProgressBar
		i = 0;
		progressBar = new JProgressBar(0, 10);
		progressBar.setBackground(new Color(255, 255, 0));
		progressBar.setForeground(new Color(255, 0, 0));
		
		//scroll pane modifications
		panel.setLayout(new FlowLayout());
		//pane.setSize(200,200);
		//pane.setBackground(new Color(0,0,255));
	
		//JTextArea 
		output.setBackground(new Color(50,205,50));
		panel.add(output);
		
		//panel.add(pane, BorderLayout.CENTER);
		
		//ProgressBar added
		panel.add(progressBar);
		
		//JLabel
		panel.add(label);
		
		slot1.setIcon(new ImageIcon(canImage));
		slot1.setBackground(Color.ORANGE);
		slot2.setIcon(new ImageIcon(bottleImage));
		slot2.setBackground(Color.ORANGE);
		slot3.setIcon(new ImageIcon(crateImage));
		slot3.setBackground(Color.ORANGE);
		slot4.setIcon(new ImageIcon(tinImage));
		slot4.setBackground(Color.ORANGE);
		reset.setIcon(new ImageIcon(resetImage));
		reset.setBackground(Color.ORANGE);
		receipt.setIcon(new ImageIcon(receiptImage));
		receipt.setBackground(Color.ORANGE);
		
		
		slot1.addActionListener(this); 
		slot2.addActionListener(this); 
		slot3.addActionListener(this); 
		slot4.addActionListener(this);
		reset.addActionListener(this);		

		getContentPane().add(panel);
		panel.repaint();

		PrinterInterface myPrinter = new Display(); 
		myPanel = new CustomerPanel(myPrinter); 	
		
	}
	/**
	 * Main entry point into the recycling machine.
	 * @param args not used.
	 */
	public static void main(String [] args ) { 
		RecyclingGUI myGUI = new RecyclingGUI(); 
		myGUI.setVisible(true); 

	}
}
