package project;

public class Departments{
	
	private String code;
	private int supervisor;
	
	public Departments(String c, int s){
		code = c;
		supervisor = s;
	}
	
	public String getCode(){return code;}
	public int getSupervisor(){return supervisor;}
	
	public void setCode(String c){code = c;}
	public void setSupervisor(int s){supervisor = s;}
	
	public String toString(){
		return code + "            " + Integer.toString(supervisor);
	}

}
