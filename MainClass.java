//test class
public class MainClass {
   
   //main method
	public static void main(String[] args) {
      
      //creating survey object from the DAO class
		SurveyDAO survey = new SurveyDAO();
      
      //creating object "john" from survey class and giving it a name and ID
		Survey john = new Survey();
		john.setName("John");
		john.setID(8);
      
      //creating object "martin" from survey class and giving it a name and ID
      Survey martin = new Survey();
		martin.setName("Martin");
		martin.setID(6);
		
      // Adding Data
      // survey.addFile("survey.csv");
		survey.add(john);
      survey.add(martin);
		// Deleting Data
		survey.delete(3);
		// Updating Data
		survey.update(martin);
		// Displaying Data
		survey.findAll();
	}
}