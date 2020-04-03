//WRITTEN BY BRANDON TAN
public class ColourLocation extends Location{
	/* GLOBAL VARIABLES */
	private int numberOfHousesBought= 0; 		//To keep track of number of houses bought 
	private int numberOfHotelsBought = 0;		//To keep track of number of hotels bought 
	private int[] rent = new int[6];			//rent array to keep track of rent
	private int mortgage;						//Mortgage variable to keep track of mortgage value
	private int houseCost;						//House cost to keep track of cost of house
	private int ownedByWhichPlayer = 10; 		//Dummy value for 10, it means that no one currently owns this property
	private int numOfSameColourLocations;		//Variable to keep track of number of same locations to get houses
	private boolean isOwned = false;			//Variable to keep track of whether there is an owner in this property
	
	
	
	public ColourLocation()
	{
		//Blank Constructor
	}
	
	
	public ColourLocation(String colour, String name, int price,
			int defaultRent, int rent1, int rent2, int rent3, int rent4, int hotelRent, int mortgage, int houseCost,int position, int numOfSameColorLocations)
	{
		//Constructor which takes the price of the property and colour and inserts it into the super's constructor - Location.
		super(name,price,colour,position);
		this.rent[0] = defaultRent;
		this.rent[1] = rent1;
		this.rent[2] = rent2;
		this.rent[3] = rent3;
		this.rent[4] = rent4;
		this.rent[5] = hotelRent;
		this.mortgage = mortgage;
		this.houseCost = houseCost;
		this.numOfSameColourLocations = numOfSameColorLocations;
		
	}
	
	/////////////////// GETTER AND SETTER METHODS//////////////////////////////
	
	public int GetNumberOfHouses()
	{
		return numberOfHousesBought;
	}
	
	public int GetNumberOfHotels()
	{
		return numberOfHotelsBought;
	}
	public int[] GetRentArray()
	{
		return this.rent;
	}
	
	//Return rent based on number of properties this location has.
	public int GetRent()
	{
		if (numberOfHotelsBought == 1)
			return this.rent[5];
		else if (numberOfHousesBought == 0)
			return this.rent[0];
		else if (numberOfHousesBought == 1)
			return this.rent[1];
		else if (numberOfHousesBought == 2)
			return this.rent[2];
		else if (numberOfHousesBought == 3)
			return this.rent[3];
		else if (numberOfHousesBought == 4)
			return this.rent[4];
		return 0;
	}
	public int GetMortgage()
	{
		return this.mortgage;
	}
	public int GetHouseCost()
	{
		return this.houseCost;
	}
	public int GetOwnerOfProperty()
	{
		return this.ownedByWhichPlayer;
	}
	public boolean GetIsOwned()
	{
		return this.isOwned;
	}
	
	public int GetNumOfSameColourLocations()
	{
		return this.numOfSameColourLocations;
	}
	
	//////////////////////////////////////////////////////////////////////////
	
	//IF there is less than 4 houses bought, can purchase - return true
	//If there is 4 houses already bought, change ability to buy hotel true, which allows the user to buy hotel.
	public boolean UpgradeProperty()
	{
		if(numberOfHousesBought != 4)
		{
			numberOfHousesBought++;
			return true;
		}
		if(numberOfHousesBought == 4 )
		{
			numberOfHotelsBought++;
		}
		return false;
		
	}
	
	//Method to downgrade property. If there is a hotel, minus the hotel. If there is any houses, minus houses, hotel is priority 
	//Return true if successful
	public boolean DowngradeProperty()
	{
		if(numberOfHotelsBought == 1 )
		{
			numberOfHotelsBought--;
			return true;
		}
		else if(numberOfHousesBought != 0)
		{
			numberOfHousesBought--;
			return true;
		}
		
		return false;
		
	}
	
	//Method to Set the owner of the property and show that it is owned
	public void BuyingProperty(int playerCounter)
	{
		this.ownedByWhichPlayer = playerCounter;
		this.isOwned = true;
	}
	
	//Method to Set owner of property to default value and show that it is no longer owned
	public void MortgageProperty()
	{
		this.ownedByWhichPlayer = 10;
		this.isOwned = false;
	}

	
}
