package project;

import java.math.BigDecimal;

public class Products{

	private String name;
	private String department;
	private String brand;
	private Double cost;
	private String country;
	private String instock;
	private int quantity;
	
	public Products(String pName, String d, String b, Double c, String cntry, String yn, int q){
		name = pName;
		department = d;
		brand = b;
		cost = c;
		country = cntry;
		instock = yn;
		quantity = q;
	}
	
	public String getName(){return name;}
	public String getDepartment(){return department;}
	public String getBrand(){return brand;}
	public Double getCost(){return cost;}
	public String getCountry(){return country;}
	public String isInStock(){return instock;}
	public int getInventory(){return quantity;}
	
	public void setName(String n){name = n;}
	public void setDepartment(String d){department = d;}
	public void setBrand(String b){brand = b;}
	public void setCost(Double c){cost = c;}
	public void setCountry(String c){country = c;}
	public void setInventory(String yn){instock = yn;}
	public void inventory(int q){quantity = q;}
	
	public String toString(){
		return String.format("%s         %s        %s            $%.2f      Country:%s       In Stock(%s):%d", 
				department, brand, name, cost, country, instock, quantity);
	}
}
