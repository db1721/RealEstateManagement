package Project4;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/*
 * File: Project Name
 * Author: Dan Beck
 * Date: March 8, 2020
 * Purpose: Implements StateChangeable interface. It should 
 * 			contain five instance variables, the property address 
 * 			stored as a string, the number of bedrooms, the square 
 * 			footage and the price, all stored as integers, and the 
 * 			status of the property whose type should be the enumerated 
 * 			type Status.
 */

public class Property implements StateChangable
{
	DecimalFormat df = new DecimalFormat("###,###,###");
	String address;
	int bedrooms;
	int sqft;
	int price;
	int st;

	/*A constructor that accepts four parameters for the purpose of 
	*initializing the characteristics of the property, the address, the 
	*number of bedrooms, the square footage and the price. The status 
	*of the property should be set to FOR_SALE.
	*/
	public Property(String address, int bedrooms, int sqft, int price)
	{
		this.address = address;
		this.bedrooms = bedrooms;
		this.sqft = sqft;
		this.price = price;
		this.st = 1;
	}

	/*A method named changeState that allows the status of the property 
	*to be changed.
	*/
	public Status changeState(int st) 
	{
		if(st == 1)
			return Status.FOR_SALE;
		else if(st == 2)
			return Status.UNDER_CONTRACT;
		else if(st == 3)
			return Status.SOLD;
		else
			JOptionPane.showMessageDialog(null, "Error Occured");
		return null;
	}
	
	/*An overridden toString method that returns a string containing the 
	 * property address, the number of bedrooms, the square footage, 
	 * the price and the current status, appropriately labeled.
	 */
	@Override
	public String toString() 
	{
		return "Address: " + this.address +
			   "\nBedrooms: " + this.bedrooms + 
			   "\nSquare Footage: " + df.format(this.sqft) + " sq.ft." + 
			   "\nSale's Price: $" + df.format(this.price) +
			   "\nCurrrent Status: " + changeState(st);
	}
}
