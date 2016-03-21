package project;

public class Employees{
	
	private int id;
	private String department;
	private String name;
	private long number;
	
	public Employees(int ID, String dCode, String n, long num){
		id = ID;
		department = dCode;
		name = n;
		number = num;
	}
	
	public int getID(){return id;}
	public String getDepartment(){return department;}
	public String getName(){return name;}
	public long getPhoneNumer(){return number;}
	
	public void setID(int i){id = i;}
	public void setDepartment(String d){department = d;}
	public void setName(String n){name = n;}
	public void setNumber(long n){number = n;}
	
	public String toString(){
		return  Integer.toString(id) + "   " + department + "     " + name + "    " + Long.toString(number);
	}

}
