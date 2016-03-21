package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class GUI extends JFrame implements Dialog{

	private static final long serialVersionUID = 1L;
	private Connection dbConnection;
	private Statement stat;
	
	private Products productSelected;
	private Employees employeeSelected;
	private Departments departmentSelected;
	private Equipments equipmentSelected;
	
	private Products editProduct;
	private Departments editDepartment;
	private Employees editEmployee;
	private Equipments editEquipment;
	
	private ArrayList<Products> productsList = new ArrayList<Products>();
	private ArrayList<Employees> employeesList = new ArrayList<Employees>();
	private ArrayList<Departments> departmentsList = new ArrayList<Departments>();
	private ArrayList<Equipments> equipmentsList = new ArrayList<Equipments>();
	
	GUI thisFrame;
	ViewScreen view; 
	
	//list
	ListSelectionListener productsListener;
	ListSelectionListener departmentsListener;
	ListSelectionListener employeesListener;
	ListSelectionListener equipmentsListener;
	
	ActionListener searchButtonListener; //search button
	MouseListener mouseListener; // double click
	KeyListener keyListener; // search text
	ChangeListener tabListener; // change tabs
	ActionListener newProduct; //adding a new product
	ActionListener newEmployee;
	ActionListener newEquipment; 
	
	public GUI(String string, Connection db, Statement s, ArrayList<Products> listp,
			ArrayList<Employees> liste, ArrayList<Departments> listd,
			ArrayList<Equipments> listeq) {
		
		super(string);
		dbConnection = db;
		stat = s;
		productsList = listp;
		employeesList = liste;
		departmentsList = listd;
		equipmentsList = listeq;
		
		thisFrame = this;
		
		addWindowListener( new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					try{
						System.out.println("Closing Database Connection");
						dbConnection.close();
					}catch (SQLException ex){
						ex.printStackTrace();
					}
					System.exit(0);
				}
			}	
		);
		
		add(view = new ViewScreen());
		
		searchButtonListener = new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				search();
			}
		};
		
		newProduct = new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				System.out.println("New Product");
				ProductChange pc = new ProductChange(thisFrame, thisFrame, "Product Details", true, null, stat, dbConnection);
				pc.setVisible(true);
			}
		};
		
		newEmployee = new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				System.out.println("New Employee");
				EmployeeChange ec = new EmployeeChange(thisFrame, thisFrame, "Employee Detail", true, null, stat, dbConnection);
				ec.setVisible(true);
			}
		};
		
		newEquipment = new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				System.out.println("New Equipment");
				EquipmentChange eqc = new EquipmentChange(thisFrame, thisFrame, "Equipment Details", true, null, stat, dbConnection);
				eqc.setVisible(true);
			}
		};
		
		mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent event){
				if(event.getClickCount() == 2){
					if(productSelected != null){
						JList<Products> theList = (JList<Products>) event.getSource();
						int index = theList.locationToIndex(event.getPoint());
						
						editProduct = (Products) theList.getModel().getElementAt(index);
						ProductChange pc = new ProductChange(thisFrame, thisFrame, "Product Details", true, editProduct, stat, dbConnection);
						pc.setVisible(true);
					}
					
					else if(departmentSelected != null){
						JList<Departments> theList = (JList<Departments>) event.getSource();
						int index = theList.locationToIndex(event.getPoint());
						
						editDepartment = (Departments) theList.getModel().getElementAt(index);
						DepartmentChange dc = new DepartmentChange(thisFrame, thisFrame, "Department Details", true, editDepartment, stat, dbConnection);
						dc.setVisible(true);
					}
					
					else if(employeeSelected != null){
						JList<Employees> theList = (JList<Employees>) event.getSource();
						int index = theList.locationToIndex(event.getPoint());
						
						editEmployee = (Employees) theList.getModel().getElementAt(index);
						EmployeeChange ec = new EmployeeChange(thisFrame, thisFrame, "Employee Detail", true, editEmployee, stat, dbConnection);
						ec.setVisible(true);
					}
					
					else if(equipmentSelected != null){
						JList<Equipments> theList = (JList<Equipments>) event.getSource();
						int index = theList.locationToIndex(event.getPoint());
						
						editEquipment = (Equipments) theList.getModel().getElementAt(index);
						EquipmentChange eqc = new EquipmentChange(thisFrame, thisFrame, "Equipment Details", true, editEquipment, stat, dbConnection);
						eqc.setVisible(true);
					}
				}
			}
		};
		
		
		keyListener = new KeyListener() {
			public void keyPressed(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyTyped(KeyEvent arg0) {
				int keyChar = arg0.getKeyChar();
				
				if(keyChar == KeyEvent.VK_ENTER){search();}
			}
		};
		
	    tabListener = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent changeEvent) {
				JTabbedPane source = (JTabbedPane) changeEvent.getSource();
				int index = source.getSelectedIndex();
				System.out.println("Tab changed to: " + source.getTitleAt(index));	
			}
	    };
	    
	    
	    productsListener = new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				System.out.println("Products List");
				selectProductsList();
			}
	    };
	    
	    departmentsListener = new ListSelectionListener(){
	    	public void valueChanged(ListSelectionEvent event){
	    		System.out.println("Departments List");
	    		selectDepartmentsList(); 
	    	}
	    };
	    
	    employeesListener = new ListSelectionListener(){
	    	public void valueChanged(ListSelectionEvent event){
	    		System.out.println("Employees List");
	    		selectEmployeesList();
	    	}
	    };
	    
	    equipmentsListener = new ListSelectionListener(){
	    	public void valueChanged(ListSelectionEvent event){
	    		System.out.println("Equipments List");
	    		selectEquipmentsList();
	    	}
	    };
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 700);
		
		update();
	}

	public void enableListeners(){
		view.getSearchButton().addActionListener(searchButtonListener);
		view.getSearchText().addKeyListener(keyListener);
		
		view.getTabbedPane().addChangeListener(tabListener);
		
		view.getNewProduct().addActionListener(newProduct);
		view.getNewEmployee().addActionListener(newEmployee);
		view.getNewEquipment().addActionListener(newEquipment);
		
		view.getProducts().addListSelectionListener(productsListener);
		view.getDepartments().addListSelectionListener(departmentsListener);
		view.getEmployees().addListSelectionListener(employeesListener);
		view.getEquipments().addListSelectionListener(equipmentsListener);
		
		view.getProducts().addMouseListener(mouseListener);
		view.getDepartments().addMouseListener(mouseListener);
		view.getEmployees().addMouseListener(mouseListener);
		view.getEquipments().addMouseListener(mouseListener);
	}
	
	public void disableListeners(){
		view.getSearchButton().removeActionListener(searchButtonListener);
		view.getSearchText().removeKeyListener(keyListener);
		
		view.getTabbedPane().removeChangeListener(tabListener);
		
		view.getNewProduct().removeActionListener(newProduct);
		view.getNewEmployee().removeActionListener(newEmployee);
		view.getNewEquipment().removeActionListener(newEquipment);
		
		view.getProducts().removeListSelectionListener(productsListener);
		view.getDepartments().removeListSelectionListener(departmentsListener);
		view.getEmployees().removeListSelectionListener(employeesListener);
		view.getEquipments().removeListSelectionListener(equipmentsListener);
		
		view.getProducts().removeMouseListener(mouseListener);
		view.getDepartments().removeMouseListener(mouseListener);
		view.getEmployees().removeMouseListener(mouseListener);
		view.getEquipments().removeMouseListener(mouseListener);
	}
	
	public void updateSearchButton(){
		view.getSearchButton().setEnabled(true);
	}
	
	public void populateList(){		
		Products productsTemp[] = new Products[1];
		Departments departmentsTemp[] = new Departments[1];
		Employees employeesTemp[] = new Employees[1];
		Equipments equipmentsTemp[] = new Equipments[1];
		
		view.getProducts().setListData(productsList.toArray(productsTemp));
		view.getDepartments().setListData(departmentsList.toArray(departmentsTemp));
		view.getEmployees().setListData(employeesList.toArray(employeesTemp));
		view.getEquipments().setListData(equipmentsList.toArray(equipmentsTemp));
	}
	
	public void update(){
		disableListeners();
		populateList();
		updateSearchButton();
		enableListeners();
	}
	
	
	public void search(){
		
	}
	
	public void selectProductsList(){
		productSelected = (Products) view.getProducts().getSelectedValue();
		System.out.println("Product:       " + productSelected);
	}
	
	public void selectDepartmentsList(){
		departmentSelected = (Departments) view.getDepartments().getSelectedValue();
		System.out.println("Department:      " + departmentSelected);
	}
	
	public void selectEmployeesList(){
		employeeSelected = (Employees) view.getEmployees().getSelectedValue();
		System.out.println("Employee:      " + employeeSelected);
	}
	
	public void selectEquipmentsList(){
		equipmentSelected = (Equipments) view.getEquipments().getSelectedValue();
		System.out.println("Equipment:      " + equipmentSelected);
	}

	@Override
	public void dialogFinished(Dialog.operation anOperation) {
		if(anOperation == Dialog.operation.UPDATE){
			System.out.println("UPDATE");
		}
		else if(anOperation == Dialog.operation.DELETE){
			System.out.println("DELETE");
		}
		productSelected = null;
		departmentSelected = null;
		employeeSelected = null;
		equipmentSelected = null;
		update();
	}

	@Override
	public void dialogCancelled() {	
		productSelected = null;
		departmentSelected = null;
		employeeSelected = null;
		equipmentSelected = null;
		update();
	}

}
