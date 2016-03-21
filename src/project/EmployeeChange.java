package project;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class EmployeeChange extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	Connection dbConnect;
	Statement stat;
	Dialog dialog;
	
	private Employees item;
	
	private JButton okButton;
	private JButton cancelButton;
	private JButton deleteButton;
	private JLabel IDLabel;
	private JLabel nameLabel;
	private JLabel departmentLabel;
	private JLabel numLabel;
	private JTextField IDField;
	private JTextField departmentField;
	private JTextField nameField;
	private JTextField numField;
	
	
	public JTextField getID(){return IDField;}
	public JTextField getDepartment(){return departmentField;}
	public JTextField getNumber(){return numField;}
	public JTextField getEName(){return nameField;}
	
	public EmployeeChange(Frame owner, Dialog client, String title, boolean b, Employees edit,
			Statement astat, Connection dbConnection) {
		
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

	private void buildWindow(Employees edit) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		IDLabel = new JLabel("Employee ID");
		IDLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		IDLabel.setBounds(10, 21, 66, 14);
		contentPanel.add(IDLabel);
		
		nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nameLabel.setBounds(10, 97, 46, 14);
		contentPanel.add(nameLabel);
		
		departmentLabel = new JLabel("Department");
		departmentLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		departmentLabel.setBounds(10, 58, 66, 14);
		contentPanel.add(departmentLabel);
		
		numLabel = new JLabel("Phone #");
		numLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		numLabel.setBounds(10, 139, 66, 14);
		contentPanel.add(numLabel);
		
		IDField = new JTextField(Integer.toString(edit.getID()));
		IDField.setBounds(84, 19, 290, 20);
		contentPanel.add(IDField);
		IDField.setColumns(10);
		
		departmentField = new JTextField(edit.getDepartment());
		departmentField.setBounds(86, 56, 288, 20);
		contentPanel.add(departmentField);
		departmentField.setColumns(10);
		
		nameField = new JTextField(edit.getName());
		nameField.setBounds(84, 95, 290, 20);
		contentPanel.add(nameField);
		nameField.setColumns(10);
		
		numField = new JTextField(Long.toString(edit.getPhoneNumer()));
		numField.setBounds(86, 137, 288, 20);
		contentPanel.add(numField);
		numField.setColumns(10);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		okButton = new JButton("OK");
		buttonPane.add(okButton);
		
		deleteButton = new JButton("Delete");
		buttonPane.add(deleteButton);

		cancelButton = new JButton("Cancel");
		buttonPane.add(cancelButton);
	}
	
	private void buildWindow() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		IDLabel = new JLabel("Employee ID");
		IDLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		IDLabel.setBounds(10, 21, 66, 14);
		contentPanel.add(IDLabel);
		
		nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nameLabel.setBounds(10, 97, 46, 14);
		contentPanel.add(nameLabel);
		
		departmentLabel = new JLabel("Department");
		departmentLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		departmentLabel.setBounds(10, 58, 66, 14);
		contentPanel.add(departmentLabel);
		
		numLabel = new JLabel("Phone #");
		numLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		numLabel.setBounds(10, 139, 66, 14);
		contentPanel.add(numLabel);
		
		IDField = new JTextField();
		IDField.setBounds(84, 19, 290, 20);
		contentPanel.add(IDField);
		IDField.setColumns(10);
		
		departmentField = new JTextField();
		departmentField.setBounds(86, 56, 288, 20);
		contentPanel.add(departmentField);
		departmentField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setBounds(84, 95, 290, 20);
		contentPanel.add(nameField);
		nameField.setColumns(10);
		
		numField = new JTextField();
		numField.setBounds(86, 137, 288, 20);
		contentPanel.add(numField);
		numField.setColumns(10);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		okButton = new JButton("OK");
		buttonPane.add(okButton);

		cancelButton = new JButton("Cancel");
		buttonPane.add(cancelButton);
	}
	
	// confirm the update
	private void okSelected(){
		if(IDField.getText().length() != 0){
			item.setID(Integer.parseInt(IDField.getText()));
			item.setName(nameField.getText());
			item.setDepartment(departmentField.getText());
			item.setNumber(Long.parseLong(numField.getText()));
			
			try {
				PreparedStatement ps = dbConnect.prepareStatement("update employees set employeeID=?, departmentCode=?, name=?, phoneNum=?");
				ps.setInt(1, Integer.parseInt(IDField.getText()));
				ps.setString(2, departmentField.getText());
				ps.setString(3, nameField.getText());
				ps.setLong(4, Long.parseLong(numField.getText()));
				ps.executeQuery();
				System.out.println("Updated Database");

			} catch (SQLException e) {
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
		dispose();
	}
	
	private void deleteSelected(){
		item.setID(Integer.parseInt(IDField.getText()));
		item.setDepartment(departmentField.getText());
		item.setName(nameField.getText());
		item.setNumber(Long.parseLong(numField.getText()));
		
		if(IDField.getText().length() != 0){
			try{
				PreparedStatement ps = dbConnect.prepareStatement("delete from employees where employeeID=" + item.getID() + ";");
				ps.executeQuery();
				System.out.println("Deleted Employee");
			}catch(SQLException e){
				e.printStackTrace();
			}
		}else{
			try{
				PreparedStatement ps = dbConnect.prepareStatement("delete from employess where departmentCode like ? and name like ? and phoneNum like ?;");
				ps.setString(1, departmentField.getText());
				ps.setString(2, nameField.getText());
				ps.setLong(3, Long.parseLong(numField.getText()));
				ps.executeQuery();
				System.out.println("Deleted Employee");
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		
	}

}
