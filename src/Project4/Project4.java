package Project4;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.TreeMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * File: Project 4
 * Author: Dan Beck
 * Date: March 8, 2020
 * Purpose: Class that generates the GUI and passes parameters to 
 * 			other classes.
 */

public class Project4
{
	private JFrame frame;
	private JTextField textPrice;
	private JTextField textTransactionNo;
	private JTextField textAddress;
	private JTextField textBedrooms;
	private JTextField textSquareFootage;
	
	// Variables for use in multiple methods
	int price;
	int transNo;
	String address;
	int bedrooms;
	int sqft;
	
	//Tree map storing all the house data
	TreeMap<Integer, Property> houseMap = new TreeMap<Integer, Property>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Project4 window = new Project4();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Project4() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		//Creates the frame
		frame = new JFrame("Real Estate Database");
		frame.setResizable(false);
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		//Generates the drop down menu for inserting, deleting or finding a property
		String[] store = {"Insert", "Delete", "Find"};
		JComboBox<String> dropdownStore = new JComboBox<String>(store);
		dropdownStore.setBounds(180, 176, 160, 22);
		frame.getContentPane().add(dropdownStore);
		
		//Generates the drop down menu for the enum values
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox dropdownEnumerated = new JComboBox(Status.values());
		dropdownEnumerated.setBounds(180, 210, 160, 22);
		frame.getContentPane().add(dropdownEnumerated);
		
		//Label for transaction number
		JLabel lblTransactionNo = new JLabel("Transaction No:");
		lblTransactionNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTransactionNo.setBounds(10, 11, 122, 22);
		frame.getContentPane().add(lblTransactionNo);
		
		//Label for address
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setBounds(10, 44, 122, 22);
		frame.getContentPane().add(lblAddress);
		
		//Label for bedrooms
		JLabel lblBedrooms = new JLabel("Bedrooms:");
		lblBedrooms.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBedrooms.setBounds(10, 77, 122, 22);
		frame.getContentPane().add(lblBedrooms);
		
		//Label for square footage
		JLabel lblSquareFootage = new JLabel("Square Footage:");
		lblSquareFootage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSquareFootage.setBounds(10, 110, 122, 22);
		frame.getContentPane().add(lblSquareFootage);
		
		//Label for price
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrice.setBounds(10, 143, 122, 22);
		frame.getContentPane().add(lblPrice);

		//Generates the Change Status button
		JButton btnChangeStatus = new JButton("Change Status");
		btnChangeStatus.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Property p = houseMap.get(transNo);
				switch(dropdownEnumerated.getSelectedIndex())
				{
				case 0:
					//Changes the status of the property to FOR_SALE
					JOptionPane.showMessageDialog(null, "Status for Property #" + 
							transNo + " has been changed to  FOR_SALE");
					p.st = 1;
					break;
				case 1:
					//Changes the status of the property to UNDER_CONTRACT
					JOptionPane.showMessageDialog(null, "Status for Property #" + 
							transNo + " has been changed to UNDER_CONTRACT");
					p.st = 2;
					break;
				case 2:
					//Changes the status of the property to SOLD
					JOptionPane.showMessageDialog(null, "Status for Property #" + 
							transNo + " has been changed to SOLD");
					p.st = 3;
					break;
				}
			}
		});
		btnChangeStatus.setBounds(10, 210, 160, 23);
		frame.getContentPane().add(btnChangeStatus);
		
		//Generates the process button
		JButton btnProcess = new JButton("Process");
		btnProcess.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					//Pulls variables from text fields 
					price = Integer.parseInt(textPrice.getText());
					address = textAddress.getText();
					bedrooms = Integer.parseInt(textBedrooms.getText());
					sqft = Integer.parseInt(textSquareFootage.getText());
					transNo = Integer.parseInt(textTransactionNo.getText());
					Property p = new Property(address, bedrooms, sqft, price);
				
				//Process button with insert selected
				if(dropdownStore.getSelectedIndex() == 0)
				{
					//Tests for correct input types
			     try 
			     {
			    	 if(houseMap.containsKey(transNo) == false)
			         {
			        	 houseMap.put(transNo, p); 
			        	 JOptionPane.showMessageDialog(null, "House " + transNo + 
			        				   " information has been stored");
						 }   
			        	 else 
			        	 {
			        		 JOptionPane.showMessageDialog(null, "Transaction number " + transNo + 
			        				   " is already is use!");
			        	 } 
			    	 }
			     catch(Exception er1) 
			     {
			    	 JOptionPane.showMessageDialog(null, "Please enter a valid parameter!");
			     }
			     }
				//Process button with delete selected
				else if(dropdownStore.getSelectedIndex() == 1) 
				{
					transNo = Integer.parseInt(textTransactionNo.getText());
					if(houseMap.containsKey(transNo) == true) 
					{
			        	houseMap.remove(transNo);
			        	JOptionPane.showMessageDialog(null, "House " + transNo + " has been deleted.");
			        }
					else
						JOptionPane.showMessageDialog(null, "Property number #" + transNo + 
			        				   " does not exist.");
				} 
				//Process button with find selected
				else if(dropdownStore.getSelectedIndex() == 2) 
				{
					if(houseMap.containsKey(transNo) == true) 
					{
			        transNo = Integer.parseInt(textTransactionNo.getText());
			        Property value = houseMap.get(transNo);  
					JOptionPane.showMessageDialog(null, value.toString());
					
					//Re-populates the pop-up with the found property info
					textAddress.setText(value.address);
					textBedrooms.setText(Integer.toString(value.bedrooms));
					textSquareFootage.setText(Integer.toString(value.sqft));
					textPrice.setText(Integer.toString(value.price));
					}
					//Checks if there is information with the provided transaction number
					else 
					{
						JOptionPane.showMessageDialog(null, "There is no information for Property #" +
									transNo);
				    }
					
			   	}
				else 
				{
					JOptionPane.showMessageDialog(null, "Something weird happened!");
			    }
				}
				catch(Exception er2)
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid parameter!");
				}
		        
			}
		});
		btnProcess.setBounds(10, 176, 160, 23);
		frame.getContentPane().add(btnProcess);

		//Generates text field for price
		textPrice = new JTextField();
		textPrice.setBounds(180, 145, 160, 22);
		frame.getContentPane().add(textPrice);
		textPrice.setColumns(10);
		
		//Generates text field for transaction number
		textTransactionNo = new JTextField();
		textTransactionNo.setColumns(10);
		textTransactionNo.setBounds(180, 14, 160, 22);
		frame.getContentPane().add(textTransactionNo);
		
		//Generates text field for address
		textAddress = new JTextField();
		textAddress.setColumns(10);
		textAddress.setBounds(180, 47, 160, 22);
		frame.getContentPane().add(textAddress);
		
		//Generates text field for bedrooms
		textBedrooms = new JTextField();
		textBedrooms.setColumns(10);
		textBedrooms.setBounds(180, 80, 160, 22);
		frame.getContentPane().add(textBedrooms);
		
		//Generates text field for square footage
		textSquareFootage = new JTextField();
		textSquareFootage.setColumns(10);
		textSquareFootage.setBounds(180, 113, 160, 22);
		frame.getContentPane().add(textSquareFootage);
		frame.setBounds(100, 100, 363, 281);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
}
