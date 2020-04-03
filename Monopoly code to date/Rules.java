//WRITTEN BY BRANDON
public class Rules {
	RandomObject ro = new RandomObject();
	String euro = DisplayScreen.EURO;

	public Rules()
	{

	}
	//Rules checked when someone rolls dice
	/*
	 * Checks pass go, checks chance or community chest, checks tax
	 */
	public void CheckingRules(Player[] playerArray, int playerCounter, Cards[] commCards, Cards[] chanceCards, int diceSum)
	{
		this.CheckGettingCommOrChance(playerArray, playerCounter, commCards, chanceCards);
		this.CheckTax(playerArray, playerCounter, commCards, chanceCards);
		this.CheckRent(playerArray, playerCounter, diceSum);
		this.CheckGoToJail(playerArray, playerCounter);
		this.CheckBankrupt(playerArray, playerCounter);
		this.CheckIsInDebt(playerArray, playerCounter);
	}

	//Helper method to check pass go, if player posiiton is greater than zero, and dice sum is greater than 40 minus player location, pass go
	public void CheckPassGo(Player[] playerArray, int playerCounter, int diceSum)
	{
		Player currPlayer = playerArray[playerCounter];
		if(currPlayer.GetPos() > 0 && diceSum >= (40 - currPlayer.GetPos()))
		{
			currPlayer.SetMoney(200);
			System.out.println("You have passed Go!, Collect " + euro + "200!");
			System.out.println("CURRENT BALANCE: " + euro + playerArray[playerCounter].GetMoney() );

		}

	}
	//Method to check if player is in debt
	public void CheckIsInDebt(Player[] playerArray, int playerCounter)
	{
		if(playerArray[playerCounter].GetMoney()<0)
		{
			playerArray[playerCounter].SetIsInDebt(true);
		}
	}
	//Method to set player in jail
	public void GoToJail(Player[] playerArray, int playerCounter)
	{
		playerArray[playerCounter].SetPos(10);
		playerArray[playerCounter].GoingToJail();
	}

	//Method to check if player is at position 10 to go to jail
	public void CheckGoToJail(Player[] playerArray, int playerCounter)
	{
		if(playerArray[playerCounter].GetPos() == 30)
		{
			this.GoToJail(playerArray, playerCounter);
		}
	}
	
	//Method to check the number of times a player has rolled doubles to pay 50 
	public void CheckJailCounter(Player[] playerArray, int playerCounter)
	{
		if(playerArray[playerCounter].GetJailCounter() == 3)
		{
			playerArray[playerCounter].ReleaseFromJail();
			System.out.println("You have tried rolling doubles too many times! You will have to pay " + euro + "50");
			playerArray[playerCounter].SetMoney(-50);
			System.out.println("CURRENT BALANCE: " + euro + playerArray[playerCounter].GetMoney() );
		}
	}
	//Method to check if the current player is bankrupt.
	/*
	 * If the player has no money and the number of propertyOwned is 0
	 */
	public void CheckBankrupt(Player[] playerArray, int playerCounter)
	{
		//If the player's current money is less than 0;
		if(playerArray[playerCounter].GetMoney()<0)
		{
			//If the number of property owned is less than one, go bankrupt.
			if(playerArray[playerCounter].GetNumOfPropertyOwned()==0)
				playerArray[playerCounter].GoneBankrupt();	
		}
	}

	//Method to check whether getting community card or chance card.
	public void CheckGettingCommOrChance(Player[] playerArray, int playerCounter, Cards[] commCards, Cards[] chanceCards)
	{
		int playerPosition = playerArray[playerCounter].GetPos();
		if(playerPosition == 2 || playerPosition == 13 || playerPosition == 33 )
		{
			Cards card = ro.RandomCommCard(commCards);
			(new CommunityChestCard()).ActionCard(playerArray,playerCounter, card, chanceCards);
		}
		else if (playerPosition == 7 || playerPosition == 22 || playerPosition == 36)
		{
			Cards card = ro.RandomCommCard(chanceCards);
			(new ChanceCard()).ActionCard(playerArray,playerCounter, card);
		}

	}


	//Rules checked when someone rolls dice
	public void CheckTax(Player[] playerArray, int playerCounter, Cards[] commCards, Cards[] chanceCards)
	{
		int playerPosition = playerArray[playerCounter].GetPos();
		if(playerPosition == 4)
		{
			System.out.println("You have been taxed! Pay " + euro + "200!");
			playerArray[playerCounter].SetMoney(-200);
		}
		else if (playerPosition == 38)
		{
			System.out.println("You have been taxed! Pay " + euro + "400!");
			playerArray[playerCounter].SetMoney(-400);
		}
	}

	//Method to check rent
	public void CheckRent(Player[] playerArray, int playerCounter, int diceSum )
	{
		int locationPositioninArray = 0; //Variable to keep track of location positions

		Player currPlayer = playerArray[playerCounter];
		for(int i = 0; i<playerArray.length; i++)
		{
			int counterTrain = 0;
			int counterUtilities = 0;
			//If the player counter does not equals to i which means player cannot charge himself for rent
			if(i != playerCounter)
			{
				Location[] playerLocationOwnedArray = playerArray[i].GetPropertyOwned();
				//If the current player being checked has more than 0 property
				if (playerArray[i].GetNumOfPropertyOwned()>0)
				{
					//If they have more than 0 property, check through their location array
					for(int j = 0; j<playerLocationOwnedArray.length; j++)
					{
						//Check to make sure it does not equal null, otherwise will get null exception error
						if(playerLocationOwnedArray[j] != null)
						{
							//If the location is an instance of SpecialLocation, count the number of train and utilitiy properties owned.
							if (playerLocationOwnedArray[j] instanceof SpecialLocation)
							{ 
								int location = playerLocationOwnedArray[j].GetPosition();
								if( location == 5 || location == 15 || location == 25 || location == 35 )
									counterTrain++;
								else if(location == 18|| location == 28)
									counterUtilities++;

							}
							//If the location is a colour location
							
							if(currPlayer.GetPos() == playerLocationOwnedArray[j].GetPosition())
							{
								if(playerLocationOwnedArray[j] instanceof ColourLocation)
								{
									int ownerOfProperty = ((ColourLocation)playerLocationOwnedArray[j]).GetOwnerOfProperty();
									//If the current player position is equals to the position of the property and the owner of the property is not on the position at the same time

									if(currPlayer.GetPos() != playerArray[ownerOfProperty].GetPos())
									{
										//If statement to check to make sure that the owner of the property is not on the property with the current user
										if(currPlayer.GetPos() != ((ColourLocation)playerLocationOwnedArray[j]).GetOwnerOfProperty())
										{
											//Storing rent of the colour location into a variable
											int rent = ((ColourLocation)playerLocationOwnedArray[j]).GetRent();
											//If the rent is greater than the current players money
											if(rent > currPlayer.GetMoney())
											{
												currPlayer.SetMoney(-(currPlayer.GetMoney()));
												this.CheckIsInDebt(playerArray, playerCounter);
												this.CheckBankrupt(playerArray, playerCounter);
											}
											//If player money is more than rent cost
											else
											{
												playerArray[playerCounter].SetMoney(-rent);
											}
											playerArray[ownerOfProperty].SetMoney(rent);
											System.out.println("You have landed on '" + playerLocationOwnedArray[j].GetName() + "'! It is owned by player " + (ownerOfProperty+1));
											System.out.println("The rent amount is: " + euro + rent);
											System.out.println("Money of player " + (playerCounter+1) + " is: " + euro + playerArray[playerCounter].GetMoney());
											System.out.println("Money of player " + (ownerOfProperty+1) + " is: " + euro + playerArray[ownerOfProperty].GetMoney());
										}
										else
										{
											System.out.println("The Owner of the property is here with you! You do not have to pay the rent!");
										}
									}
								}
								else if (playerLocationOwnedArray[j] instanceof SpecialLocation)
								{ 
									locationPositioninArray = j;
								}
							}
						}
					}
					if(currPlayer.GetPos() == playerLocationOwnedArray[locationPositioninArray].GetPosition())
					{
						if(playerLocationOwnedArray[locationPositioninArray] instanceof SpecialLocation)
						{
							//If statement to check to make sure that the owner of the property is not on the property with the current user
							int ownerOfProperty = ((SpecialLocation)playerLocationOwnedArray[locationPositioninArray]).GetOwnerOfProperty();
							//If the current player position is not the same as the owner of property, else do not charge
							if(currPlayer.GetPos() != playerArray[ownerOfProperty].GetPos())
							{
								int locationPos = playerLocationOwnedArray[locationPositioninArray].GetPosition();
								//If the location either of the following, charge train rent, else charge utiliy rent
								if(locationPos == 5 || locationPos == 15 || locationPos == 25 || locationPos == 35)
								{
									int rent = ((SpecialLocation)playerLocationOwnedArray[locationPositioninArray]).GetRent(counterTrain);
									playerArray[playerCounter].SetMoney(-rent);
									playerArray[ownerOfProperty].SetMoney(rent);
									System.out.println("The charging amount is: " + rent);
									System.out.println("Money of player " + (playerCounter+1) + " is: " + euro + playerArray[playerCounter].GetMoney());
									System.out.println("Money of player " + (ownerOfProperty+1) + " is: " + euro + playerArray[ownerOfProperty].GetMoney());
								}
								else
								{
									int rent = ((SpecialLocation)playerLocationOwnedArray[locationPositioninArray]).GetRent(diceSum, counterUtilities);
									playerArray[playerCounter].SetMoney(-rent);
									playerArray[ownerOfProperty].SetMoney(rent);
									System.out.println("The charging amount is: " + rent);
									System.out.println("Money of player " + (playerCounter+1) + " is: " + euro + playerArray[playerCounter].GetMoney());
									System.out.println("Money of player " + (ownerOfProperty+1) + " is: " + euro + playerArray[ownerOfProperty].GetMoney());
								}
							}
							//owner of property is here, do not charge rent
							else
							{
								System.out.println("The Owner of the property is here with you! You do not have to pay the rent!");
							}
						}
					}
				}
			}
		}
	}

	//Method to check the winner, if all but one players are bankrupt, return true, else return false
	public boolean CheckWinner(Player[] playerArray, int playerCounter)
	{
		int counter = 0;
		for (int i = 0; i<playerArray.length; i++)
		{
			if(playerArray[i].GetIsBankrupt() == true)
			{
				counter++;
			}
		}
		if(counter == playerArray.length-1)
		{
			return true;
		}
		else 
			return false;
	}

}
