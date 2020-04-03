import java.util.Scanner;
//WRITTEN BY BRANDON TAN
public class Location {
	
	/* GLOBAL VARIABLES */
	public final static int COLOUR_LOCATION = 0; // If the property pays the user, e.g pass go 
	public final static int BLANK_LOCATION = 1; // For the property if it has to be paid
	public final static int SPECIAL_LOCATION = 2; //If the property is a train property

	/* LOCAL VARIABLES */
	private Location[] monopolyNorth = new Location[11];
	private Location[] monopolySouth = new Location[11];
	private Location[] monopolyEast = new Location[9];
	private Location[] monopolyWest = new Location[9];
	private String name;
	private int price;
	private String color;
	private int position;
	Scanner input = new Scanner(System.in);
	
	
	
	
	////////////////////////////////CONSTRUCTORS//////////////////////////////////////////
	
	public Location()
	{
		//Blank constructor
	}
	public Location(String name, int price, String color, int position) // Constructor for a payable property
	{
		//Constructor taking in price, and color
		this.name = name;
		this.price = price;
		this.color = color;
		this.position = position;
		 
	}
	
	public Location (String name,int position) // Constructor for a receivable property
	{
		this.name = name;
		this.position = position;
	}
	
	public Location(String name, int price, int position)
	{
		this.name = name;
		this.price = price;
		this.position = position;

	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//Done by Brandon Tan
	/////////////////////////////// GETTER AND SETTER METHODS/////////////////////////////////////////
	public String GetName()
	{
		return this.name;
	}
	public int GetPrice()
	{
		return this.price;
	}
	public String GetColor()
	{
		return this.color;
	}
	public Location[] GetMonopolyNorth()
	{
		return this.monopolyNorth;
	}
	public Location[] GetMonopolySouth()
	{
		return this.monopolySouth;
	}
	public Location[] GetMonopolyEast()
	{
		return this.monopolyEast;
	}
	public Location[] GetMonopolyWest()
	{
		return this.monopolyWest;
	}
	public int GetPosition()
	{
		return this.position;
	}
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	//Done by Brandon Tan
	//Method to add location from filename, creates instance of fileaccessor, then uses method and 
	//sends references of arrays north, south, east, west.
	public void AddLocation(String filename)
	{
		FileAccessor fileAccessor = new FileAccessor(filename);
		fileAccessor.ImportLocationFromFile(this.GetMonopolyNorth(), this.GetMonopolyEast(), this.GetMonopolySouth(), this.GetMonopolyWest());
	}

	//Method to return the direction the player is at currently.
	public String Direction(int position)
	{
		if(position<=10)
			return "North";
		else if(position>10 && position<=19)
			return "East";
		else if(position>19 && position<=30)
			return "South";
		else
			return "West";
	}
	//Method that takes in Player array and player counter from main
	//Returns true if successfully bought, which prints successfully bought in main
	//Returns false if not successfully bought and prints here
	public boolean BuyingLocation(Player[] playerArray,int playerCounter)
	{
		Player currPlayer = playerArray[playerCounter];
		Location[] buyLocation = new Location[0];
		boolean bought = false;
		if((this.Direction(currPlayer.GetPos())).equals("North"))
			buyLocation = this.monopolyNorth;
		else if((this.Direction(currPlayer.GetPos())).equals("East"))
			buyLocation = this.monopolyEast;
		else if((this.Direction(currPlayer.GetPos())).equals("South"))
			buyLocation = this.monopolySouth;
		else if((this.Direction(currPlayer.GetPos())).equals("West"))
			buyLocation = this.monopolyWest;

		for(int i = 0; i<buyLocation.length; i++)
		{
			//If the position of the location is equals to the position of the player
			if(buyLocation[i].GetPosition() == currPlayer.GetPos())
			{
				//If the location is an instance of the ColourLocation class
				if(buyLocation[i] instanceof ColourLocation)
				{
					//If the location variable is false, means its not owned
					if(((ColourLocation)buyLocation[i]).GetIsOwned() == false)
					{
						//If the play has more money than the price of the location
						if(currPlayer.GetMoney()>buyLocation[i].price)
						{
							//Uses the method buying property in colour location to set the currplayer as the owner
							((ColourLocation)buyLocation[i]).BuyingProperty(playerCounter);
							//Deducts current players money with the price of the location
							currPlayer.SetMoney(-buyLocation[i].GetPrice()); 
							//Add location to the locationOwned array in player class
							currPlayer.AddLocationToOwned(buyLocation[i]);
							return true;
						}
						// If the current player does not have sufficient funds
						else 
						{
							System.out.println("Error: Insufficient funds");
							bought = false;
						}
					}
					//If the owner of property is the current player print error
					else if (((ColourLocation)buyLocation[i]).GetOwnerOfProperty() == playerCounter)
					{
						System.out.println("Error: You already own this property");

					}
					//If the property is not owned by current player and not for sale print error
					else
					{
						System.out.println("Error: The property has been bought by: Player " + (((ColourLocation)buyLocation[i]).GetOwnerOfProperty()+1));
					}
				}
				//If location is instance of special location and not colour location
				else if(buyLocation[i] instanceof SpecialLocation)
				{
					//If the property is owned
					if(((SpecialLocation)buyLocation[i]).GetIsOwned() == false)
					{
						//If the player money is greater than price, allow to buy
						if(currPlayer.GetMoney()>buyLocation[i].price)
						{
							((SpecialLocation)buyLocation[i]).BuyingProperty(playerCounter);
							currPlayer.SetMoney(-buyLocation[i].GetPrice()); //Sets the player money using player array
							currPlayer.AddLocationToOwned(buyLocation[i]);

							return true;
						}
						//else print not enough money
						else 
						{
							System.out.println("Error: Insufficient funds");
							bought = false;
						}
					}
					//If the owner of property is the player, print error
					else if (((SpecialLocation)buyLocation[i]).GetOwnerOfProperty() == playerCounter)
					{
						System.out.println("Error: You already own this property");

					}
					//If property is owned but not by player, print error, bought by who
					else
					{
						System.out.println("Error: The property has been bought by: Player " + (((SpecialLocation)buyLocation[i]).GetOwnerOfProperty()+1));
					}
					
				}
				//If the location is instance of blanklocation
				else 
				{
					System.out.println("Error: Cannot purchase this location");
					bought = false;
				}
			}
		}
		return bought; //Dummy value
	}
	
	public boolean UpgradingLocation(Player[] playerArray,int playerCounter, int locationToBeUpgraded , String stringColourOfLocationToUpgrade)
	{
		Player currPlayer = playerArray[playerCounter];
		Location[] location = currPlayer.GetPropertyOwned(); 
		ColourLocation castedLocationToBeUpgraded = null;
		//Casted the location to colour location to prevent having to cast it everytime. Dummy value inserted into variable
		if(location[locationToBeUpgraded] instanceof ColourLocation)
			castedLocationToBeUpgraded = (ColourLocation)(location[locationToBeUpgraded]);

		
		//Try and catch used to enable ease of casting the location straight to a colour location and catches error when
		//there is a special location trying to be upgraded.
		try
		{
			castedLocationToBeUpgraded = (ColourLocation)(location[locationToBeUpgraded]); 
		}
		catch(ClassCastException e)
		{
			//If the location is a specialLocation, print cannot upgrade this location.
			System.out.println("Error: Cannot upgrade this location!");
			return false;
		}
		int numOfColour = 0; 	//Int to count the number of same colours within the array of owned locations
		boolean result = false;

		
		//Loop counting till the number of property owned. 
		for(int i = 0; i<currPlayer.GetPropertyOwned().length; i++)
		{
			
			//If statement checking if all the properties owned are an instance
			//of colourLocation, if it is a special location, cannot be upgraded
			if(location[i] instanceof ColourLocation)
			{
				//If the colour of this location equals to the colour of the location wanting to be upgraded.
				if((location[i].GetColor()).equals(stringColourOfLocationToUpgrade))
				{
					numOfColour++;
				}
			}
			
		}
		if(location[locationToBeUpgraded] instanceof ColourLocation)
		{
			//If the number of same colour locations is equals to the number of same colour locations in the player's owned location array
			if(castedLocationToBeUpgraded.GetNumOfSameColourLocations() == numOfColour)
			{
				
				//This if statement ensures that there is no hotel in place, otherwise will print out property has been fully upgraded
				if(castedLocationToBeUpgraded.GetNumberOfHotels() !=1)
				{
					
					//If the current players money is more than the house cost, deduct money and allow upgrading
					if(currPlayer.GetMoney()>(castedLocationToBeUpgraded.GetHouseCost()))
					{
						castedLocationToBeUpgraded.UpgradeProperty();
						currPlayer.SetMoney(-castedLocationToBeUpgraded.GetHouseCost()); //Sets the player money using player array
						result = true;
					}
					
					else 
					{
						System.out.println("Error: Insufficient funds");
						result = false;
					}
				}
				
				else
				{ //If the hotel is already purchased, that is the max value for upgrade.
					System.out.println("Error: The property has already been upgraded to the maximum available");
					return result;
				}
			}
			else //Prints not enough colours since user does not have all colours within this set.
				System.out.println("Error: You must have all the colours to upgrade this location!");
		}
		return result;
	}
	
	public boolean DowngradingLocation(Player[] playerArray,int playerCounter, int locationToBeDowngraded , String stringColourOfLocationToDowngrade)
	{
		Player currPlayer = playerArray[playerCounter];
		Location[] location = currPlayer.GetPropertyOwned(); 
		ColourLocation castedLocationToBeDowngraded = null;
		
		//Casted the location to colour location to prevent having to cast it everytime. Dummy value inserted into variable
		if(location[locationToBeDowngraded] instanceof ColourLocation)
			castedLocationToBeDowngraded = (ColourLocation)(location[locationToBeDowngraded]);

		//Try and catch used to enable ease of casting the location straight to a colour location and catches error when
		//there is a special location trying to be upgraded.
		try
		{
			castedLocationToBeDowngraded = (ColourLocation)(location[locationToBeDowngraded]); 
		}
		catch(ClassCastException e)
		{
			//If the location is a specialLocation, print cannot upgrade this location.
			System.out.println("Error: Cannot upgrade this location!");
			return false;
		}
		boolean result = false;

		if(location[locationToBeDowngraded] instanceof ColourLocation)
		{
			//If the number of same colour locations is equals to the number of same colour locations in the player's owned location array
			if(castedLocationToBeDowngraded.GetNumberOfHotels()>0 || castedLocationToBeDowngraded.GetNumberOfHouses()>0)
			{
					castedLocationToBeDowngraded.DowngradeProperty();
					currPlayer.SetMoney(castedLocationToBeDowngraded.GetHouseCost()/2); //Sets the player money using player array
					result = true;
			}
			//If there is no houses or hotels on this location
			else 
				System.out.println("Error: You must have at least one property to downgrade this location!");
		}
		return result;
	}
	
	public boolean MortgageLocation(Player[] playerArray,int playerCounter, int locationToBeMortgaged)
	{
		Player currPlayer = playerArray[playerCounter];
		Location[] location = currPlayer.GetPropertyOwned(); 
		
		ColourLocation colourcastedLocationToMortgaged = null;
		//Casted the location to colour location to prevent having to cast it everytime. Dummy value inserted into variable
		if(location[locationToBeMortgaged] instanceof ColourLocation)
			colourcastedLocationToMortgaged = (ColourLocation)(location[locationToBeMortgaged]);

		
		//Try casting to colour location
		try
		{
			colourcastedLocationToMortgaged = (ColourLocation)(location[locationToBeMortgaged]); 
		}
		//If it catches an error, it means the location is a special location.
		catch(ClassCastException e)
		{
		}
		
		boolean result = false;

		//If the location to be mortgaged is an instance of the colour location class
		if(location[locationToBeMortgaged] instanceof ColourLocation)
		{
			//If the number hotels and houses is equals to 0, allow them to mortgage
			if(colourcastedLocationToMortgaged.GetNumberOfHotels() == 0 || colourcastedLocationToMortgaged.GetNumberOfHouses() == 0)
			{
				colourcastedLocationToMortgaged.MortgageProperty();;
				currPlayer.SetMoney(colourcastedLocationToMortgaged.GetMortgage()); //Sets the player money using player array
				currPlayer.RemoveLocationFromOwned(locationToBeMortgaged);
				result = true;
			}
			//If there is location, allow them the chose of selling all their land improvements
			else
			{
				System.out.println("Error: You must first sell all your improvements, would you like to sell them? (Y/N)");
				char removingLandImprovements = input.next().charAt(0);
				removingLandImprovements =  java.lang.Character.toLowerCase(removingLandImprovements);
				if(removingLandImprovements == 'y')
				{
					while(colourcastedLocationToMortgaged.GetNumberOfHotels() != 0 && colourcastedLocationToMortgaged.GetNumberOfHouses() != 0)
					{
						//Uses the downgrading location in a while loop to sell all the locations
						this.DowngradingLocation(playerArray, playerCounter, locationToBeMortgaged, location[locationToBeMortgaged].GetColor());
					}
				}
				else
					result = false;
			}
		}
		//If the location to be mortgaged is an instance of the special location class
		else if(location[locationToBeMortgaged] instanceof SpecialLocation)
		{
			colourcastedLocationToMortgaged.MortgageProperty();;
			currPlayer.SetMoney(((SpecialLocation)(location[locationToBeMortgaged])).GetMortgage()); //Sets the player money using player array
			currPlayer.RemoveLocationFromOwned(locationToBeMortgaged);
			result = true;
		}
		return result;
	}
}
