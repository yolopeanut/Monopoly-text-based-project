import java.util.Scanner;

//DONE BY ANSELM
public class Player {
	private String name;
	private String symbol;
	private Player[] players;
	private Location[] propertyOwned = new Location[40];
	private int playerMoney = 1500;//Starting money
	private int numOfPropertyOwned;
	private int position = 0;
	private int jailCounter = 0;
	private boolean isInDebt = false;
	private boolean isBankrupt = false;
	private boolean isInJail = false;
	private boolean getOutOfJailFree = false;

	RandomObject ro = new RandomObject();
	Rules rules = new Rules();
	public Player()
	{
		
	}
	public Player (int numOfPlayers)
	{
		players = new Player[numOfPlayers];
	}
	public Player(String name)
	{
		this.name = name;
	}
	/////////////////////////GETTER AND SETTER METHODS////////////////////////////////////
	
	//============================SETTERS=======================//
	/*Set postion of player - parameter is the newPosition of player
	 * Return true if player is not in jail
	 * Return false if player is in jail and cannot move
	 */
	public boolean SetPos(int newPos)
	{
		if(this.isInJail == false)
		{
			this.position = newPos;
			if(position>39)
				position = position-40;
			return true; 
		}
		return false;
	}
	public void SetSymbol(String symbol)
	{
		this.symbol = symbol;
	}
	public void SetMoney(int amount)
	{
		this.playerMoney = this.playerMoney + amount;
	}
	public void SetJailCounter(int counter)
	{
		this.jailCounter = counter;
	}
	public void SetIsInDebt(boolean isInDebt)
	{
		this.isInDebt = isInDebt;
	}
	
	//==========================================================//
	//======================GETTERS=============================//

	public Player[] GetPlayerArray()
	{     
		return this.players;
	}
	public Location[] GetPropertyOwned()
	{
		return this.propertyOwned;
	}
	public String GetName()
	{
		return this.name;
	}
	public String GetSymbol()
	{
		return this.symbol;
	}
	public int GetMoney()
	{
		return this.playerMoney;
	}
	public int GetPos()
	{
		return this.position;
	}
	public int GetNumOfPropertyOwned()
	{
		return this.numOfPropertyOwned;
	}
	public boolean GetIsInJail()
	{
		return this.isInJail;
	}
	public boolean GetIsBankrupt()
	{
		return this.isBankrupt;
	}
	public boolean GetIsInDebt() {
		return isInDebt;
	}
	
	public boolean GetGetOutOfJailFree()
	{
		return this.getOutOfJailFree;
	}
	public int GetJailCounter()
	{
		return this.jailCounter;
	}
	//==========================================================//

	
	//////////////////////////////////////////////////////////////////////////////////////

	//Method to add a location object to location array for players.
	public void AddLocationToOwned(Location location)
	{
		this.propertyOwned[this.numOfPropertyOwned] = location;
		this.numOfPropertyOwned++;
	}
	public void RemoveLocationFromOwned(int locationPostion)
	{
		this.propertyOwned[locationPostion] = null;
		this.numOfPropertyOwned--;
	}
	//Removed 2 here
	public void GoingToJail()
	{
		this.isInJail = true;
	}
	
	public void ReleaseFromJail()
	{
		this.isInJail = false;
	}
	
	public void GetOutOfJailFreeCard()
	{
		this.getOutOfJailFree = true;
	}
	public void UseGetoutOfJailFree()
	{
		this.getOutOfJailFree = false;
	}
	
	//Method to sell get out of jail card. takes in current player, their choice to sell to and the amount to sell for.
	//Return false if they have a get out of jail free card
	//Return true if successful transaction
	public boolean SellingGetOutOfJailCard(Player[] playerArray, int playerCounter, int playerChoice, int amount)
	{
		//If the player who wants to sell to has a get out of jail free card, return false
		if(playerArray[playerChoice].GetGetOutOfJailFree() == true)
			return false;
		else
		{
			playerArray[playerCounter].UseGetoutOfJailFree();
			playerArray[playerChoice].GetOutOfJailFreeCard();
			
			playerArray[playerCounter].SetMoney(amount);
			System.out.println("PLAYER " + (playerCounter+1) + "BALANCE: " + DisplayScreen.EURO + playerArray[playerCounter].GetMoney());
			return true;

		}
	}
	public void GoneBankrupt()
	{
		this.isBankrupt = true;
	}
	
	//Takes in playerArray, the playerCounter to get the current player,
	public boolean RollDice(Player[] playerArray, int playerCounter, Cards[] commCard, Cards[] chanceCard)
	{
		int dice1 = 0;
		int dice2 = 0;
		int diceSum = 0;
		int counter = 1;
		//If current player is not in jail
		if(playerArray[playerCounter].GetIsInJail() == false)
		{
			
			dice1 = ro.RandomDiceRoll();
			dice2 = ro.RandomDiceRoll();
			diceSum = dice1 + dice2;
			System.out.println("First Dice:" + dice1);
			System.out.println("Second Dice:" + dice2);
			
			//Checks pass go before set position.
			rules.CheckPassGo(playerArray, playerCounter, diceSum);
			//Access the method to set position of the current player based on the dice sum
			playerArray[playerCounter].SetPos(playerArray[playerCounter].GetPos() + diceSum);

			//Checks through rules when a player rolls the dice.
			rules.CheckingRules(playerArray, playerCounter, commCard, chanceCard,diceSum);
			if(playerArray[playerCounter].GetIsInJail() == false && dice1 == dice2)
			{
				return true;
			}
				
		}
		
		//If current player is in jail
		else 
		{
			
			dice1 = ro.RandomDiceRoll();
			dice2 = ro.RandomDiceRoll();
			diceSum = dice1 + dice2;
			System.out.println("First Dice:" + dice1);
			System.out.println("Second Dice:" + dice2);
			//Rolls 3 times, if dice 1 equals dice 2 break because player is no longer in jail for rolling doubles.
			while(dice1 != dice2 && counter<=3 && playerArray[playerCounter].isInJail == true)
			{
				dice1 = ro.RandomDiceRoll();
				dice2 = ro.RandomDiceRoll();
				System.out.println("First Dice:" + dice1);
				System.out.println("Second Dice:" + dice2);
				counter++;
				diceSum = dice1 + dice2;
			}
			playerArray[playerCounter].SetJailCounter((playerArray[playerCounter].GetJailCounter())+1);
			if(dice1 == dice2)
			{
				System.out.println("You have been released from jail for rolling doubles");
				playerArray[playerCounter].ReleaseFromJail();
				playerArray[playerCounter].SetJailCounter(0);

			}
			
			//Access the method to set position of the current player based on the dice sum
			playerArray[playerCounter].SetPos(playerArray[playerCounter].GetPos() + diceSum);
			//Checks through rules when a player rolls the dice.
			rules.CheckingRules(playerArray, playerCounter, commCard, chanceCard, diceSum);
			
		}
		return false;

	}
	
	
	
	
}
