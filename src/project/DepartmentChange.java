package project;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DepartmentChange extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	
	Connection dbConnect;
	Statement stat;
	Dialog dialog;
	
	private Departments item;
	
	private JTextField dCodeField;
	private JTextField supervisorField;
	
	private JButton okButton;
	private JButton cancelButton;
	private JButton deleteButton;
	
	public JTextField getCode(){return dCodeField;}
	public JTextField getSupervisor(){return supervisorField;}

	public DepartmentChange(Frame owner, Dialog client, String title, boolean b, Departments edit,
			Statement astat, Connection dbConnection) {
		
		super(owner,title,b);
		stat = astat;
		dbConnect = dbConnection;
		dialog = client;
		
		buildWindow(edit);
		
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
		
		deleteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				deleteSelected();
			}
		});
	}

	private void buildWindow(Departments edit) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		dCodeField = new JTextField(edit.getCode());
		dCodeField.setBounds(109, 29, 265, 19);
		contentPanel.add(dCodeField);
		dCodeField.setColumns(10);
		
		JLabel codeLabel = new JLabel("Deparment Code");
		codeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		codeLabel.setBounds(10, 31, 103, 14);
		contentPanel.add(codeLabel);
		
		supervisorField = new JTextField(Integer.toString(edit.getSupervisor()));
		supervisorField.setBounds(109, 59, 265, 20);
		contentPanel.add(supervisorField);
		supervisorField.setColumns(10);
		
		JLabel supervisorLabel = new JLabel("Supervisor ID");
		supervisorLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		supervisorLabel.setBounds(23, 62, 76, 14);
		contentPanel.add(supervisorLabel);
		
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
	
	// confirm the update
	private void okSelected(){
		
		if(dCodeField.getText().length() != 0){
			item.setCode(dCodeField.getText());
			item.setSupervisor(Integer.parseInt(supervisorField.getText()));
			
			try{
				PreparedStatement ps = dbConnect.prepareStatement("update departments set departmentCode=?, supervisorID=?");
				ps.setString(1, dCodeField.getText());
				ps.setInt(2, Integer.parseInt(supervisorField.getText()));
				ps.executeQuery();
				
				System.out.println("Updated Database");
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
		dispose();
	}
	
	private void deleteSelected(){
		item.setCode(dCodeField.getText());
		item.setSupervisor(Integer.parseInt(supervisorField.getText()));
		
		if(dCodeField.getText().length() != 0){
			try{
				PreparedStatement ps = dbConnect.prepareStatement("delete from departments where departmentcode='" + item.getCode() +"';");
				ps.executeQuery();
				System.out.println("Deleted Department");
			}catch(SQLException e){
				e.printStackTrace();
			}
		}else{
			try{
				PreparedStatement ps = dbConnect.prepareStatement("delete from departments where supervisor like ?;");
				ps.setInt(1, Integer.parseInt(supervisorField.getText()));
				System.out.println("Deleted Department");
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
