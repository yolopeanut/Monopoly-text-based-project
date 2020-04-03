//Done by BRANDON TAN
public class DisplayScreen {
	public static final String EURO = "\u20ac";
	public DisplayScreen()
	{
		//Blank constructor
	}

	//==================================HELPER METHODS================================================//
	public void DisplayDashes()
	{
		String dashes = "---------------------------------";
		System.out.println(dashes + dashes + dashes + dashes + dashes + dashes );

	}
	public String DisplayMonopolySpaces()
	{
		String spaces = "                       ";
		return spaces;
	}
	public void DisplaySpaces()
	{
		String spaces = "                           ";
		System.out.print(spaces + spaces + spaces + spaces + spaces + spaces);
	}
	public void DisplayDashandSpace()
	{
		String dashes = "------------------";
		String spaces = "                           ";
		System.out.println(dashes + spaces + spaces + spaces + spaces + spaces + spaces + dashes);
	}

	//Align text in centre of 16 characters with text as parameters
	public void AlignTextCenter16(String text)
	{		
		int textLengthMid =(int) text.length()/2;
		int distanceFromMid = 8 - textLengthMid;

		for (int i = 0; i<distanceFromMid; i++)
			System.out.print(" ");
		System.out.print(text);
		for (int i = 0; i< 16 - (text.length() + distanceFromMid); i++)
			System.out.print(" ");

	}

	//Following helper methods help to display the grid, with the align helper method to center all text.
	public void GetNameOfLocation(Location[] location, int i)
	{
		if(location[i] instanceof BlankLocation)
			this.AlignTextCenter16(String.valueOf(((BlankLocation)location[i]).GetName()));
		else if(location[i] instanceof ColourLocation)
			this.AlignTextCenter16(String.valueOf(((ColourLocation)location[i]).GetName()));
		else if(location[i] instanceof SpecialLocation)
			this.AlignTextCenter16(String.valueOf(((SpecialLocation)location[i]).GetName()));
	}
	public void GetPriceOrSpecialInfo(Location[] location, int i)
	{
		if (location[i] instanceof BlankLocation)
			this.AlignTextCenter16(String.valueOf(((BlankLocation)location[i]).GetSpecialInfo()));
		else if(location[i] instanceof ColourLocation)
			this.AlignTextCenter16(DisplayScreen.EURO + String.valueOf(((ColourLocation)location[i]).GetPrice()));
		else if(location[i] instanceof SpecialLocation)
			this.AlignTextCenter16(DisplayScreen.EURO + String.valueOf(((SpecialLocation)location[i]).GetPrice()));
	}
	public void GetColourOfLocation(Location[] location, int i)
	{
		if(location[i] instanceof BlankLocation)
			this.AlignTextCenter16(" ");
		else if(location[i] instanceof ColourLocation)
			this.AlignTextCenter16(String.valueOf(((ColourLocation)location[i]).GetColor()));
		else if(location[i] instanceof SpecialLocation)
			this.AlignTextCenter16(" ");
	}
	public void GetHousingOfLocation(Location[] location, int i)
	{
		if(location[i] instanceof BlankLocation)
			this.AlignTextCenter16(" ");
		else if(location[i] instanceof ColourLocation)
		{
			if (((ColourLocation)location[i]).GetNumberOfHotels() == 1)
				this.AlignTextCenter16("[HOTEL]");
			else if (((ColourLocation)location[i]).GetNumberOfHouses() == 0)
				this.AlignTextCenter16(" ");
			else if (((ColourLocation)location[i]).GetNumberOfHouses() == 1)
				this.AlignTextCenter16("H");
			else if (((ColourLocation)location[i]).GetNumberOfHouses() == 2)
				this.AlignTextCenter16("HH");
			else if (((ColourLocation)location[i]).GetNumberOfHouses() == 3)
				this.AlignTextCenter16("HHH");
			else if (((ColourLocation)location[i]).GetNumberOfHouses() == 4)
				this.AlignTextCenter16("HHHH");
		}
		else if(location[i] instanceof SpecialLocation)
			this.AlignTextCenter16(" ");
	}

	public void GetPosOfPlayer(Location[] location, Player[] player, int a)
	{

		String playerSymbol = "";
		for (int j = 0; j< player.length; j++)
		{

			int pos = player[j].GetPos();
			if (pos == location[a].GetPosition())
			{
				playerSymbol = playerSymbol + player[j].GetSymbol(); 
			}
		}
		this.AlignTextCenter16(playerSymbol);

	}


	//=================================================================================================//


	public void PrintMainScreen()
	{
		//Prints monopoly welcome screen.
		System.out.println("===========================");
		System.out.println("    WELCOME TO MONOPOLY    ");
		System.out.println("===========================");
		System.out.println("Enter the number of players playing!");
		System.out.print("Number of players: ");
	}

	public void PrintMonopolyBoardScreen(Location[] north, Location[] south, Location[] east, Location[] west, Player[] player)
	{

		//Prints the monopoly board starting from the north, new line, east, west, new line, repeat until south.
		this.DisplayDashes();

		//===================================NORTH SIDE OF BOARD=======================================//
		//=================================== COLOUR OF LOCATION ======================================//
		for(int i = 0; i<north.length; i++)
		{
			System.out.print("|");
			this.GetColourOfLocation(north, i); //Calls the method to get colour for West array
			System.out.print("|");	
		}		
		//=============================================================================================//
		
		System.out.println();
		
		//===================================== NAME OF LOCATION ======================================//
		for (int i = 0; i<north.length; i++)
		{
			System.out.print("|");
			this.GetNameOfLocation(north, i); //Calls the method to get name of location for North array
			System.out.print("|");	
		}
		//=============================================================================================//
		
		System.out.println();

		//===================================SPECIAL INFO OR PRICE======================================//
		for (int i = 0; i<north.length; i++)
		{
			System.out.print("|");
			GetPriceOrSpecialInfo(north, i); //Calls the method to get the price and special info for North array
			System.out.print("|");
		}
		//=============================================================================================//
		
		System.out.println();

		//====================================HOUSING OF LOCATION====================================//

		for (int i = 0; i<north.length; i++)
		{
			System.out.print("|");
			GetHousingOfLocation(north, i);//Calls the method to get SpecialInfo for West array
			System.out.print("|");	
		}
		//=============================================================================================//
		
		System.out.println();


		//====================================PLAYER POSITION=========================================//
		for(int i = 0; i<north.length; i++)
		{
			System.out.print("|");
			this.GetPosOfPlayer(north, player, i);
			System.out.print("|");
		}

		//=============================================================================================//

		System.out.println();

		this.DisplayDashes();

		//East and West side printing

		//For loop, 9 is the length of the east and west sides.
		for(int i = 8; i>=0; i--)
		{
			//=================================== COLOUR OF LOCATION ======================================//

			System.out.print("|");
			this.GetColourOfLocation(west, i); //Calls the method to get colour for West array
			System.out.print("|");
			if(i == 4)
				System.out.print(this.DisplayMonopolySpaces() + "_______       _______  __________  ______        ____  __________  ___________   __________  _____    ____      ____" + this.DisplayMonopolySpaces());
			else if(i == 3)
				System.out.print(this.DisplayMonopolySpaces() + "|  |     \\_/     |  | |  |    |  | |  |    \\  \\  |  | |  |    |  | |  |         |  |    |  | |   |______  |    |    " + this.DisplayMonopolySpaces());
			else
				this.DisplaySpaces();
			System.out.print("|");
			this.GetColourOfLocation(east, i); //Calls the method to get colour for East array
			System.out.println("|");	

			//=============================================================================================//

			//===================================== NAME OF LOCATION ======================================//

			System.out.print("|");
			this.GetNameOfLocation(west, i);//Calls the method to get name of location for West array
			System.out.print("|");
			if(i == 4)
				System.out.print(this.DisplayMonopolySpaces() + "|      \\     /      | |   ____   | |     \\       |  | |   ____   | |   ____   \\ |   ____   | |   |    \\   \\    /   /" + this.DisplayMonopolySpaces());
			else if(i == 3)
				System.out.print(this.DisplayMonopolySpaces() + "|  |             |  | |  |____|  | |  |     \\  \\_|  | |  |____|  | |  |         |  |____|  | |          | |    |    " + this.DisplayMonopolySpaces());
			else
				this.DisplaySpaces();
			System.out.print("|");
			this.GetNameOfLocation(east, i);//Calls the method to get name of location for East array
			System.out.println("|");

			//=============================================================================================//


			//===================================SPECIAL INFO OR PRICE======================================//

			System.out.print("|");
			GetPriceOrSpecialInfo(west, i);//Calls the method to get SpecialInfo for West array
			System.out.print("|");
			if(i == 4)
				System.out.print(this.DisplayMonopolySpaces() + "|   __  \\   /  __   | |  |    |  | |  |\\  \\      |  | |  |    |  | |  |    \\   ||  |    |  | |   |     \\   \\  /   / " + this.DisplayMonopolySpaces());
			else if(i == 3)
				System.out.print(this.DisplayMonopolySpaces() + "|__|             |__| |__________| |__|      \\______| |__________| |__|         |__________| |__________| |____|    " + this.DisplayMonopolySpaces());
			else
				this.DisplaySpaces();
			System.out.print("|");
			GetPriceOrSpecialInfo(east, i);//Calls the method to get SpecialInfo for East array
			System.out.println("|");

			//=============================================================================================//

			//====================================HOUSING OF LOCATION====================================//
			System.out.print("|");
			GetHousingOfLocation(west, i);//Calls the method to get Housing for West array
			System.out.print("|");
			if(i == 4)
				System.out.print(this.DisplayMonopolySpaces() + "|  |  \\  \\ /  /  |  | |  |    |  | |  | \\  \\     |  | |  |    |  | |  |____/  / |  |    |  | |   |      \\   \\/   /  " + this.DisplayMonopolySpaces());
			else
				this.DisplaySpaces();
			System.out.print("|");
			GetHousingOfLocation(east, i);//Calls the method to get Housing for East array
			System.out.println("|");

			//=============================================================================================//

			//====================================PLAYER POSITION=========================================//

			System.out.print("|");
			this.GetPosOfPlayer(west, player, i);
			System.out.print("|");
			if(i == 4)
				System.out.print(this.DisplayMonopolySpaces() + "|  |   \\     /   |  | |  |    |  | |  |  \\  \\    |  | |  |    |  | |  _______/  |  |    |  | |   |       \\      /   " + this.DisplayMonopolySpaces());
			else
				this.DisplaySpaces();

			System.out.print("|");
			this.GetPosOfPlayer(east, player, i);
			System.out.println("|");

			//=============================================================================================//


			//If statement to make sure that it prints the line at the top of south locations
			if (i == 4)
				System.out.println("------------------" + this.DisplayMonopolySpaces() + "|  |    \\   /    |  | |  |    |  | |  |   \\  \\   |  | |  |    |  | |  |         |  |    |  | |   |        |    |    " + this.DisplayMonopolySpaces() + "------------------");
			else if( i != 0)
				this.DisplayDashandSpace();
			else
				this.DisplayDashes();

		}
		////////Colour of Location////////////////////////////////////////////////////////////////////////////
		for(int i = south.length-1; i>=0; i--)
		{
			System.out.print("|");
			this.GetColourOfLocation(south, i); //Calls the method to get colour for West array
			System.out.print("|");	
		}		
		////////////////////////////////////////////////////////////////////////////////////
		System.out.println();
		//////////Name of Location//////////////////////////////////////////////////////////////////////////
		for (int i = south.length-1; i>=0; i--)
		{
			System.out.print("|");
			this.GetNameOfLocation(south, i); //Calls the method to get name of location for North array
			System.out.print("|");	
		}
		////////////////////////////////////////////////////////////////////////////////////
		System.out.println();

		////////////Special Info or Price////////////////////////////////////////////////////////////////////////
		for (int i = south.length-1; i>=0; i--)
		{
			System.out.print("|");
			GetPriceOrSpecialInfo(south, i); //Calls the method to get the price and special info for North array
			System.out.print("|");
		}
		////////////////////////////////////////////////////////////////////////////////////
		System.out.println();

		/////////////Housing Of location//////////////////////////////////////////////////////////////////////////
		for (int i = south.length-1; i>=0; i--)
		{
			System.out.print("|");
			GetHousingOfLocation(south, i);//Calls the method to get SpecialInfo for West array
			System.out.print("|");	
		}
		////////////////////////////////////////////////////////////////////////////////////
		System.out.println();

		//Player position////////////////////////////////////////////////////////////////////////////////////////
		for(int i = south.length-1; i>=0; i--)
		{
			System.out.print("|");
			this.GetPosOfPlayer(south, player, i);
			System.out.print("|");
		}
		////////////////////////////////////////////////////////////////////////////////////////
		System.out.println();

		this.DisplayDashes();
	}

	public void PrintGameScreen(Player currPlayer)
	{
		//Prints monopoly welcome screen.
		System.out.println("===========================");
		System.out.println("     CHOOSE YOUR ACTION    ");
		System.out.println("===========================");
		//if player is in debt then don't print 1,2,3
		if(currPlayer.GetIsInDebt() == false)
		{
			System.out.println("1. ROLL YOUR DICE");
			System.out.println("2. BUY THE LOCATION YOU ARE ON");
			System.out.println("3. UPGRADE PROPERTY");
		}
		System.out.println("4. DOWNGRADE PROPERTY");
		System.out.println("5. VIEW ALL PROPERTIES");
		System.out.println("6. MORTGAGE PROPERTY");
		System.out.println("7. DISPLAY RULES");
		System.out.println("8. END TURN");
		
		//If player has get out of jail free card
		if(currPlayer.GetGetOutOfJailFree() == true)
		{
			System.out.println("9.SELL YOUR GET OUT OF JAIL FREE CARD");
		}
	}
		




		/* Method takes in playerArray and playerCounter and prints out all the properties owned by the current player */
		public void ShowAllPropertiesOwned(Player[] playerArray, int playerCounter,String option)
		{
			Player currPlayer = playerArray[playerCounter];
			for(int i = 0; i<(currPlayer.GetPropertyOwned()).length; i++) //For loop using array length of property owned in player class
			{
				if ((currPlayer.GetPropertyOwned())[i] == null)
					System.out.print("");
				else if ((currPlayer.GetPropertyOwned())[i] instanceof ColourLocation)
					System.out.println(i+1 + "." + (currPlayer.GetPropertyOwned())[i].GetName() + " - " + DisplayScreen.EURO + ((ColourLocation)(currPlayer.GetPropertyOwned()[i])).GetHouseCost());
				else if ((currPlayer.GetPropertyOwned())[i] instanceof SpecialLocation && option.equals("Upgrade"))
					System.out.println(i+1 + "." + (currPlayer.GetPropertyOwned())[i].GetName() + " - Cannot be upgraded!");
				else if ((currPlayer.GetPropertyOwned())[i] instanceof SpecialLocation && option.equals("Downgrade"))
					System.out.println(i+1 + "." + (currPlayer.GetPropertyOwned())[i].GetName() + " - Cannot be Downgraded!");
				else if ((currPlayer.GetPropertyOwned())[i] instanceof SpecialLocation && option.equals("Mortgage"))
					System.out.println(i+1 + "." + (currPlayer.GetPropertyOwned())[i].GetName() + " - " + DisplayScreen.EURO + ((SpecialLocation)(currPlayer.GetPropertyOwned()[i])).GetMortgage());
			}
		}
		

		/* Method which takes in the location array and player array and prints out all the locations */
		public void ShowAllProperties(Location[] location, Player[] playerArray)
		{

			for(int i = 0; i<location.length; i++)
			{
				if(location[i] instanceof ColourLocation)
					if (((ColourLocation)location[i]).GetIsOwned() == true)
						System.out.printf("%-15s %-20s -IS OWNED BY - " + playerArray[((ColourLocation)location[i]).GetOwnerOfProperty()].GetName() + "\n", location[i].GetColor(),location[i].GetName());
					else 
						System.out.printf("%-15s %-20s -NOT OWNED \n", location[i].GetColor(), location[i].GetName());
				if(location[i] instanceof SpecialLocation)
					if (((SpecialLocation)location[i]).GetIsOwned() == true)
						System.out.printf("%-15s %-20s -IS OWNED BY - " + playerArray[((SpecialLocation)location[i]).GetOwnerOfProperty()].GetName() + "\n", "NO COLOUR",location[i].GetName());
					else
						System.out.printf("%-15s %-20s -NOT OWNED \n", "NO COLOUR", location[i].GetName());
			}
		}

		//Method which takes displays the headers for the method above this
		public void DisplayHeaderForShowAllPropertiesMethod(String direction)
		{
			if(direction.equals("NORTH"))
			{
				System.out.printf("%15s \n", "NAME OF LOCATION");
				System.out.println("============================================================");
				System.out.printf("%33s \n", "NORTH");
				System.out.println("============================================================");
				System.out.println("============================================================");
				System.out.printf("%-15s %-15s %14s \n", "COLOUR", "NAME", "IS OWNED");
				System.out.println("============================================================");
			}
			else if(direction.equals("EAST"))
			{
				System.out.println("============================================================");
				System.out.printf("%33s \n", "EAST");
				System.out.println("============================================================");
				System.out.println("============================================================");
				System.out.printf("%-15s %-15s %14s \n", "COLOUR", "NAME", "IS OWNED");
				System.out.println("============================================================");
			}
			else if(direction.equals("SOUTH"))
			{
				System.out.println("============================================================");
				System.out.printf("%33s \n", "SOUTH");
				System.out.println("============================================================");
				System.out.println("============================================================");
				System.out.printf("%-15s %-15s %14s \n", "COLOUR", "NAME", "IS OWNED");
				System.out.println("============================================================");
			}
			else if(direction.equals("WEST"))
			{
				System.out.println("============================================================");
				System.out.printf("%33s \n", "WEST");
				System.out.println("============================================================");
				System.out.println("============================================================");
				System.out.printf("%-15s %-15s %14s \n", "COLOUR", "NAME", "IS OWNED");
				System.out.println("============================================================");
			}
			

		}

		

	}

