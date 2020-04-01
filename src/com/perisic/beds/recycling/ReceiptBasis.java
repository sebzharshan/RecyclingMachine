package com.perisic.beds.recycling;
import java.util.Vector;

import com.perisic.peripherals.RecyclingGUI; 

/**
 * This is where the data lives, i.e. cans, bottles and crates are recorded
 * in this class. We might call it our database (if we insist!). 
 * It also provides a summative statement about all the items inserted into the 
 * machine. 
 * @author Marc Conrad
 *
 */

/**
 * Now, the fourth item, Tin, is also recorded here.
 * @author harshan
 *
 */
public class ReceiptBasis {
	private Vector<DepositItem> myItems = new Vector<DepositItem>();
	
	/**
	 * @param item an item that has been inserted into the machine (such as can, bottle, crate). 
	 */
	public int b = 0, c = 0, cr = 0, t = 0, tI;
	public int x = 0;
	public String z;
	
	public void addItem(DepositItem item) { 
		
		//myItems.add(item); 
		//item.number = myItems.indexOf(item); 
		
		if(myItems.size() <= 9) { //max = 10; since arrays start from 0, I put '<=9'
		myItems.add(item); 
		item.number = myItems.indexOf(item); 
		x = myItems.size();
		
		}
		else {
			System.out.println("Machine is Full!");
			z = "Machine is Full! Maximum: 10 items";
			RecyclingGUI.setLabel(z);
		}
		
	}
	/**
	 * Calculates a summary based on the items inserted.
	 * @return the overall value of the items inserted by the customer.
	 */
	public String computeSum() { 
		String receipt = ""; 
		int sum = 0;

		for(int i=0; i < myItems.size(); i++ ) {
			DepositItem item = myItems.get(i); 
			receipt = receipt + "Item "+(item.number + 1) +  ": "+item.value +" ("+item.getName()+")"; //(item.number + 1) this start the count from 1
			receipt = receipt + System.getProperty("line.separator");
			sum = sum + item.value; 
			
			//code to count the number of bottles, cans, crates or tins added:

			if(item.value == 16) {
				c++;				
			} else if(item.value == 18) {
				b++;
			} else if(item.value == 42) {
				cr++;
			} else {
				t++;
			} 
			
		}
		tI = c + b + cr + t; //add all the items added into the machine
		receipt = receipt + System.getProperty("line.separator");
		receipt = receipt + "Total: "+ sum; 
		receipt = receipt + System.getProperty("line.separator");
		receipt = receipt + System.getProperty("line.separator");
		//code to show how many bottles, cans, crates and tins have been added:
		receipt = receipt + "Cans added: " + c; 
		receipt = receipt + System.getProperty("line.separator");
		receipt = receipt + "Bottle added: " + b; 
		receipt = receipt + System.getProperty("line.separator");
		receipt = receipt + "Crates added: " + cr; 
		receipt = receipt + System.getProperty("line.separator");
		receipt = receipt + "Tins added: " + t; 
		receipt = receipt + System.getProperty("line.separator");
		receipt = receipt + "Items added: " + tI; 
		
		return receipt; 
	}

}
