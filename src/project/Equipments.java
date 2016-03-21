package project;

public class Equipments{

	private int id;
	private String name;
	
	public Equipments(int i, String n){
		name = n;
		id = i;
	}
	
	public String getName(){return name;}
	public int getID(){return id;}
	
	public void setName(String n){name = n;}
	public void setID(int i){id = i;}
	
	public String toString(){
		return Integer.toString(id) + "        " + name;
	}
}
