package project;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JList;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Insets;

public class ViewScreen extends JPanel {
	private static final long serialVersionUID = 1L;

	private Font font = new Font("Calibri", Font.BOLD, 16);

	private JTextField searchField;
	private JButton btnSearch;
	private JTabbedPane listPane;
	private JScrollPane productTab;
	private JScrollPane departmentsTab;
	private JScrollPane employeesTab;
	private JScrollPane equipmentTab;
	private JList<Products> productList;
	private JList<Departments> departmentList;
	private JList<Employees> employeeList;
	private JList<Equipments> equipmentList;
	
	private JButton addProduct;
	private JButton addEmployee;
	private JButton addEquipment;
	
	public JTabbedPane getTabbedPane(){return listPane;}
	public JTextField getSearchText(){return searchField;}
	public JButton getSearchButton(){return btnSearch;}
	
	public JList<Products> getProducts(){return productList;}
	public JList<Departments> getDepartments(){return departmentList;}
	public JList<Employees> getEmployees(){return employeeList;}
	public JList<Equipments> getEquipments(){return equipmentList;}
	
	public JButton getNewProduct(){return addProduct;}
	public JButton getNewEmployee(){return addEmployee;}
	public JButton getNewEquipment(){return addEquipment;}
	
	
	public ViewScreen() {
		setBackground(Color.DARK_GRAY);
		setBounds(500, 1500, 589, 503);
		this.setFont(font);
		setLayout(null);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(29, 72, 89, 23);
		add(btnSearch);
		
		searchField = new JTextField();
		searchField.setBounds(128, 72, 395, 22);
		add(searchField);
		searchField.setColumns(10);
		
		JLabel dbTitle = new JLabel("Grocery Store Database");
		dbTitle.setBounds(118, 22, 327, 51);
		dbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		dbTitle.setForeground(SystemColor.textHighlight);
		dbTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(dbTitle);
		
		listPane = new JTabbedPane(JTabbedPane.TOP);
		listPane.setBounds(10, 106, 569, 500);
		add(listPane);

		productTab = new JScrollPane();
		listPane.addTab("Products", null, productTab, null);
		productList = new JList<Products>();
		productTab.setViewportView(productList);
		
		addProduct = new JButton("Add a Product");
		productTab.setColumnHeaderView(addProduct);

		departmentsTab = new JScrollPane();
		listPane.addTab("Deparments", null, departmentsTab, null);
		departmentList = new JList<Departments>();
		departmentsTab.setViewportView(departmentList);
		
		employeesTab = new JScrollPane();
		listPane.addTab("Employees", null, employeesTab, null);
		employeeList = new JList<Employees>();
		employeesTab.setViewportView(employeeList);
		
		addEmployee = new JButton("Add an Employee");
		employeesTab.setColumnHeaderView(addEmployee);
		
		equipmentTab = new JScrollPane();
		listPane.addTab("Equipment", null, equipmentTab, null);
		equipmentList = new JList<Equipments>();
		equipmentTab.setViewportView(equipmentList);
		
		addEquipment = new JButton("Add an Equipment");
		equipmentTab.setColumnHeaderView(addEquipment);
	}
}
