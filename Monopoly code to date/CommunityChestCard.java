//DONE BY ANSELM 
import java.util.Scanner;
public class CommunityChestCard extends Cards{

	Rules rules = new Rules();
	Scanner input = new Scanner(System.in);
	String euro = DisplayScreen.EURO;

	public CommunityChestCard()
	{

	}
	public CommunityChestCard(String specialInfo)
	{
		super(specialInfo);
	}
	public void ActionCard(Player[] playerArray, int playerCounter, Cards card, Cards[] chanceCard)
	{
		Player currPlayer = playerArray[playerCounter];
		//////////////////////////////////MOVEMENT CARDS/////////////////////////////////////////////////
		if(card.GetSpecialInfo().equals("Advance to Go"))
		{
			System.out.println(card.GetSpecialInfo());
			currPlayer.SetPos(0);
			currPlayer.SetMoney(200);
			System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		

		}
		else if (card.GetSpecialInfo().equals("Go to jail. Move directly to jail. Do not pass Go. Do not collect 200"))
		{
			System.out.println(card.GetSpecialInfo());
			currPlayer.SetPos(10);
			currPlayer.GoingToJail();

		}
		else if (card.GetSpecialInfo().equals("Go back three spaces"))
		{
			System.out.println(card.GetSpecialInfo());
			currPlayer.SetPos(currPlayer.GetPos()-3);
		}
		else if (card.GetSpecialInfo().equals("Go back to Old Kent Road"))
		{
			System.out.println(card.GetSpecialInfo());
			currPlayer.SetPos(1);
		}
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////PAYMENT CARDS/////////////////////////////////////////////////
		else if(card.GetSpecialInfo().equals("Pay hospital 100"))
		{
			System.out.println(card.GetSpecialInfo());
			currPlayer.SetMoney(-100);
			System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		
		}
		else if (card.GetSpecialInfo().equals("Doctor's fee. Pay 50"))
		{
			System.out.println(card.GetSpecialInfo());
			currPlayer.SetMoney(-50);
			System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		
		}
		else if (card.GetSpecialInfo().equals("Pay your insurance premium 50"))
		{
			System.out.println(card.GetSpecialInfo());
			currPlayer.SetMoney(-50);
			System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		
		}

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////RECEIVING CARDS/////////////////////////////////////////////////
		else if (card.GetSpecialInfo().equals("Bank error in your favour. Collect 200"))
		{
			System.out.println(card.GetSpecialInfo());
			currPlayer.SetMoney(+200);
			System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		

		}
		else if (card.GetSpecialInfo().equals("Annuity matures. Collect 100"))
		{
			System.out.println(card.GetSpecialInfo());
			currPlayer.SetMoney(+100);
			System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		

		}
		else if (card.GetSpecialInfo().equals("You inherit 100"))
		{
			System.out.println(card.GetSpecialInfo());
			currPlayer.SetMoney(+100);
			System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		

		}
		else if (card.GetSpecialInfo().equals("From sale of stock you get 50"))
		{
			System.out.println(card.GetSpecialInfo());
			currPlayer.SetMoney(+50);
			System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		

		}
		else if (card.GetSpecialInfo().equals("Receive interest on 7% preference shares: 25"))
		{
			System.out.println(card.GetSpecialInfo());
			currPlayer.SetMoney(+25);
			System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		

		}
		else if (card.GetSpecialInfo().equals("Income tax refund. Collect 20"))
		{
			System.out.println(card.GetSpecialInfo());
			currPlayer.SetMoney(+20);
			System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		

		}
		else if (card.GetSpecialInfo().equals("You have won second prize in a beauty contest. Collect 10"))
		{
			System.out.println(card.GetSpecialInfo());
			currPlayer.SetMoney(+10);
			System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		

		}
		else if (card.GetSpecialInfo().equals("It is your birthday. Collect 10 from each player"))
		{
			System.out.println(card.GetSpecialInfo());
			int numOfPlayers = playerArray.length;
			currPlayer.SetMoney((numOfPlayers-1)*10);
			
			for(int i = 0; i<numOfPlayers; i++)
			{
				if(i != playerCounter)
					playerArray[i].SetMoney(-10);
			}
			System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		

			// still adds from 1 player
		}
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////OTHER CARDS/////////////////////////////////////////////////
		else if (card.GetSpecialInfo().equals("Get out of jail free. This card may be kept until needed or sold"))
		{
			System.out.println(card.GetSpecialInfo());
			currPlayer.GetOutOfJailFreeCard();
		}
		else if (card.GetSpecialInfo().equals("Pay a 10 fine or take a Chance"))
		{
			int userChoice;
			System.out.println(card.GetSpecialInfo());
			System.out.println("Options: 1. Pay the 10 fine");
			System.out.println("Options: 2. Take a Chance");
			System.out.print("Your choice: ");
			userChoice = input.nextInt();
			while(userChoice != 1 && userChoice != 2)
			{
				userChoice = input.nextInt();
				if(userChoice != 1 && userChoice != 2)
					System.out.println("Error: Choose an option from the list!");
			}
			if(userChoice == 1) //User Pays 10 euro
			{
				currPlayer.SetMoney(-10);
				System.out.println("CURRENT BALANCE: " + euro + currPlayer.GetMoney() );		
			}
			if(userChoice == 2) //Player takes a chance
			{
				RandomObject ro = new RandomObject();
				ChanceCard chance = new ChanceCard();
				chance.ActionCard(playerArray, playerCounter, (ro.RandomChanceCard(chanceCard)));
			}

		}
	}
}
