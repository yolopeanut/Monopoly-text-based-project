//WRITTEN BY BRANDON TAN
public class BlankLocation extends Location{
	/* GLOBAL VARIABLES */
	private String specialInfo;
	
	//Constructor which takes in the name, special info and position of the current location being added 
	public BlankLocation(String name, String specialInfo,int position)
	{
		//Transfers name and position to super class
		super(name,position);
		this.specialInfo = specialInfo;
	}
	
	//GETTER AND SETTER METHOD
	public String GetSpecialInfo()
	{
		return this.specialInfo;
	}
	
	
	
}
