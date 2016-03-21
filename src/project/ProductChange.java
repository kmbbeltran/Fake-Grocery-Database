package project;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ProductChange extends JDialog{

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	
	Dialog dialog;
	Statement stat;
	Connection dbConnect;
	
	private Products item;
	
	private JButton okButton;
	private JButton cancelButton;
	private JButton deleteButton;
	private JLabel dLabel;
	private JLabel brLabel;
	private JLabel nLabel;
	private JLabel cLabel;
	private JLabel costLabel;
	private JLabel sLabel;
	private JLabel qLabel;
	private JTextField codeField;
	private JTextField brandField;
	private JTextField cntryField;
	private JTextField nameField;
	private JTextField costField;
	private JTextField stockField;
	private JTextField qtyField;
	
	public ProductChange(Frame owner, Dialog client, String title, boolean b, Products edit, Statement astat,
			Connection dbConnection) {
		
		super(owner,title,b);
		stat = astat;
		dbConnect = dbConnection;
		dialog = client;
		
		if(edit == null){
			buildWindow();
		}else{
			buildWindow(edit);
			deleteButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					deleteSelected();
				}
			});
		}
		
		setSize(400, 250);
		
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				okSelected();
			}
		});
		
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				cancelSelected();
			}
		});
	}
	

	private void buildWindow(Products edit){
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		dLabel = new JLabel("Department");
		dLabel.setBounds(10, 11, 57, 14);
		contentPanel.add(dLabel);
		
		brLabel = new JLabel("Brand");
		brLabel.setBounds(10, 47, 46, 14);
		contentPanel.add(brLabel);
		
		nLabel = new JLabel("Name");
		nLabel.setBounds(10, 82, 46, 14);
		contentPanel.add(nLabel);
		
		cLabel = new JLabel("Country");
		cLabel.setBounds(10, 107, 46, 14);
		contentPanel.add(cLabel);
		
		costLabel = new JLabel("Cost");
		costLabel.setBounds(10, 132, 46, 14);
		contentPanel.add(costLabel);
		
		sLabel = new JLabel("In-Stock(Y/N)");
		sLabel.setBounds(122, 132, 73, 14);
		contentPanel.add(sLabel);
		
		qLabel = new JLabel("Quantity");
		qLabel.setBounds(260, 132, 46, 14);
		contentPanel.add(qLabel);
		
		codeField = new JTextField(edit.getDepartment());
		codeField.setBounds(77, 8, 256, 20);
		contentPanel.add(codeField);
		codeField.setColumns(10);
		
		brandField = new JTextField(edit.getBrand());
		brandField.setBounds(77, 44, 256, 20);
		contentPanel.add(brandField);
		brandField.setColumns(10);
		
		cntryField = new JTextField(edit.getCountry());
		cntryField.setBounds(77, 104, 256, 20);
		contentPanel.add(cntryField);
		cntryField.setColumns(10);
		
		nameField = new JTextField(edit.getName());
		nameField.setBounds(77, 79, 256, 20);
		contentPanel.add(nameField);
		nameField.setColumns(10);
		
		costField = new JTextField(Double.toString(edit.getCost()));
		costField.setBounds(10, 147, 86, 20);
		contentPanel.add(costField);
		costField.setColumns(10);
		
		stockField = new JTextField(edit.isInStock());
		stockField.setBounds(120, 147, 86, 20);
		contentPanel.add(stockField);
		stockField.setColumns(10);
		
		qtyField = new JTextField(Integer.toString(edit.getInventory()));
		qtyField.setBounds(260, 147, 86, 20);
		contentPanel.add(qtyField);
		qtyField.setColumns(10);

		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		okButton = new JButton("OK");
		buttonPane.add(okButton);

		deleteButton = new JButton("Delete");
		buttonPane.add(deleteButton);

		cancelButton = new JButton("Cancel");
		buttonPane.add(cancelButton);
	}
	
	private void buildWindow(){
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		dLabel = new JLabel("Department");
		dLabel.setBounds(10, 11, 57, 14);
		contentPanel.add(dLabel);
		
		brLabel = new JLabel("Brand");
		brLabel.setBounds(10, 47, 46, 14);
		contentPanel.add(brLabel);
		
		nLabel = new JLabel("Name");
		nLabel.setBounds(10, 82, 46, 14);
		contentPanel.add(nLabel);
		
		cLabel = new JLabel("Country");
		cLabel.setBounds(10, 107, 46, 14);
		contentPanel.add(cLabel);
		
		costLabel = new JLabel("Cost");
		costLabel.setBounds(10, 132, 46, 14);
		contentPanel.add(costLabel);
		
		sLabel = new JLabel("In-Stock(Y/N)");
		sLabel.setBounds(122, 132, 73, 14);
		contentPanel.add(sLabel);
		
		qLabel = new JLabel("Quantity");
		qLabel.setBounds(260, 132, 46, 14);
		contentPanel.add(qLabel);
		
		codeField = new JTextField("");
		codeField.setBounds(77, 8, 256, 20);
		contentPanel.add(codeField);
		codeField.setColumns(10);
		
		brandField = new JTextField("");
		brandField.setBounds(77, 44, 256, 20);
		contentPanel.add(brandField);
		brandField.setColumns(10);
		
		cntryField = new JTextField("");
		cntryField.setBounds(77, 104, 256, 20);
		contentPanel.add(cntryField);
		cntryField.setColumns(10);
		
		nameField = new JTextField("");
		nameField.setBounds(77, 79, 256, 20);
		contentPanel.add(nameField);
		nameField.setColumns(10);
		
		costField = new JTextField("");
		costField.setBounds(10, 147, 86, 20);
		contentPanel.add(costField);
		costField.setColumns(10);
		
		stockField = new JTextField("");
		stockField.setBounds(120, 147, 86, 20);
		contentPanel.add(stockField);
		stockField.setColumns(10);
		
		qtyField = new JTextField("");
		qtyField.setBounds(260, 147, 86, 20);
		contentPanel.add(qtyField);
		qtyField.setColumns(10);

		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		okButton = new JButton("OK");
		buttonPane.add(okButton);

		cancelButton = new JButton("Cancel");
		buttonPane.add(cancelButton);
	}
	
	// confirm the update
	private void okSelected(){
		if(nameField.getText().length() != 0){
			item.setName(nameField.getText());
			item.setDepartment(codeField.getText());
			item.setBrand(brandField.getText());
			item.setCountry(cntryField.getText());
			item.setCost(Double.parseDouble(costField.getText()));
			item.setInventory(stockField.getText());
			item.inventory(Integer.parseInt(qtyField.getText()));
			
			try{
				PreparedStatement ps = dbConnect.prepareStatement("update products set productName=?, "
						+ "departmentCode=?, brand=?, cost=?, country=?, instock=?, quantity=?");
				ps.setString(1, nameField.getText());
				ps.setString(2, codeField.getText());
				ps.setString(3, brandField.getText());
				ps.setDouble(4, Double.parseDouble(costField.getText()));
				ps.setString(5, cntryField.getText());
				ps.setString(6, stockField.getText());
				ps.setInt(7, Integer.parseInt(qtyField.getText()));
				
				ps.executeQuery();
				System.out.println("Update Database");
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}else{
			System.out.println("The key is null");
		}

		if (dialog != null) {dialog.dialogFinished(Dialog.operation.UPDATE);}
		dispose();
	}
	
	private void cancelSelected(){
		if (dialog != null) {dialog.dialogCancelled();}
		item = null;
		dispose();
	}
	
	private void deleteSelected(){
		item.setDepartment(codeField.getText());
		item.setName(nameField.getText());
		item.setBrand(brandField.getText());
		item.setCost(Double.parseDouble(costField.getText()));
		item.setCountry(cntryField.getText());
		item.setInventory(stockField.getText());
		item.inventory(Integer.parseInt(qtyField.getText()));
		
		
		if(nameField.getText().length() != 0){
			try{
				PreparedStatement ps = dbConnect.prepareStatement("delete from products where productName='" + item.getName() + "';");
				ps.executeQuery();
				System.out.println("Product Deleted");
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}else{
			try{
				PreparedStatement ps = dbConnect.prepareStatement("delete from products where departmentCode like ? and " + 
							"brand like ? and cost like ? and country like ? and instock like ? and quantity like ?;");
				ps.setString(1, codeField.getText());
				ps.setString(2, brandField.getText());
				ps.setDouble(3, Double.parseDouble(costField.getText()));
				ps.setString(4, cntryField.getText());
				ps.setString(5, stockField.getText());
				ps.setInt(6, Integer.parseInt(qtyField.getText()));
				
				ps.executeQuery();
				System.out.println("Product Deleted");
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	}
}
