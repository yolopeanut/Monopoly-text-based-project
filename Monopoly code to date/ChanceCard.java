//DONE BY BRANDON
public class ChanceCard extends Cards{
	String euro = DisplayScreen.EURO;
	
	
	public ChanceCard()
	{
		//Default constructor
	}
	
	//Stores the information taken from the text file into the super class
	public ChanceCard(String specialInfo)
	{
		super(specialInfo);
	}


	public void ActionCard(Player[] playerArray, int playerCounter, Cards card)
	{
		Player currPlayer = playerArray[playerCounter];
		//=============================================MOVEMENT CARDS===============================================//
		
		if (card.GetSpecialInfo().equals("Go to jail. Move directly to jail. Do not pass Go. Do not collect 200"))
		{
			System.out.println(card.GetSpecialInfo());
			
			//Sets player position to jail
			currPlayer.SetPos(10); 
			
			//Access method in player class to put player in jail
			currPlayer.GoingToJail();
			
		}
		
		else if(card.GetSpecialInfo().equals("Advance to Go"))
		{
			System.out.println(card.GetSpecialInfo());
			
			//Sets player position to go
			currPlayer.SetPos(0);
			
			//Sets player money
			currPlayer.SetMoney(200);

			System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );
		}
		
		else if (card.GetSpecialInfo().equals("Advance to Pall Mall. If you pass Go collection 200"))
		{
			System.out.println(card.GetSpecialInfo());
			
			//If the current player's position is greater than 19, they will pass go to reach Pall Mall
			if(currPlayer.GetPos()>=19)
			{
				
				//Sets player money
				currPlayer.SetMoney(200);
				
				System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		
			}
			
			//Sets player position to Pall Mall
			currPlayer.SetPos(19);
		}
		
		else if (card.GetSpecialInfo().equals("Take a trip to Marylebone Station and if you pass Go collect 200"))
		{
			System.out.println(card.GetSpecialInfo());
			
			//If the current player's position is greater than 15, they will pass go to reach Marylebone Station
			if(currPlayer.GetPos()>=15)
			{
				
				//Sets player money
				currPlayer.SetMoney(200);
				
				System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );
				
			}
			
			//Sets player position to Marylebone Station
			currPlayer.SetPos(15);
		}
		
		else if (card.GetSpecialInfo().equals("Advance to Trafalgar Square. If you pass Go collect 200"))
		{
			System.out.println(card.GetSpecialInfo());
			//If the current player's position is greater than 24, they will pass go to reach Trafalgar Square
			if(currPlayer.GetPos()>=24)
			{
				//Sets player money
				currPlayer.SetMoney(200);
				System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		
			}
			//Sets player position to Trafalgar Square
			currPlayer.SetPos(24);
		}
		
		else if (card.GetSpecialInfo().equals("Advance to Mayfair"))
		{
			//Sets player money
			System.out.println(card.GetSpecialInfo());
			
			//Sets player position to Mayfair
			currPlayer.SetPos(39);
		}
		else if (card.GetSpecialInfo().equals("Go back three spaces"))
		{
			System.out.println(card.GetSpecialInfo());
			
			//Gets players position and minus 3 to it. Then sets their position to that value
			currPlayer.SetPos(currPlayer.GetPos()-3);
		}
		//==================================================PAYMENT CARDS===============================================//
		
		if(card.GetSpecialInfo().equals("Make general repairs on all of your houses. For each house pay 25. For each hotel pay 100"))
		{
			System.out.println(card.GetSpecialInfo());
			int numOfHouses = 0; //Variable to keep track of number of houses a player has
			int numOfHotels = 0; //Variable to keep track of number of hotels a player has
			Location[] propertyOwnedArray = currPlayer.GetPropertyOwned(); //Variable to store the array of playerOwnedLocations
			
			//For loop to check through all the properties in the array
			for(int i = 0; i < propertyOwnedArray.length; i++)
			{
				//If the location is an instance of colour Location
				if(propertyOwnedArray[i] instanceof ColourLocation)
				{
					//If there is a property built, the number of houses is the number of houses variable plus the number of houses at that property
					if(((ColourLocation)propertyOwnedArray[i]).GetNumberOfHouses()>0)
						numOfHouses = numOfHouses + ((ColourLocation)propertyOwnedArray[i]).GetNumberOfHouses();
					
					//If there is a property built, the number of hotels is the number of hotels variable plus the number of hotels at that property
					if (((ColourLocation)propertyOwnedArray[i]).GetNumberOfHotels()>0)
						numOfHotels = numOfHotels + ((ColourLocation)propertyOwnedArray[i]).GetNumberOfHouses();
				}
			}
			//Sets the player money based on the number of hotels and houses owned.
			currPlayer.SetMoney(-(numOfHotels)*100 -(numOfHouses)*25);
			System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		
		}
		
		else if (card.GetSpecialInfo().equals("You are assessed for street repairs: 40 per house, 115 per hotel"))
		{
			System.out.println(card.GetSpecialInfo());
			int numOfHouses = 0; //Variable to keep track of number of houses a player has
			int numOfHotels = 0; //Variable to keep track of number of hotels a player has
			Location[] propertyOwnedArray = currPlayer.GetPropertyOwned(); //Variable to store the array of playerOwnedLocations
			
			for(int i = 0; i<propertyOwnedArray.length; i++)
			{
				if (propertyOwnedArray[i] instanceof ColourLocation)
				{
					//If there is a property built, the number of houses is the number of houses variable plus the number of houses at that property
					if(((ColourLocation)propertyOwnedArray[i]).GetNumberOfHouses()>0)
						numOfHouses = numOfHouses + ((ColourLocation)propertyOwnedArray[i]).GetNumberOfHouses();
					
					//If there is a property built, the number of hotels is the number of hotels variable plus the number of hotels at that property
					if (((ColourLocation)propertyOwnedArray[i]).GetNumberOfHotels()>0)
						numOfHotels = numOfHotels + ((ColourLocation)propertyOwnedArray[i]).GetNumberOfHouses();
				}
			}
			//Sets the player money based on the number of hotels and houses owned.
			currPlayer.SetMoney(-(numOfHouses)*40 -(numOfHotels)*115);
			System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		

		}
		
		else if (card.GetSpecialInfo().equals("Pay school fees of 150"))
		{
			System.out.println(card.GetSpecialInfo());
			
			//Sets the player money
			currPlayer.SetMoney(-150);
			
			System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		
		}
		
		else if (card.GetSpecialInfo().equals("Drunk in charge fine 20"))
		{
			System.out.println(card.GetSpecialInfo());
			
			//Sets the player money
			currPlayer.SetMoney(-20);
			
			System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		
		}
		
		else if (card.GetSpecialInfo().equals("Speeding fine 15"))
		{
			System.out.println(card.GetSpecialInfo());
			
			//Sets the player money
			currPlayer.SetMoney(-15);
			
			System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		
		}
		//================================================ RECEIVING CARDS ===============================================//
		else if (card.GetSpecialInfo().equals("Your building loan matures. Receive 150"))
		{
			System.out.println(card.GetSpecialInfo());
			
			//Sets the player money
			currPlayer.SetMoney(+150);
			
			System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		

		}
		else if (card.GetSpecialInfo().equals("You have won a crossword competition. Collect 100"))
		{
			System.out.println(card.GetSpecialInfo());
			
			//Sets the player money
			currPlayer.SetMoney(+100);
			
			System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		

		}
		else if (card.GetSpecialInfo().equals("Bank pays you dividend of 50"))
		{
			System.out.println(card.GetSpecialInfo());
			
			//Sets the player money
			currPlayer.SetMoney(+50);
			
			System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		

		}
		//================================================== OTHER CARDS ===============================================//
		else if (card.GetSpecialInfo().equals("Get out of jail free. This card may be kept until needed or sold"))
		{
			System.out.println(card.GetSpecialInfo());
			
			//Uses the method in player class to store the get out of jail free card
			currPlayer.GetOutOfJailFreeCard();
		}
		//=============================================================================================================//


	}
}
