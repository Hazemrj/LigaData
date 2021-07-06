package com.ligadata.beans;

public class Perc extends SuperCSV {
  
   int ID;
	String fname;
   int grade;
   String country;
   
   final String percFile;
   final String percDirectory;
   
   //default constructor
	public Perc() {
      percFile = "percentage.csv";
      percDirectory = "C:/Users/hazem/OneDrive/Desktop/github/Java/percentage";
	}
   
   //parametrized constructor
	public Perc( int ID, String fname, int grade, String country, String percFile, String percDirectory) {
      this.ID = ID;
		this.fname = fname;
      this.grade = grade;
      this.country = country;
      this.percFile = percFile;
      this.percDirectory = percDirectory;
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
   
   public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
   
   public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
   
   
   
   
   public String getPercFile() {
		return percFile;
	}
   
   public String getPercDirectory() {
      return percDirectory;
   }

}