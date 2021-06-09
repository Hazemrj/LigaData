public class MainClass {

	public static void main(String[] args) {
   
		SurveyDAO survey = new SurveyDAO();
      
		Survey john = new Survey();
		john.setName("John");
		john.setID(8);
      
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