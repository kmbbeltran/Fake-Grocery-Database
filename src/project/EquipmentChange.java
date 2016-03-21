package project;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EquipmentChange extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	
	Connection dbConnect;
	Statement stat;
	Dialog dialog;
	
	private Equipments item; 
	
	private JButton okButton;
	private JButton cancelButton;
	private JButton deleteButton;
	
	private JLabel IDLabel;
	private JTextField IDField;
	private JLabel nameLabel;
	private JTextField nameField;
	
	public JTextField getID(){return IDField;}
	public JTextField getEName(){return nameField;}

	public EquipmentChange(Frame owner, Dialog client, String title, boolean b, Equipments edit,
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

	private void buildWindow(Equipments edit) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		IDLabel = new JLabel("Equipment ID");
		IDLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		IDLabel.setBounds(10, 37, 84, 14);
		contentPanel.add(IDLabel);
		
		IDField = new JTextField(Integer.toString(edit.getID()));
		IDField.setBounds(91, 35, 283, 20);
		contentPanel.add(IDField);
		IDField.setColumns(10);
		
		nameLabel = new JLabel("Equipment Name");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nameLabel.setBounds(10, 82, 94, 14);
		contentPanel.add(nameLabel);
		
		nameField = new JTextField(edit.getName());
		nameField.setBounds(107, 80, 267, 20);
		contentPanel.add(nameField);
		nameField.setColumns(10);
		
		
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
		
		IDLabel = new JLabel("Equipment ID");
		IDLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		IDLabel.setBounds(10, 37, 84, 14);
		contentPanel.add(IDLabel);
		
		IDField = new JTextField();
		IDField.setBounds(91, 35, 283, 20);
		contentPanel.add(IDField);
		IDField.setColumns(10);
		
		nameLabel = new JLabel("Equipment Name");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nameLabel.setBounds(10, 82, 94, 14);
		contentPanel.add(nameLabel);
		
		nameField = new JTextField();
		nameField.setBounds(107, 80, 267, 20);
		contentPanel.add(nameField);
		nameField.setColumns(10);
		
		
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
			
			try{
				PreparedStatement ps = dbConnect.prepareStatement("update equipments set equipID=?, equipName=?");
				ps.setInt(1, Integer.parseInt(IDField.getText()));
				ps.setString(2, nameField.getName());
				ps.executeQuery();
				System.out.println("Update Database");
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		else{
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
		item.setName(nameField.getText());
		
		if(IDField.getText().length() != 0){
			try{
				PreparedStatement ps = dbConnect.prepareStatement("delete from equipments where equipID=" + item.getID() + ";");
				ps.executeQuery();
				System.out.println("Deleted Equipment");
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		else{
			try{
				PreparedStatement ps = dbConnect.prepareStatement("delete from equipments where equipName like ?");
				ps.setString(1, nameField.getText());
				ps.executeQuery();
				System.out.println("Deleted Equipment");
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
