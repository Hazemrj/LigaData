package com.ligadata.beans;

public class Fslash extends SuperCSV {
  
   int ID;
   String fname;
   String bd;
   String path = "C:\\Users\\hazem\\OneDrive\\Desktop\\github\\Java\\fslash";

   
   //default constructor
	public Fslash() {

	}
   
   //parametrized constructor
	public Fslash(int ID, String fname, String bd, String path) {
      this.ID = ID;
      this.fname = fname;
      this.bd = bd;
      this.path = path;
	}

   //get and set method for int ID
   public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
   
   //get and set method for string Name
	public String getFirstName() {
		return fname;
	}

	public void setFirstName(String fname) {
		this.fname = fname;
	}
   
   public String getBD() {
		return bd;
	}

	public void setBD(String bd) {
		this.bd = bd;
	}

	public String getPath()
	{
		return path;
	}
   


}