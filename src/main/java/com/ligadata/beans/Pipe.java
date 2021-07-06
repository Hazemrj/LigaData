package com.ligadata.beans;

public class Pipe extends SuperCSV {

   int age;
	String fname;
   String lname;
   String email;
   String city;
   
   final String pipeFile;
   final String pipeDirectory;
   
   //default constructor
	public Pipe() {
      pipeFile = "pipe.csv";
      pipeDirectory = "C:/Users/hazem/OneDrive/Desktop/github/Java/pipe";
	}
   
   //parametrized constructor
	public Pipe(int age, String fname, String lname, String email, String city, String pipeFile, String pipeDirectory) {
      this.age = age;
      this.fname = fname;
      this.lname = lname;
      this.email = email;
      this.city = city;
      this.pipeFile = pipeFile;
      this.pipeDirectory = pipeDirectory;
	}

   
   
   public int getAge() {
		return age;
	}
   
	public String getFirstName() {
		return fname;
	}
   
   public String getLastName() {
		return lname;
	}
   
   public String getEmail() {
		return email;
	}
   
   public String getCity() {
		return city;
	}
   
   public String getPipeFile() {
		return pipeFile;
	}
   
   public String getPipeDirectory() {
      return pipeDirectory;
   }
   
   
   public void setAge(int age) {
		this.age = age;
	}
   
   public void setFirstName(String fname) {
		this.fname = fname;
	}
   
   public void setLastName(String lname) {
		this.lname = lname;
	}
   
   public void setEmail(String email) {
		this.email = email;
	}
   
   public void setCity(String city) {
		this.city = city;
	}
   
   

}
