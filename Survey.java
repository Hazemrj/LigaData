public class Survey {
   int ID;
	String name;
   
   //default constructor
	public Survey() {

	}
   
   //parametrized constructor
	public Survey( int ID, String name) {
      this.ID = ID;
		this.name = name;
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

}