package com.ligadata.beans;

public class Survey extends SuperCSV {
   int ID;
	String name;
   final String surveyFile;
   final String surveyDirectory;
   
   //default constructor
	public Survey() {
      surveyFile = "survey.csv";
      surveyDirectory = "C:/Users/hazem/OneDrive/Desktop/github/Java/survey";
	}
   
   //parametrized constructor
	public Survey( int ID, String name, String surveyFile, String surveyDirectory) {
      this.ID = ID;
		this.name = name;
      this.surveyFile = surveyFile;
      this.surveyDirectory = surveyDirectory;
	}

   //get and set method for int ID
   public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
   
   //get and set method for string Name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
   
   public String getSurveyFile() {
		return surveyFile;
	}
   
   public String getSurveyDirectory() {
      return surveyDirectory;
   }

}