import java.util.Scanner;

public class MonopolyMain {
	public static void main(String[] args)
	{
		//Variables

		///////////////////////////////////////////////////////////////////////////////////////////
		// Variable to import Location using filename of locations
		Cards card = new Cards();
		Location location = new Location();
		DisplayScreen display = new DisplayScreen();
		Scanner input = new Scanner(System.in);
		Rules rule = new Rules();
		///////////////////////////////////////////////////////////////////////////////////////////
		Player[] playerArray;			//Array to keep the array of players.
		int numOfPlayers = 0;			//Dummy value to initialize
		int userOption;					
		int minOption;					//Min option to limit the options of players when selecting from game menu
		int maxOption;					//Max option to limit the options of players when selecting from game menu
		int locationChosen;				//Variable to get input of current player for which property to upgrade, downgrade or mortgage
		int playerCounter = 0;			//To keep track of the current player
		boolean diceRoll;				//To keep track if the player who rolled has rolled a doubles - if true, has rolled a doubles, if false - has not.
		boolean hasRolled = false;		//Has rolled keeps track if the player has rolled once, if it is true, do not allow to roll again
		//////////////////////////////////////////////////////////////////////////////////
		//INITIALIZER
		card.AddCard("Cards.txt");
		/////////////////////////////////////////////////////////////////////////////////
		display.PrintMainScreen();
		numOfPlayers = input.nextInt(); //Stores the number of players in variable

		while(numOfPlayers<2)
		{
			System.out.println("Error: The number of players cannot be less than 2!");
			numOfPlayers = input.nextInt();
		} 
		
		//Makes a new player instance with the number of players to initialize the array size for players
		Player player = new Player(numOfPlayers);
		
		//Stores the player array in a variable to be used easily
		playerArray = player.GetPlayerArray(); 
		
		//Eats a line
		input.nextLine(); 


		//For loop to add a new player object for the number of players in the game.
		for (int i = 0; i<(numOfPlayers); i++)
		{
			String name;
			System.out.println("What is player " + (i+1) + "'s name");
			name = input.nextLine();
			playerArray[i] = new Player(name);
			if (i == 0)
			{
				playerArray[i].SetSymbol("#");
			}
			else if (i == 1)
			{
				playerArray[i].SetSymbol("$");
			}
			else if (i == 2)
			{
				playerArray[i].SetSymbol("@");
			}
			else if (i == 3)
			{
				playerArray[i].SetSymbol("%");
			}
			System.out.println("Player " + (i+1) + ", you are the '" + playerArray[i].GetSymbol() + "' symbol!");
		}

		//Imports locations from variable with text file name
		location.AddLocation("MonopolyLocations.txt");     
		mainLoop: while(true)
		{
			//Prints the monopoly board screen taking in the 4 arrays for directions
			display.PrintMonopolyBoardScreen(location.GetMonopolyNorth(), location.GetMonopolySouth(), location.GetMonopolyEast(),location.GetMonopolyWest(), player.GetPlayerArray());
			
			//Prints which players turn it is.
			System.out.println("Player "+ (playerCounter+1)+ " ("+ playerArray[playerCounter].GetSymbol() + ") : " + playerArray[playerCounter].GetName() + " it is your turn");
			
			//Prints the game screen for players to choose the options. Takes in current player to check if they are bankrupt or such.
			display.PrintGameScreen(playerArray[playerCounter]);

			System.out.println("CURRENT BALANCE: " + DisplayScreen.EURO + playerArray[playerCounter].GetMoney());
			
			
			
			////////////////////////////////////////////////////////////
			//Section to determine the limits of the user choices
			if(playerArray[playerCounter].GetIsInDebt() == true)
				minOption = 4;
			else
				minOption = 1;

			if(playerArray[playerCounter].GetGetOutOfJailFree()== true)
				maxOption = 9;
			else
				maxOption = 8;
			////////////////////////////////////////////////////////////
			System.out.print("Your choice: ");
			userOption = input.nextInt();

			//Loops while user chooses something out of bounds
			while(userOption<minOption || userOption>maxOption)
			{
				//If the user chooses something out of the listed options
				if (userOption<minOption || userOption>maxOption)
				{
					display.PrintGameScreen(playerArray[playerCounter]);
					System.out.println("Error: Choose something from the list above!");
				}
				userOption = input.nextInt();

			}
			
			//If Player chooses 1 for game options, roll dice
			if(userOption == 1)
			{
				//If user hasn't rolled in their turn
				if(hasRolled == false)
				{
					//If player is in jail
					if(playerArray[playerCounter].GetIsInJail() == true)
					{
						System.out.println("Would you like to pay " + DisplayScreen.EURO + "50" +" to get out of jail? (Y/N)");
						char userJailChoice = input.next().charAt(0);
						userJailChoice = java.lang.Character.toLowerCase(userJailChoice);
						//If user chooses to get out of jail
						if(userJailChoice == 'y')
						{
							//Method to release player from jail
							playerArray[playerCounter].ReleaseFromJail();
							
							//Method to deduct money of current player
							playerArray[playerCounter].SetMoney(-50);
						}
					}
					//Method to dice roll, found in player class - takes in current player, player array, community card array and chance card array
					diceRoll = playerArray[playerCounter].RollDice(playerArray, playerCounter, card.GetCommCardArray(), card.GetChanceCardArray());
					
					//If dice roll equals true, player rolled doubles, allow them to roll again.
					if(diceRoll == true)
						hasRolled = false;
					//If dice roll equals false, player didn't roll doubles, cannot roll again
					else
						hasRolled = true;
				}
				//If user tries to roll when already rolled
				else
					System.out.println("You have already rolled!");

				

			}
			//If the user chooses to buy the location they are on
			else if (userOption == 2)
			{
				//If the method buyingLocation returns true, means the location was successfully bought.
				//Error handling is done in the buying location method.
				if(location.BuyingLocation(playerArray,playerCounter) == true)
					System.out.println("Successfully bought!");

			}

			//If the user chooses to upgrade their property
			else if (userOption == 3)
			{
				//If the current player has more than 0 property
				if(playerArray[playerCounter].GetNumOfPropertyOwned()>0)
				{
					System.out.println("Choose which property you want to upgrade from the list below (Note: must have all properties of the same colour)");

					//Method from display class to show all the current players properties owned
					display.ShowAllPropertiesOwned(playerArray, playerCounter,"Upgrade");
					locationChosen = input.nextInt(); 
					
					//UpgradeSuccessful variable containing if the location upgrade was successful - Error handling is done inside the method.
					boolean upgradeSuccessful = location.UpgradingLocation(playerArray, playerCounter, locationChosen-1,(playerArray[playerCounter].GetPropertyOwned()[locationChosen-1].GetColor()));
				
					if(upgradeSuccessful == true)
					{
						System.out.println("Property has been upgraded successfully!");
					}
				}
				//If user tries to upgrade with no property
				else 
					System.out.println("Error: No property is owned");
			}

			//If the user chooses 4, down-grade property that they select
			else if (userOption == 4)
			{
				//If the current player has more than 0 property
				if(playerArray[playerCounter].GetNumOfPropertyOwned()>0)
				{
					System.out.println("Choose which property you want to downgrade from the list below (Note: Property will return half of the cost of building)");

					//Method from display class to show all the current players properties owned
					display.ShowAllPropertiesOwned(playerArray, playerCounter, "Downgrade");
					locationChosen = input.nextInt(); 
					
					//DowngradeSuccessful variable containing if the location down-grade was successful - Error handling is done inside the method.
					boolean DowngradeSuccessful = location.DowngradingLocation(playerArray, playerCounter, locationChosen-1,(playerArray[playerCounter].GetPropertyOwned()[locationChosen-1].GetColor()));
					
					if(DowngradeSuccessful == true)
					{
						System.out.println("Property has been Downgraded successfully!");
					}
				}
				//If user tries to downgrade with no property
				else 
					System.out.println("Error: No property is owned");

			}

			//If user chooses to display all properties in the game
			else if (userOption == 5)
			{
				//Method to display the header for NORTH array
				display.DisplayHeaderForShowAllPropertiesMethod("NORTH");
				//Method to display the properties for NORTH array
				display.ShowAllProperties(location.GetMonopolyNorth(), playerArray);

				//Method to display the header for EAST array
				display.DisplayHeaderForShowAllPropertiesMethod("EAST");
				//Method to display the properties for EAST array
				display.ShowAllProperties(location.GetMonopolyEast(), playerArray);

				//Method to display the header for SOUTH array
				display.DisplayHeaderForShowAllPropertiesMethod("SOUTH");
				//Method to display the properties for SOUTH array
				display.ShowAllProperties(location.GetMonopolySouth(), playerArray);

				//Method to display the header for WEST array
				display.DisplayHeaderForShowAllPropertiesMethod("WEST");
				//Method to display the properties for WEST array
				display.ShowAllProperties(location.GetMonopolyWest(), playerArray);
				
				System.out.println("============================================================");
				System.out.println();
				System.out.println("============================================================");
			}

			//If the user chooses 6, mortgage the property that they select
			else if(userOption == 6)
			{
				//If the current player has more than 0 property
				if(playerArray[playerCounter].GetNumOfPropertyOwned()>0)
				{
					System.out.println("Choose which property you want to mortgage from the list below");
					//Method to display all properties owned 
					display.ShowAllPropertiesOwned(playerArray, playerCounter, "Mortgage");
					
					locationChosen = input.nextInt(); 
					if(location.MortgageLocation(playerArray, playerCounter, locationChosen-1) == true)
						System.out.println("Successfully Mortgaged!");
				}
				else 
					System.out.println("Error: No property is owned");
			}
			//If user chooses 7, display rules
			else if (userOption == 7)
			{
				new DisplayRule().DisplayRules();
			}
			
			//If user chooses 8, end their turn
			else if (userOption == 8)
			{
				playerCounter++;
				//If it is after the last players turn, set the turn back to the first player
				if(playerArray.length == playerCounter)
					playerCounter = 0;

				//If the player is bankrupt
				while(playerArray[playerCounter].GetIsBankrupt() == true)
				{
					playerCounter++;
					if(playerArray.length == playerCounter)
						playerCounter = 0;
				}
				hasRolled = false;
			}
			
			//If user chooses 9, sell the jail card
			else if (userOption == 9)
			{
				int counter = 1;
				System.out.println("Which player would you like to sell it to?");
				for(int i = 0; i< playerArray.length; i++)
				{
					if(playerArray[i].GetIsBankrupt() == false)
					{
						System.out.println((counter) + ". Player " + (i+1));
						counter++;
					}
				}
				System.out.println("Your choice: ");
				int userOptionSelling = input.nextInt();
				System.out.println("How much do you want to sell it for? \n Amount:");
				int amount = input.nextInt();
				
				//While loop to ensure that the other party has sufficient money to sell
				while(amount > playerArray[userOptionSelling-1].GetMoney())
				{
					System.out.println("Error: Cannot sell for more than their money!");
					amount = input.nextInt();
				}
				//If method to check if the player 
				if(playerArray[playerCounter].SellingGetOutOfJailCard(playerArray, playerCounter, userOptionSelling-1, amount) == true)
					System.out.println("Succesfully sold");
				else
					System.out.println("Error: Cannot sell to someone who already has a 'Get out of jail free card'! ");
			}
			
			
			//If the checkwinner method in rules returns true, find the player who is not bankrupt, because the rest will be bankrupt.
			if(rule.CheckWinner(playerArray, playerCounter) == true)
			{
				for(int i = 0; i<playerArray.length; i++)
				{
					if(playerArray[i].GetIsBankrupt() == false)
					{
						System.out.println("Congratulations, player " + (i+1) + "! You have won this game of Monopoly!");
						break mainLoop;

					}

				}
			}
			

		}
		input.close();
	}
}
