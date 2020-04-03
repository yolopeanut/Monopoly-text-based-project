

public class SpecialLocation extends Location{
	private boolean isOwned = false;
	private int mortgage;
	private int ownedByWhichPlayer = 10; //Dummy value for 10, it means that no one currently owns this property
	private int[] rent = new int[4];


	
	//Constructor to initiate the special location and store the following variables in the super class.
	public SpecialLocation(String name, int price, int mortgage,int position, int rent1, int rent2, int rent3, int rent4) 
	{
		super(name, price, position);
		this.mortgage = mortgage;
		this.rent[0] = rent1;
		this.rent[1] = rent2;
		this.rent[2] = rent3;
		this.rent[3] = rent4;

	}
	//================================GETTER METHODS=======================================//
	public boolean GetIsOwned()
	{
		return this.isOwned;
	}
	public int GetMortgage()
	{
		return this.mortgage;
	}
	public int GetOwnerOfProperty()
	{
		return this.ownedByWhichPlayer;
	}
	public int GetRent(int index)
	{
		return rent[index];
	}
	public int GetRent(int diceSum, int counter)
	{
		if(counter == 1)
			return diceSum * 4;
		else
			return diceSum * 10;
	}
	//=================================================================================//

	//Method which sets the owner of the property and says that there is an owner for the property
	public void BuyingProperty(int playerCounter)
	{
		this.ownedByWhichPlayer = playerCounter;
		this.isOwned = true;
	}
}
