package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import project.*;

public class MainClass {
	public static void main(String[] args){
		System.out.println("Project Database");
		GUI frame = null; 
		
		try{
			Class.forName("org.sqlite.JDBC");
			
			Connection database = DriverManager.getConnection("jdbc:sqlite:project.db");
			Statement stat = database.createStatement();
			
			
			String sqlProducts = "select * from products;";
			System.out.println(sqlProducts);
			
			ArrayList<Products> listProducts = new ArrayList<Products>();
			
			ResultSet rs = stat.executeQuery(sqlProducts);
			while(rs.next()){
				Products p = new Products(
						rs.getString("productName"),
						rs.getString("departmentCode"),
						rs.getString("brand"),
						rs.getDouble("cost"),
						rs.getString("country"),
						rs.getString("instock"),
						rs.getInt("quantity")
					);
				listProducts.add(p);
			}
			rs.close();
			
			String sqlEmployees = "select * from employees;";
			System.out.println(sqlEmployees);
			
			ArrayList<Employees> listEmployees = new ArrayList<Employees>();
			
			rs = stat.executeQuery(sqlEmployees);
			while(rs.next()){
				Employees e = new Employees(
					rs.getInt("employeeID"),
					rs.getString("departmentCode"),
					rs.getString("name"),
					rs.getLong("phoneNum")
				);
				listEmployees.add(e);
			}
			rs.close();
			
			String sqlDepartments = "select * from departments;";
			System.out.println(sqlDepartments);
			
			ArrayList<Departments> listDepartments = new ArrayList<Departments>();
			
			rs = stat.executeQuery(sqlDepartments);
			while(rs.next()){
				Departments d = new Departments(
					rs.getString("departmentCode"),
					rs.getInt("supervisorID")
				);
				listDepartments.add(d);
			}
			rs.close();
			
			String sqlEquipments = "select * from equipments;";
			System.out.println(sqlEquipments);
			
			ArrayList<Equipments> listEquipments = new ArrayList<Equipments>();
			
			rs.getStatement().executeQuery(sqlEquipments);
			while(rs.next()){
				Equipments eq = new Equipments(
					rs.getInt("equipID"),
					rs.getString("equipName")
				);
				listEquipments.add(eq);
			}
			rs.close();
			
			frame = new GUI("Grocery Database", database, stat, listProducts, listEmployees, 
						listDepartments, listEquipments);
		
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch (SQLException e){
			e.printStackTrace();
		}
	
		frame.setVisible(true);
	}
}
